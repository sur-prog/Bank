package com.buildchallenge.assignment2;

public class Assignment {
    private final String name;
    private final double pointsEarned;
    private final double pointsPossible;
    private final Category category;

    public Assignment(String name, double pointsEarned, double pointsPossible, Category category) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Assignment name cannot be empty");
        if (pointsPossible <= 0) throw new IllegalArgumentException("pointsPossible must be > 0");
        if (pointsEarned < 0 || pointsEarned > pointsPossible) throw new IllegalArgumentException("Invalid pointsEarned");

        this.name = name;
        this.pointsEarned = pointsEarned;
        this.pointsPossible = pointsPossible;
        this.category = category;
    }

    public String getName() { return name; }
    public double getPointsEarned() { return pointsEarned; }
    public double getPointsPossible() { return pointsPossible; }
    public Category getCategory() { return category; }

    public double getPercentage() {
        return (pointsEarned / pointsPossible) * 100.0;
    }
}
