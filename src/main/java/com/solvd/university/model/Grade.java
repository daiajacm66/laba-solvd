package com.solvd.university.model;

import com.solvd.university.interfaces.Describable;

public class Grade extends Evaluation implements Describable {

    private int value;
    private Course course;

    public Grade(int id, int value, Course course) {
        super(id);
        this.value = value;
        this.course = course;
    }

    public int getValue() {
        return value;
    }

    public Course getCourse() {
        return course;
    }

    @Override
    public String getDescription() {
        return "Grade: " + value + " in " + course.getName();
    }

}