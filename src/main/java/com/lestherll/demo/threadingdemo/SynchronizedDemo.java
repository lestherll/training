package com.lestherll.demo.threadingdemo;

public class SynchronizedDemo implements Runnable {

    static Integer i = 0;

    public static void main(String[] args) throws InterruptedException {
        SynchronizedDemo t1 = new SynchronizedDemo(); // different object
        SynchronizedDemo t2 = new SynchronizedDemo(); // different object

        Thread thread1 = new Thread(t1, "Thread1");
        Thread thread2 = new Thread(t2, "Thread2");
        Thread thread3 = new Thread(t1, "Thread3");
        Thread thread4 = new Thread(t1, "Thread4");
        Thread thread5 = new Thread(t2, "Thread5");

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
//        System.out.println(i);
    }

    // static synchronized
    public static synchronized void f() {
        // same as synchronized (Test.class) {
        try {
            i++;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println("result (inside" + Thread.currentThread().getName() + "): " + i);
    }

//    public synchronized void run() {
//        try {
//            i++;
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        System.out.println("result (inside" + Thread.currentThread().getName() + "): " + i);
//    }

    public void run() {
        try {
            synchronized (this) {
                i++;
                System.out.println("result (inside" + Thread.currentThread().getName() + "): " + i);
            }
//            i++;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    // DEADLOCK
    public void f1(Object obj1, Object obj2) {
        synchronized (obj1) {
            synchronized (obj2) {

            }
        }
    }

    public void f2(Object obj1, Object obj2) {
        synchronized (obj2) {
            synchronized (obj1) {

            }
        }
    }

}
