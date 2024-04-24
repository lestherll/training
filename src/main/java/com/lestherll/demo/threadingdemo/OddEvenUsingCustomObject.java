package com.lestherll.demo.threadingdemo;


import java.util.function.Function;

/*
Custom helper range class
 */
class Range {
    private final int start;
    private final int end;
    private final int step;

    private int current;

    Range(int start, int end, int step) {
        this.start = start;
        this.end = end;
        this.step = step;
        this.current = start;
    }

    Range(int start, int end) {
        this.start = start;
        this.end = end;
        this.step = 1;
        this.current = start;
    }

    private void setCurrent(int newCurrent) {
        this.current = Math.min(newCurrent, this.end);
    }

    public void step() {
        this.setCurrent(this.current + this.step);
    }

    public void step(int nStep) {
        this.setCurrent(this.current + nStep);
    }

    public boolean hasEnded() {
        return this.current >= this.end;
    }

    public int getCurrent() {
        return current;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public int getStep() {
        return step;
    }
}

public class OddEvenUsingCustomObject {
    private static final Range sharedObject = new Range(0, 10,1);

    public static void main(String[] args) {
        Thread oddThread1 = new Thread(OddEvenUsingCustomObject::printOddNumber);
        Thread evenThread1 = new Thread(OddEvenUsingCustomObject::printEvenNumber);

        evenThread1.start();
        oddThread1.start();
    }

    public static void printOddNumber() {
        printByCustomCondition((x) -> x % 2 != 0);
    }

    public static void printEvenNumber() {
        printByCustomCondition((x) -> x % 2 == 0);
    }

    public static void printByCustomCondition(Function<Integer, Boolean> f) {
        synchronized (sharedObject) {
            while (!sharedObject.hasEnded()) {
                while (f.apply(sharedObject.getCurrent())) {
                    try {
                        sharedObject.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName() + " " + sharedObject.getCurrent());
                sharedObject.step();
                sharedObject.notify();
            }
        }
    }

}
