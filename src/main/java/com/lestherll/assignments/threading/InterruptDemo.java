package com.lestherll.assignments.threading;

import java.math.BigInteger;

public class InterruptDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            while (!Thread.interrupted()) {
                longComputation();
            }
            System.out.println("interrupted");
        });

        t.start();
        System.out.println(t.getName() + " : " + t.getState());

        // MAY need the sleep just to make sure
        // the thread starts long computation
        // before the interrupt happens
//        Thread.sleep(10);


        t.interrupt();

    }

    public static BigInteger longComputation() {
        System.out.println("starting long computation");
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


