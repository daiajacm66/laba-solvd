package com.solvd.university.utils;

public class Threading extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 10; i++){
            System.out.println("Thread: " + getName());
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
