package com.solvd.university.utils;

public class MockConnection {
    private final int id;

    public MockConnection(int id) {
        this.id = id;
    }

    public void open() {
        System.out.println("Opening connection: " + id);
    }

    public void close() {
        System.out.println("Closing connection: " + id);
    }

    public int getId() {
        return id;
    }
}
