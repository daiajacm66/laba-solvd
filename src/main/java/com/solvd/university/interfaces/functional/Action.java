package com.solvd.university.interfaces.functional;

@FunctionalInterface
public interface Action<T> {
    void execute(T t);
}
