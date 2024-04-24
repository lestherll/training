package com.lestherll.assignments.threading;

import java.util.concurrent.BlockingQueue;


class Producer<T> implements Runnable {
    private final BlockingQueue<T> queue;

    public Producer(BlockingQueue<T> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
        }
        System.out.println("producer interrupted");
    }

    public void produce(T message) {
        try {
            this.queue.put(message);
            System.out.println(Thread.currentThread().getName() + " Produced: " + message);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

