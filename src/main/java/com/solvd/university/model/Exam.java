package com.solvd.university.model;

import com.solvd.university.interfaces.Describable;

import java.util.ArrayList;
import java.util.List;

public class Exam extends Evaluation implements Describable {

    private String date;
    private List<Grade> grades;

    public Exam(int id, String date) {
        super(id);
        this.date = date;
        this.grades = new ArrayList<>();
    }

    public String getDate() {
        return date;
    }

    public List<Grade> getGrades() {
        return grades;
    }

    @Override
    public String getDescription() {
        return "Exam on " + date;
    }
}