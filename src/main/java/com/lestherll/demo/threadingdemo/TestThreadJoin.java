package com.lestherll.demo.threadingdemo;

public class TestThreadJoin {

    static int i = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(TestThreadJoin::task);

        t1.start();
        t1.join();

        System.out.println("Task is done for thread: " + Thread.currentThread().getName());

    }

    public static void task() {
        System.out.println("Starting task for thread: " + Thread.currentThread().getName());
        System.out.println(i);
        try {
            // simulate blocking operation
            Thread.sleep(5_000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Task is done for thread: " + Thread.currentThread().getName());
    }
}
