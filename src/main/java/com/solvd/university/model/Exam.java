package com.solvd.university.model;

import com.solvd.university.interfaces.Describable;

import java.util.ArrayList;
import java.util.List;

public class Exam extends Evaluation implements Describable {
    private Year year;
    private Semester semester;
    private String date;
    private List<Grade> grades;

    public Exam(int id, Year year, Semester semester, String date) {
        super(id);
        this.year = year;
        this.semester = semester;
        this.date = date;
        this.grades = new ArrayList<>();
    }

    public Year getYear() {
        return year;
    }

    public Semester getSemester() {
        return semester;
    }

    public String getDate() {
        return date;
    }

    public List<Grade> getGrades() {
        return grades;
    }

    @Override
    public String getDescription() {
        return "Exam on " + date +
                ", semester " + semester.semesterNumber() +
                ", year " + year.yearNumber();
    }
}