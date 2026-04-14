package com.solvd.university.interfaces;

import com.solvd.university.exceptions.InvalidGradeException;
import com.solvd.university.model.Grade;

public interface Gradable {
    void addGrade(Grade grade) throws InvalidGradeException;
}