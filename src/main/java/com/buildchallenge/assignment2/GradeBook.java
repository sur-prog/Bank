package com.buildchallenge.assignment2;

import java.util.*;

public class GradeBook {
    private final Map<String, Student> students = new HashMap<>();

    public void addStudent(String studentId, String name) {
        students.put(studentId, new Student(studentId, name));
    }

    public void enrollInCourse(String studentId, String courseName, int creditHours) {
        Student s = getStudentOrThrow(studentId);
        s.enroll(new Course(courseName, creditHours));
    }

    public void addAssignment(String studentId, String courseName, Assignment assignment) {
        Student s = getStudentOrThrow(studentId);
        Course c = s.getCourseOrThrow(courseName);
        c.addAssignment(assignment);
    }

    public double getCategoryAverage(String studentId, String courseName, Category category) {
        Student s = getStudentOrThrow(studentId);
        Course c = s.getCourseOrThrow(courseName);
        return c.getCategoryAverage(category);
    }

    public String getCourseGrade(String studentId, String courseName) {
        Student s = getStudentOrThrow(studentId);
        Course c = s.getCourseOrThrow(courseName);

        double percent = c.getCoursePercentage();
        return String.format("%.2f%% (%s)", percent, c.getLetterGrade());
    }

    public double calculateGPA(String studentId) {
        Student s = getStudentOrThrow(studentId);

        double totalPoints = 0;
        int totalCredits = 0;

        for (Course c : s.getCourses()) {
            totalPoints += c.getGpaPoints() * c.getCreditHours();
            totalCredits += c.getCreditHours();
        }

        if (totalCredits == 0) return 0.0;
        return totalPoints / totalCredits;
    }

    public String generateTranscript(String studentId) {
        Student s = getStudentOrThrow(studentId);

        StringBuilder sb = new StringBuilder();
        sb.append("\n===== TRANSCRIPT =====\n");
        sb.append("Student: ").append(s.getName()).append(" (").append(s.getStudentId()).append(")\n\n");

        for (Course c : s.getCourses()) {
            sb.append("Course: ").append(c.getCourseName())
                    .append(" | Credits: ").append(c.getCreditHours())
                    .append(" | Grade: ").append(String.format("%.2f", c.getCoursePercentage()))
                    .append("% (").append(c.getLetterGrade()).append(")\n");
        }

        sb.append("\nCumulative GPA: ").append(String.format("%.2f", calculateGPA(studentId))).append("\n");
        sb.append("======================\n");
        return sb.toString();
    }

    private Student getStudentOrThrow(String studentId) {
        Student s = students.get(studentId);
        if (s == null) throw new IllegalArgumentException("Student not found: " + studentId);
        return s;
    }
}
