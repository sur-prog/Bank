package com.buildchallenge.assignment2;

public class GradeBookDemo {
    public static void main(String[] args) {
        GradeBook gb = new GradeBook();

        gb.addStudent("S1", "Allen");
        gb.addStudent("S2", "Jeff");
        gb.addStudent("S3", "Raj");

        gb.enrollInCourse("S1", "Java", 4);
        gb.enrollInCourse("S1", "DBMS", 3);

        gb.enrollInCourse("S2", "Java", 4);
        gb.enrollInCourse("S2", "OS", 3);

        gb.enrollInCourse("S3", "DBMS", 3);
        gb.enrollInCourse("S3", "CN", 3);

        gb.addAssignment("S1", "Java", new Assignment("HW1", 20, 20, Category.HOMEWORK));
        gb.addAssignment("S1", "Java", new Assignment("Quiz1", 9, 10, Category.QUIZZES));
        gb.addAssignment("S1", "Java", new Assignment("Midterm", 24, 25, Category.MIDTERM));
        gb.addAssignment("S1", "Java", new Assignment("Final", 32, 35, Category.FINAL_EXAM));

        gb.addAssignment("S1", "DBMS", new Assignment("HW1", 18, 20, Category.HOMEWORK));
        gb.addAssignment("S1", "DBMS", new Assignment("Quiz1", 6, 10, Category.QUIZZES));
        gb.addAssignment("S1", "DBMS", new Assignment("Midterm", 22, 25, Category.MIDTERM));
        gb.addAssignment("S1", "DBMS", new Assignment("Final", 33, 35, Category.FINAL_EXAM));

        gb.addAssignment("S2", "Java", new Assignment("HW1", 17, 20, Category.HOMEWORK));
        gb.addAssignment("S2", "Java", new Assignment("Quiz1", 4, 10, Category.QUIZZES));
        gb.addAssignment("S2", "Java", new Assignment("Midterm", 24, 25, Category.MIDTERM));
        gb.addAssignment("S2", "Java", new Assignment("Final", 29, 35, Category.FINAL_EXAM));

        gb.addAssignment("S2", "OS", new Assignment("HW1", 11, 20, Category.HOMEWORK));
        gb.addAssignment("S2", "OS", new Assignment("Quiz1", 5, 10, Category.QUIZZES));
        gb.addAssignment("S2", "OS", new Assignment("Midterm", 12.5, 25, Category.MIDTERM));
        gb.addAssignment("S2", "OS", new Assignment("Final", 28.5, 35, Category.FINAL_EXAM));

        gb.addAssignment("S3", "DBMS", new Assignment("HW1", 18, 20, Category.HOMEWORK));
        gb.addAssignment("S3", "DBMS", new Assignment("Quiz1", 8, 10, Category.QUIZZES));
        gb.addAssignment("S3", "DBMS", new Assignment("Midterm", 20, 25, Category.MIDTERM));
        gb.addAssignment("S3", "DBMS", new Assignment("Final", 30, 35, Category.FINAL_EXAM));

        gb.addAssignment("S3", "CN", new Assignment("HW1", 10, 20, Category.HOMEWORK));
        gb.addAssignment("S3", "CN", new Assignment("Quiz1", 7, 10, Category.QUIZZES));
        gb.addAssignment("S3", "CN", new Assignment("Midterm", 15, 25, Category.MIDTERM));
        gb.addAssignment("S3", "CN", new Assignment("Final", 25, 35, Category.FINAL_EXAM));


        System.out.println(gb.generateTranscript("S1"));
        System.out.println(gb.generateTranscript("S2"));
        System.out.println(gb.generateTranscript("S3"));
    }
}
