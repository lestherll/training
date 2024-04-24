package com.lestherll.demo.threadingdemo;


public class ThreadDemo extends Thread {

    public static void main(String[] args) {
        ThreadDemo t = new ThreadDemo();
        Thread t1 = new Thread(t);
        t1.start();
//        t1.run();
        System.out.println("1 Thread: " + Thread.currentThread().getName());
        System.out.println("2 Thread: " + Thread.currentThread().getName());
    }

    @Override
    public void run() {
        System.out.println("inside run 1 Thread: " + Thread.currentThread().getName());
        System.out.println("inside run 2 Thread: " + Thread.currentThread().getName());
    }
}