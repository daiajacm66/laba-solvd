package com.solvd.university.interfaces.functional;

import com.solvd.university.model.Grade;

@FunctionalInterface
public interface GradeEvaluator {
    boolean evaluate(Grade grade);
}