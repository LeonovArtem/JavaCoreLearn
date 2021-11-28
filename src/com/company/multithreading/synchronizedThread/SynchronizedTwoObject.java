package com.company.multithreading.synchronizedThread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SynchronizedTwoObject {
    public static void main(String[] args) throws InterruptedException {
        Worker worker = new Worker();
        worker.execute();
    }
}

class Worker {
    Random random = new Random();

    private List<Integer> list_1 = new ArrayList<>();
    private List<Integer> list_2 = new ArrayList<>();

    public void execute() throws InterruptedException {
        long before = System.currentTimeMillis();

//        work();
        Thread thread1 = new Thread(this::work);
        Thread thread2 = new Thread(this::work);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        long after = System.currentTimeMillis();
        System.out.println("Executed time: " + (after - before) + " ms");

        System.out.println("List_1: " + list_1.size());
        System.out.println("List_2: " + list_2.size());
    }

    protected void work() {
        for (int i = 0; i < 1000; i++) {
            addToList1();
            addToList2();
        }
    }

    public synchronized void addToList1() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        list_1.add(random.nextInt(100));
    }

    public synchronized void addToList2() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        list_2.add(random.nextInt(100));
    }
}
