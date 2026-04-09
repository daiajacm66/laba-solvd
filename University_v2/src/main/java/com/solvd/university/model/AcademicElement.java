package com.solvd.university.model;


public abstract class AcademicElement extends BaseEntity {

    protected String name;

    public AcademicElement(int id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }
}