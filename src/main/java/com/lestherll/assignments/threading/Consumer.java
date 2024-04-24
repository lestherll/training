package com.lestherll.assignments.threading;

import java.util.concurrent.BlockingQueue;
import java.util.function.Function;

class Consumer<T, R> implements Runnable {

    private final BlockingQueue<T> queue;

    private final BlockingQueue<R> results;
    private final Function<T, R> transform;

    public Consumer(BlockingQueue<T> queue, BlockingQueue<R> results, Function<T, R> transform) {
        this.queue = queue;
        this.results = results;
        this.transform = transform;
    }

    public R consume() throws InterruptedException {
        T message = this.queue.take();
        R transformedMessage = this.transform.apply(message);
        this.results.put(transformedMessage);
        System.out.println(Thread.currentThread().getName() + " Consumed: " + message + " --(transformed)-> " + transformedMessage);
        return transformedMessage;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
//                Thread.sleep(3000);
                R r = this.consume();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
