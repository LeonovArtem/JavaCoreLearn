package com.company.multithreading.introduction;

import java.util.Scanner;

/**
 * Проблема когерентности(равенства) кешей
 */
public class Main {
    public static void main(String[] args) {
        FirstThread firstThread = new FirstThread();
        firstThread.start();

        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        firstThread.shutdown();
    }

    static class FirstThread extends Thread {
        private boolean running = true;

        public void run() {
            while (running) {
                System.out.println("Hello: " + isRunning());
                try {
                    Thread.sleep(100);
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