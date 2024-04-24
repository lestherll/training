package com.lestherll.demo.threadingdemo;

import java.util.concurrent.atomic.AtomicInteger;

public class OddEvenUsingCustomObject {
    private static final AtomicInteger current = new AtomicInteger(0);
    private final int start;
    private final int end;

    OddEvenUsingCustomObject(int start, int end) {
        this.start = start;
        this.end = end;
//        this.current = 0;
    }

    public static void main(String[] args) {
        OddEvenUsingCustomObject shared = new OddEvenUsingCustomObject(0, 10);

        Thread oddThread = new Thread(shared::printOddNumber);
        Thread evenThread = new Thread(shared::printEvenNumber);

        evenThread.start();
        oddThread.start();

    }

    public void printOddNumber() {
        synchronized (current) {
            while (current.get() <= 10) {
                while (current.get() % 2 == 0) {
                    try {
                        current.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName() + " " + current + " ");
                current.set(current.get() + 1);
                current.notify();
            }
        }
    }

    public void printEvenNumber() {
        synchronized (current) {
            while (current.get() <= 10) {
                while (current.get() % 2 == 1) {
                    try {
                        current.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName() + " " + current + " ");
//                current += 1;
                current.set(current.get() + 1);
                current.notify();
            }
        }
    }
}
