package com.lestherll.demo.threadingdemo;

public class RunnableDemo implements Runnable {
    public static void main(String[] args) {
        RunnableDemo t = new RunnableDemo();

        // OR (Anonymous implementation)
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("inside run 1 Thread: " + Thread.currentThread().getName());
                System.out.println("inside run 2 Thread: " + Thread.currentThread().getName());
            }
        };

        // OR
        Runnable r2 = () -> {
            System.out.println("inside RUN 1 Thread: " + Thread.currentThread().getName());
            System.out.println("inside RUN 2 Thread: " + Thread.currentThread().getName());
        };

        // OR (lambda using runnable object.run)
        Runnable r3 = () -> t.run();
        // OR (or lambda qualifier)
        Runnable r4 = t::run;

        Thread t1 = new Thread(r3);
        t1.start();
        System.out.println("inside MAIN 1 Thread: " + Thread.currentThread().getName());
        System.out.println("inside MAIN 2 Thread: " + Thread.currentThread().getName());

    }

    @Override
    public void run() {
        System.out.println("inside RUN 1 Thread: " + Thread.currentThread().getName());
        System.out.println("inside RUN 2 Thread: " + Thread.currentThread().getName());
    }
}
