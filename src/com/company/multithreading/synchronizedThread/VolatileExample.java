package com.company.multithreading.synchronizedThread;

import java.util.Scanner;

/**
 * Проблема когерентности(равенства) кешей
 * volatile - решает проблему когерентности кешей.
 * Нужно использовать когда один поток записывает в переменную, а другой читает ее
 */
public class VolatileExample {

    public static void main(String[] args) {
        FirstThread firstThread = new FirstThread();
        firstThread.start();

        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        firstThread.shutdown();
        System.out.println("running = " + firstThread.isRunning());
    }

    static class FirstThread extends Thread {
        private volatile boolean running = true;

        public void run() {
            while (running) {
                System.out.println("Hello: " + isRunning());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public void shutdown() {
            this.running = false;
        }

        public boolean isRunning() {
            return running;
        }
    }
}