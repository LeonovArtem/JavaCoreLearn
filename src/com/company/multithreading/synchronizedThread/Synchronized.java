package com.company.multithreading.synchronizedThread;

public class Synchronized {
    private static int counter;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Main:Counter = " + counter);

        Synchronized main = new Synchronized();
        main.doWork();

        Thread.sleep(2000);
        System.out.println("Result:Counter = " + counter);
    }

    private void doWork() throws InterruptedException {
        Thread thread_1 = new Thread(this::incrementCounter);
        Thread thread_2 = new Thread(this::incrementCounter);

        thread_1.start();
        thread_2.start();

        /* join - поток main останаливается и ждет пока выполнится thread_1 */
        thread_1.join();
        thread_2.join();

        System.out.println("doWork:Counter = " + counter);
    }

    private void incrementCounter() {
        for (int i = 0; i < 10000; i++) {
            /* synchronized block */
            synchronized (this) {
                ++counter;
            }
        }
    }
}
