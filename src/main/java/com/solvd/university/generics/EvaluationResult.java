package com.solvd.university.generics;

public class EvaluationResult<T> {

    private T result;

    public EvaluationResult(T result) {
        this.result = result;
    }

    public T getResult() {
        return result;
    }
}
