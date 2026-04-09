package com.solvd.university.model;
public abstract class Evaluation extends BaseEntity {

    public Evaluation(int id) {
        super(id);
    }

    public abstract String getDescription();
}