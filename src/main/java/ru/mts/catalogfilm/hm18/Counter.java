package ru.mts.catalogfilm.hm18;


public class Counter {

    private long counter;

    public synchronized void increment() {
        counter++;
    }

    public long getValue() {
        return this.counter;
    }
}
