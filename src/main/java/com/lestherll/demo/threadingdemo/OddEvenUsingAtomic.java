package com.lestherll.demo.threadingdemo;

import com.lestherll.helper.AtomicIntegerEndable;
import com.lestherll.helper.Endable;
import com.lestherll.helper.Helper;



public class OddEvenUsingAtomic {

    public static void main(String[] args) {

        Endable sharedObject = new AtomicIntegerEndable(0, 10);

        Thread oddThread = new Thread(() -> Helper.printOddNumber(sharedObject), "odd-thread1");
        Thread evenThread = new Thread(() -> Helper.printEvenNumber(sharedObject), "even-thread1");

        evenThread.start();
        oddThread.start();

    }

}

