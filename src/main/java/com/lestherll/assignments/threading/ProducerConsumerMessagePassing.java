package com.lestherll.assignments.threading;

import java.util.concurrent.BlockingQueue;
import java.util.function.Function;

class ProducerConsumerMessagePassing {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Integer> queue = new CircularBlockingQueue<>(50);     // inputs/messages
        BlockingQueue<Result<Integer, Integer>> results = new CircularBlockingQueue<>(100);   // will store results here

//        BlockingQueue<Integer> queue = new LinkedBlockingQueue<>();     // inputs/messages
//        BlockingQueue<Result<Integer, Integer>> results = new LinkedBlockingQueue<>();   // will store results here

        Function<Integer, Result<Integer, Integer>> f1 = x -> new Result<Integer, Integer>(x, x + 10);
        Function<Integer, Result<Integer, Integer>> f2 = x -> new Result<Integer, Integer>(x, x * x);

        Producer<Integer> producer = new Producer<>(queue);
        Consumer<Integer, Result<Integer, Integer>> consumer1 = new Consumer<>(queue, results, f1);
        Consumer<Integer, Result<Integer, Integer>> consumer2 = new Consumer<>(queue, results, f2);

        Thread producerThread = new Thread(producer);
        Thread consumerThread1 = new Thread(consumer1);
        Thread consumerThread2 = new Thread(consumer2);

        producerThread.start();
        consumerThread1.start();
        consumerThread2.start();

//        Thread.sleep(1000);

        // Add new messages after starting the consumer thread

//        new Thread(() -> {
//            for (int j = 50; j < 100; j++) {
//                producer.produce(j);
//            }
//        }).start();

//        int i = 0;
//        while (true) {
//            producer.produce(i);
//            i++;
//            Thread.sleep(300);
//        }

        for (int i = 0; i < 50; i++) {
//            Thread.sleep(10);
            producer.produce(i);
        }

        producerThread.interrupt();
//        System.out.println(producerThread.getState());

//        Thread.sleep(2000);
//        System.out.println("\nresults = " + results);

//        for (var r :
//                results) {
//            System.out.println(r);
//        }


    }
}
