package com.solvd.university.model;
import java.util.ArrayList;
import java.util.List;

public class Program extends AcademicElement {

    private Career career;
    private List<Course> courses;

    public Program(int id, String name) {
        super(id, name);
        this.courses = new ArrayList<>();
    }

    public Career getCareer() {
        return career;
    }

    public void setCareer(Career career) {
        this.career = career;
    }

    public List<Course> getCourses() {
        return courses;
    }
}