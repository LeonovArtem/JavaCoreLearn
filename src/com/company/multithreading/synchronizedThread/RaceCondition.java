package com.company.multithreading.synchronizedThread;

/**
 * Race condition - несколько потоков пишут в одну переменную
 */
public class RaceCondition {
    private static int counter;

    public static void main(String[] args) throws InterruptedException {
        RaceCondition main = new RaceCondition();
        main.doWork();
        System.out.println("Main:Counter = " + counter);

        Thread.sleep(2000);
        System.out.println("Result:Counter = " + counter);
    }

    /**
     * Race condition:
     *  counter = counter + 1;
     *  В первый момент времени 1-й поток забирает проц. время и в итоге записывает 107 в результат
     *  1: 100 -> 101..107
     *  2: 100 -> 101 (Второй поток считал значение 100).
     *  Во второй момент времени 2-й поток инкрементит 100 и записывает в результат 101!
     */
    private void doWork() throws InterruptedException {
        Thread thread_1 = new Thread(this::increment);
        Thread thread_2 = new Thread(this::increment);

        thread_1.start();
        thread_2.start();

        /* join - поток main останаливается и ждет пока выполнится thread_1 */
        thread_1.join();
        thread_2.join();

        System.out.println("doWork:Counter = " + counter);
    }

    private void increment() {
        for (int i = 0; i < 10000; i++) {
            counter = counter + 1;
        }
    }
}
