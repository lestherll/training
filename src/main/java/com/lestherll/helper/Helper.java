package com.lestherll.helper;

import java.util.function.Function;

public class Helper {
    public static void printOddNumber(Endable sharedObject) {
        printByCustomCondition(sharedObject, (x) -> x % 2 != 0);
    }

    public static void printEvenNumber(Endable sharedObject) {
        printByCustomCondition(sharedObject, (x) -> x % 2 == 0);
    }

    public static void printByCustomCondition(Endable sharedObject, Function<Integer, Boolean> f) {
        synchronized (sharedObject) {
            while (!sharedObject.hasEnded()) {
                while (f.apply(sharedObject.getCurrent())) {
                    try {
                        sharedObject.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName() + " \tprinted \t\t" + sharedObject.getCurrent());
                sharedObject.step();
                sharedObject.notify();
            }
        }
    }
}
