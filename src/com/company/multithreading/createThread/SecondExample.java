package com.company.multithreading.createThread;

/**
 * Второй способ создания потока
 */
public class SecondExample {
    public static void main(String[] args) {
        Thread thread = new Thread(new MyRunner());
        thread.start();

        System.out.println("MAIN_THREAD");
    }
}

class MyRunner implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("thread_" + i);
        }
    }
}
