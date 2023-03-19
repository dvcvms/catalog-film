package ru.mts.catalogfilm.hm18;


public class SynchronizedIncrement implements Runnable {

    private final Counter counter;

    private final int number;

    public SynchronizedIncrement(Counter counter, int number) {
        this.counter = counter;
        this.number = number;
    }

    @Override
    public void run() {
        for (int i = 0; i < this.number; i++) {
            counter.increment();
        }
    }
}
