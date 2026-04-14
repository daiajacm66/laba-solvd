package com.solvd.university.model;
public abstract class BaseEntity {

    protected int id;

    public BaseEntity(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}