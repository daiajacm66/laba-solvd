package com.solvd.university.model;
public record Year(int yearNumber) {

    public Year {
        if (yearNumber < 1900) {
            throw new IllegalArgumentException("Invalid year");
        }
    }
}