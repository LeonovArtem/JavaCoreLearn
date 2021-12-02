package com.company.multithreading.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExample {

    public static void main(String[] args) throws InterruptedException {
        long startAt = System.currentTimeMillis();
        /**
         * executorService - пул потокоы
         * newFixedThreadPool - количество потоков в пуле
         */
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            executorService.submit(new Worker(i));
        }
        // Прекратить передачу заданий (запускаем потоки)
        executorService.shutdown();
        System.out.println("All task submitted");

        executorService.awaitTermination(1, TimeUnit.DAYS);
        long endAt = System.currentTimeMillis();
        System.out.println("Execution time: " + (endAt - startAt) + " ms");
    }

    static class Worker implements Runnable {
        private int id;

        public Worker(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.printf("Work %d was completed \n", id);
        }
    }
}
