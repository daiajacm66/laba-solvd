package com.solvd.university.interfaces;

import com.solvd.university.exceptions.PrerequisiteNotMetException;
import com.solvd.university.model.Course;

public interface Enrollable {
    void enroll(Course course) throws PrerequisiteNotMetException;
}