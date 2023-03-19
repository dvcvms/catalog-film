package ru.mts.catalogfilm.hm18;


public class Solution {

    public static long incrementCounter(int threadsNumber, int number) throws InterruptedException {
        Thread[] threads = new Thread[threadsNumber];
        Counter counter = new Counter();

        for (int i = 0; i < threadsNumber; i++) {
            threads[i] = new Thread(new SynchronizedIncrement(counter, number));
            threads[i].start();
        }

        for (int i = 0; i < threadsNumber; i++) {
            threads[i].join();
        }

        return counter.getValue();
    }
}
