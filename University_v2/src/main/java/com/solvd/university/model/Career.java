package com.solvd.university.model;

import com.solvd.university.model.AcademicElement;

public class Career extends AcademicElement {

    private int totalSubjects;

    public Career(int id, String name, int totalSubjects) {
        super(id, name);
        this.totalSubjects = totalSubjects;
    }

    public int getTotalSubjects() {
        return totalSubjects;
    }
}