package com.solvd.university.model;
public class Semester {

    private int semesterNumber;

    public Semester(int semesterNumber) {
        this.semesterNumber = semesterNumber;
    }

    public int getSemesterNumber() {
        return semesterNumber;
    }

    public void setSemesterNumber(int semesterNumber) {
        this.semesterNumber = semesterNumber;
    }
}