package com.buildchallenge.assignment2;

import java.util.*;

public class Course {
    private final String courseName;
    private final int creditHours;

    private final Map<Category, Integer> weights = new HashMap<>();
    private final List<Assignment> assignments = new ArrayList<>();

    public Course(String courseName, int creditHours) {
        if (courseName == null || courseName.isBlank()) throw new IllegalArgumentException("Course name cannot be empty");
        if (creditHours <= 0) throw new IllegalArgumentException("Credit hours must be > 0");

        this.courseName = courseName;
        this.creditHours = creditHours;

        weights.put(Category.HOMEWORK, 20);
        weights.put(Category.QUIZZES, 20);
        weights.put(Category.MIDTERM, 25);
        weights.put(Category.FINAL_EXAM, 35);
    }

    public String getCourseName() { return courseName; }
    public int getCreditHours() { return creditHours; }
    public Map<Category, Integer> getWeights() { return new HashMap<>(weights); }

    public void addAssignment(Assignment assignment) {
        assignments.add(assignment);
    }

    public List<Assignment> getAssignments() {
        return new ArrayList<>(assignments);
    }

    public double getCategoryAverage(Category category) {
        List<Assignment> list = assignments.stream()
                .filter(a -> a.getCategory() == category)
                .toList();

        if (list.isEmpty()) return -1;

        double sum = 0;
        for (Assignment a : list) sum += a.getPercentage();
        return sum / list.size();
    }

    public double getCoursePercentage() {
        double totalWeighted = 0;
        int usedWeight = 0;

        for (Category c : Category.values()) {
            double avg = getCategoryAverage(c);
            if (avg >= 0) {
                int w = weights.get(c);
                totalWeighted += avg * w;
                usedWeight += w;
            }
        }

        if (usedWeight == 0) return 0;
        return totalWeighted / usedWeight;
    }

    public String getLetterGrade() {
        double p = getCoursePercentage();
        if (p >= 90) return "A";
        if (p >= 80) return "B";
        if (p >= 70) return "C";
        if (p >= 60) return "D";
        return "F";
    }

    public double getGpaPoints() {
        return switch (getLetterGrade()) {
            case "A" -> 4.0;
            case "B" -> 3.0;
            case "C" -> 2.0;
            case "D" -> 1.0;
            default -> 0.0;
        };
    }
}
