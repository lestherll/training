package com.lestherll.demo.threadingdemo;

public class RaceCondition {

    static Integer i = 0;

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            i = i + 1;
            System.out.println(i);
        });

        Thread t2 = new Thread(() -> {
            i = i + 1;
            System.out.println(i);
        });

        t1.start(); // runnable state
        t2.start(); // runnable state

        i = i + 1;
        System.out.println(i);
    }
}
