package com.lestherll.assignments.core;


import java.util.*;

public class JavaFundamentals1 {

    public static void main(String[] args) {
        // build data
        List<Integer> list = JavaFundamentals1.generateListData(10);
        JavaFundamentals1.printSortedList(list);

        try {
            JavaFundamentals1.throwsArithmeticException("Some error occurred while calculating");
        } catch (ArithmeticException e) {
            System.out.println(e.getMessage());
        }
    }

    private static List<Integer> generateListData(int n_elements) {
        List<Integer> list = new ArrayList<>();
        Random random = new Random(42);
        for (int i = 0; i < n_elements; i++) {
            list.add(random.nextInt(0, 1001));
        }
        return list;
    }

    // 1
    private static <T extends Comparable<T>> void printSortedList(List<T> collection) {
        System.out.println("Using TreeSet to sort");
        SortedSet<T> set = new TreeSet<>(collection);
        System.out.println(collection);
        System.out.println(set);

        System.out.println("\nUsing sort via streams (not in-place)");
        System.out.println(collection);
        System.out.println(collection.stream().sorted().toList());

        System.out.println("\nUsing sort (in-place)");
        System.out.println(collection);
        Collections.sort(collection);
        System.out.println(collection);

    }

    // 2
    public static void throwsArithmeticException(String message) throws ArithmeticException {
        throw new ArithmeticException(message);
    }

}
