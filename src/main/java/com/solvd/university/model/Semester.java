package com.solvd.university.model;
public record Semester(int semesterNumber) {

    public Semester {
        if (semesterNumber < 1 || semesterNumber > 2) {
            throw new IllegalArgumentException("Invalid semester");
        }
    }
}