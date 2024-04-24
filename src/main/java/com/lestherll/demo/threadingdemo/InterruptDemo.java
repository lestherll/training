package com.lestherll.demo.threadingdemo;

import java.math.BigInteger;

public class InterruptDemo {
    public static void main(String[] args) throws InterruptedException {

        // thread that
        Thread t1 = new Thread(() -> {
            while (!Thread.interrupted()) {
                longComputation();
                // once the long computation starts,
                // it MUST end before it can check again
                // if the thread has been interrupted
            }
        }, "LONG-COMPUTATION-THREAD");

        Thread t2 = new Thread(() -> {
            try {
                // make thread sleep, so it moves to TIMED_WAITING state
                Thread.sleep(10_000);
                System.out.println(Thread.currentThread().getName() + " WAS NOT interrupted");
            } catch (InterruptedException e) {
                // possible to interrupt a thread and raise an exception
                // EVEN when sleeping/waiting, the thread DOES NOT have
                // to wait for the sleep to end before it can throw an exception
                System.out.println(Thread.currentThread().getName() + " WAS interrupted WHILE sleeping");
                throw new RuntimeException(e);
            }
        }, "SLEEPING-THREAD");

        t1.start();
        t2.start();

        // MAY need the sleep just to make sure
        // the thread starts long computation
        // before the interrupt happens
//        Thread.sleep(10);

        System.out.println("\nStarting interrupt for thread: " + t1.getName() + "...");
        t1.interrupt();

//        System.out.println(t2.getName() + ": " + t2.getState());
        System.out.println("\nStarting interrupt for thread: " + t2.getName() + "...");
        t2.interrupt();

        // just to make sure code from here runs last
        Thread.sleep(100);
        System.out.println("\nStatus of the threads: ");
        System.out.println(t2.getName() + " is running: " + t1.isAlive());
        System.out.println(t2.getName() + " is running: " + t2.isAlive());

    }

    /*
    Simulate a heavy CPU-bound computation
     */
    public static BigInteger longComputation() {
        System.out.println("Starting long computation...");
        BigInteger result = new BigInteger("0");

        BigInteger bound = new BigInteger("100000000000000000000000000");
        BigInteger i = new BigInteger("0");
        while (i.compareTo(bound) < 0) {
            result = result.add(i);
            i = i.add(new BigInteger("1"));
        }
        return result;
    }
}


