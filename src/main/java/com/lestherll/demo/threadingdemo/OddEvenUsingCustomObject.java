package com.lestherll.demo.threadingdemo;


import com.lestherll.helper.Helper;
import com.lestherll.helper.Range;


public class OddEvenUsingCustomObject {

    public static void main(String[] args) {
        Range sharedObject = new Range(0, 10, 1);

        Thread oddThread1 = new Thread(() -> Helper.printOddNumber(sharedObject));
        Thread evenThread1 = new Thread(() -> Helper.printEvenNumber(sharedObject));

        evenThread1.start();
        oddThread1.start();
    }

}
