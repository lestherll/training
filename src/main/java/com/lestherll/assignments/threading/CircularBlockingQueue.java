package com.lestherll.assignments.threading;

import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class CircularBlockingQueue<T> implements BlockingQueue<T> {
    private final int capacity;
    private final List<T> list;
    private int count = 0;
    private int current = 0;
    private int next = 0;

    public CircularBlockingQueue(int capacity) {
        this.capacity = capacity;
        this.list = new ArrayList<>(Collections.nCopies(capacity, null));
    }

    @Override
    public boolean add(T t) {
        return false;
    }

    @Override
    public boolean offer(T t) {
        return false;
    }

    @Override
    public T remove() {
        return null;
    }

    @Override
    public T poll() {
        return null;
    }

    @Override
    public T element() {
        return null;
    }

    @Override
    public T peek() {
        return null;
    }

    @Override
    public void put(T t) throws InterruptedException {
        synchronized (this) {
            while (this.isFull()) {
                this.wait();
            }
            this.list.set(this.next, t);
            this.next = (this.next + 1) % capacity;
            this.count += 1;
            this.notify();
        }
    }

    @Override
    public boolean offer(T t, long timeout, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public T take() throws InterruptedException {
        synchronized (this) {
            while (this.isEmpty()) {
                this.wait();
            }
            T t = this.list.get(this.current);
            this.current = (this.current + 1) % capacity;
            this.count -= 1;
            this.notify();
            return t;
        }
    }

    @Override
    public T poll(long timeout, TimeUnit unit) throws InterruptedException {
        return null;
    }

    @Override
    public int remainingCapacity() {
        return 0;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public int size() {
        synchronized (this) {
            return this.count;
        }
    }

    @Override
    public boolean isEmpty() {
        synchronized (this) {
            return this.size() == 0;
        }
    }

    public boolean isFull() {
        synchronized (this) {
            return this.size() == this.capacity;
        }
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return this.list.subList(this.current, this.next).iterator();
//        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    @Override
    public int drainTo(Collection<? super T> c) {
        return 0;
    }

    @Override
    public int drainTo(Collection<? super T> c, int maxElements) {
        return 0;
    }

    @Override
    public String toString() {
        return this.list.subList(this.current, this.next).toString();
    }
}

