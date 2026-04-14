package com.solvd.university.model;

import com.solvd.university.interfaces.Teachable;

public class Professor extends Person implements Teachable {

    private final String specialization;

    public Professor(int id, String name, String specialization) {
        super(id, name);
        this.specialization = specialization;
    }

    public String getSpecialization() {
        return specialization;
    }

    @Override
    public String toString() {
        return "Professor: " + name + " (" + specialization + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Professor))
            return false;
        Professor p = (Professor) o;
        return name.equals(p.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String teach() {
        return name + " is teaching " + specialization;
    }
}