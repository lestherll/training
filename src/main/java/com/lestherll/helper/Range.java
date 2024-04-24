package com.lestherll.helper;

public class Range implements Endable {
    private final int start;
    private final int end;
    private final int step;

    private int current;

    public Range(int start, int end, int step) {
        this.start = start;
        this.end = end;
        this.step = step;
        this.current = start;
    }

    Range(int start, int end) {
        this.start = start;
        this.end = end;
        this.step = 1;
        this.current = start;
    }

    public void step() {
        this.setCurrent(this.current + this.step);
    }

    public void step(int nStep) {
        this.setCurrent(this.current + nStep);
    }

    public boolean hasEnded() {
        return this.current >= this.end;
    }

    public int getCurrent() {
        return current;
    }

    private void setCurrent(int newCurrent) {
        this.current = Math.min(newCurrent, this.end);
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public int getStep() {
        return step;
    }
}
