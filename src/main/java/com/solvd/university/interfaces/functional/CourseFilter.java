package com.solvd.university.interfaces.functional;

import com.solvd.university.model.Course;

@FunctionalInterface
public interface CourseFilter {
    boolean test(Course course);
}