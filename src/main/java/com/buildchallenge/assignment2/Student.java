package com.buildchallenge.assignment2;

import java.util.*;

public class Student {
    private final String studentId;
    private final String name;
    private final List<Course> courses = new ArrayList<>();

    public Student(String studentId, String name) {
        if (studentId == null || studentId.isBlank()) throw new IllegalArgumentException("StudentId cannot be empty");
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Name cannot be empty");

        this.studentId = studentId;
        this.name = name;
    }

    public String getStudentId() { return studentId; }
    public String getName() { return name; }
    public List<Course> getCourses() { return new ArrayList<>(courses); }

    public void enroll(Course course) {
        courses.add(course);
    }

    public Course getCourseOrThrow(String courseName) {
        return courses.stream()
                .filter(c -> c.getCourseName().equalsIgnoreCase(courseName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Course not found: " + courseName));
    }
}
