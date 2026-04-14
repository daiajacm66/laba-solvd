package com.solvd.university.model;
import java.util.ArrayList;
import java.util.List;

public class Faculty extends AcademicUnit {

    private List<Program> programs;

    public Faculty(int id, String name) {
        super(id, name);
        this.programs = new ArrayList<>();
    }

    public List<Program> getPrograms() {
        return programs;
    }
}