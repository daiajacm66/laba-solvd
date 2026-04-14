package com.solvd.university.model;
import java.util.ArrayList;
import java.util.List;

public class University extends AcademicUnit {

    private List<Faculty> faculties;

    public University(int id, String name) {
        super(id, name);
        this.faculties = new ArrayList<>();
    }

    public List<Faculty> getFaculties() {
        return faculties;
    }

    @Override
    public String toString() {
        return "University: " + name;
    }
}