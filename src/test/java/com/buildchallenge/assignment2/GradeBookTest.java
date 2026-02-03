package com.buildchallenge.assignment2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GradeBookTest {

    @Test
    void testGpaCalculation() {
        GradeBook gb = new GradeBook();
        gb.addStudent("S1", "Test");

        gb.enrollInCourse("S1", "Java", 4);
        gb.addAssignment("S1", "Java", new Assignment("Final", 90, 100, Category.FINAL_EXAM));

        double gpa = gb.calculateGPA("S1");
        assertTrue(gpa >= 0);
    }

    @Test
    void testCourseGradeFormat() {
        GradeBook gb = new GradeBook();
        gb.addStudent("S1", "Test");
        gb.enrollInCourse("S1", "Java", 4);

        gb.addAssignment("S1", "Java", new Assignment("Final", 95, 100, Category.FINAL_EXAM));

        String grade = gb.getCourseGrade("S1", "Java");
        assertTrue(grade.contains("%"));
    }
}
