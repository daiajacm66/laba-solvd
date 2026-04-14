package com.solvd.university.model;
public abstract class Person extends BaseEntity {

    protected String name;

    public Person(int id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person: " + name;
    }
}