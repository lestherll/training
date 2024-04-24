package com.lestherll.demo.threadingdemo;

import java.util.concurrent.*;

public class ExecutorDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<Integer> c = () -> {
//            Thread.sleep(5_000);
            return 10;
        };

        ExecutorService exec = Executors.newCachedThreadPool();

        Future<Integer> res = exec.submit(c);
        System.out.println(res.get());
        exec.shutdown();

    }
}
