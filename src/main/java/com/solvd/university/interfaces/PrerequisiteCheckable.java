package com.solvd.university.interfaces;

import com.solvd.university.model.Course;

public interface PrerequisiteCheckable {
    boolean canEnroll(Course course);
}