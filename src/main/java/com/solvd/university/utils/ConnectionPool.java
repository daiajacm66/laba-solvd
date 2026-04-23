package com.solvd.university.utils;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ArrayBlockingQueue;

public class ConnectionPool {
    private final BlockingQueue<MockConnection> pool;

    private ConnectionPool(int size) {
        pool = new ArrayBlockingQueue<>(size);

        for (int i = 1; i <= size; i++) {
            pool.add(new MockConnection(i));
        }
    }

    private static class Holder {
        private static final ConnectionPool INSTANCE = new ConnectionPool(5);
    }

    public static ConnectionPool getInstance() {
        return Holder.INSTANCE;
    }

    public MockConnection acquire() throws InterruptedException {
        MockConnection connection = pool.take(); //Bloquea si no hay
        connection.open();
        return connection;
    }

    public void release(MockConnection conn) {
        if (conn != null) {
            conn.close();
            pool.offer(conn);
        }
    }
}
