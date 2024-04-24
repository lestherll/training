package com.lestherll.demo.threadingdemo;

public class NotifyDemo {
    private int i = 0;
    private boolean isOdd = false;

    public static void main(String[] args) throws InterruptedException {
        NotifyDemo printer = new NotifyDemo();

        Thread oddThread = new Thread(new OddPrint(printer), "Odd Thread");
        Thread evenThread = new Thread(new EvenPrint(printer), "Even Thread");

        oddThread.start();
        evenThread.start();

//        oddThread.join();
//        evenThread.join();
    }

    public synchronized void printOdd() {
        while (isOdd) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        isOdd = true;
        System.out.println(Thread.currentThread().getName() + ": " + i);
        i = i + 1;
        notify();
    }

    public synchronized void printEven() {
        while (!isOdd) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        isOdd = false;
        System.out.println(Thread.currentThread().getName() + ": " + i);
        i = i + 1;
        notify();
    }
}

class OddPrint implements Runnable {
    private final NotifyDemo printer;

    public OddPrint(NotifyDemo printer) {
        this.printer = printer;
    }


    public void run() {
        for (int i = 1; i <= 9; i += 2) {
            printer.printOdd();
        }
    }
}

class EvenPrint implements Runnable {
    private final NotifyDemo printer;

    public EvenPrint(NotifyDemo printer) {
        this.printer = printer;
    }

    public void run() {
        for (int i = 0; i <= 10; i += 2) {
            printer.printEven();
        }
    }
}


//public class Notify {
//    public static void main(String[] args) throws InterruptedException {
//
////        NumberPrinter evenPrinter = new NumberPrinter(0, 10);
////        NumberPrinter oddPrinter = new NumberPrinter(1, 10);
//
//        NumberPrinter numberPrinter = new NumberPrinter(0, 10);
//
//        Thread thread1 = new Thread(numberPrinter);
//        Thread thread2 = new Thread(numberPrinter);
//
//        thread1.start();
//        thread2.start();
//    }
//}
//class NumberPrinter extends Thread {
//    Integer startNumber;
//    Integer current;
//    Integer endNumber;
//    NumberPrinter(int startNumber, int endNumber) {
//        this.startNumber = startNumber;
//        this.current = startNumber;
//        this.endNumber = endNumber;
//    }
//
//    @Override
//    public void run() {
//        synchronized (this) {
//            while (this.current <= this.endNumber) {
//                if (this.current % 2 != 0) {
//                    this.printOdd();
//                } else {
//                    this.printEven();
//                }
//                this.current += 1;
//            }
//        }
//    }
//
//    public void printOdd() {
//        synchronized (this.current) {
//            while (this.current % 2 != 0) {
//                try {
//                    wait();
//                } catch (InterruptedException e) {
//
//                }
//            }
//            this.current += 1;
//            notify();
//        }
//    }
//
//    public void printEven() {
//        synchronized (this.current) {
//            while (this.current % 2 == 0) {
//                try {
//                    wait();
//                } catch (InterruptedException e) {
//
//                }
//            }
//            this.current += 1;
//            notify();
//        }
//    }
//}
