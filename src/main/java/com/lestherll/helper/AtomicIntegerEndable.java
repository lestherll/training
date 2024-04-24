package com.lestherll.helper;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerEndable extends AtomicInteger implements Endable {

    private final int end;

    public AtomicIntegerEndable(int value, int end) {
        super(value);
        this.end = end;
    }

    public boolean hasEnded() {
        return this.get() >= this.end;
    }

    public int getCurrent() {
        return this.get();
    }

    public void step() {
        this.incrementAndGet();
    }
}
