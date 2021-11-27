package com.company.multithreading.createThread;

/**
 * Первый способ создания потоков
 * Потоки не синхронизированны
 */
public class FirstExample {
    public static void main(String[] args) throws InterruptedException {
        MyThread myThread_1 = new MyThread();
        MyThread myThread_2 = new MyThread();
        myThread_1.start();
        Thread.sleep(1000);
        myThread_2.start();

        Thread.sleep(2000);
        System.out.println("MAIN_THREAD!");
    }

    public static class MyThread extends Thread {

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println("Hello thread_" + i);
            }
        }
    }
}
