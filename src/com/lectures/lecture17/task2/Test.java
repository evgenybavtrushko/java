package com.lectures.lecture17.task2;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {
    static int i = 0;

    public static void main(String[] args) {
        int[] x = new int[100000000];
        for (int i = 0; i < 100000000; i++) {
            x[i] = (int) (Math.random() * 300) + 1;
        }
        long startTen = System.currentTimeMillis();
        ExecutorService service = Executors.newFixedThreadPool(10);
        int maxi = 0;
        for (i = 0; i < 100000000; i += 10000000) {
            service.submit(new Callable<Integer>() {
                @Override
                public Integer call() {
                    int maxi = 0;
                    for (int j = i-10000000; j < i; j++) {
                        if (x[j] > maxi) {
                            maxi = x[j];
                        }
                    }
                    service.shutdown();
                    long finishTen = System.currentTimeMillis();
                    System.out.println("" + (finishTen - startTen) + " " + maxi + " " + (Thread.currentThread().getName()));
                    return maxi;
                }
            });
        }
    }
}