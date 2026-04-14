package com.solvd.university.model;
public abstract class AcademicUnit extends BaseEntity {

    protected String name;

    public AcademicUnit(int id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }
}