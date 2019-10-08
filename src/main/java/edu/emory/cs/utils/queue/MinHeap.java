package edu.emory.cs.utils.queue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MinHeap <T extends Comparable<T>> extends AbstractPriorityQueue<T> {
    private List<T> keys;

    public MinHeap(Comparator<T> comparator) {
        super(comparator);
        keys = new ArrayList<>();
        keys.add(null);
    }

    public MinHeap() { this(Comparator.naturalOrder()); }

    private void sink(int k) {

    }

    private void swim(int k) {

    }

    @Override
    public void add(T key) {
        keys.add(key);
        swim(size());
    }

    @Override
    public T remove() {
        if (isEmpty()) return null;
        Collections.swap(keys, 1, size());
        T min = keys.remove(size());
        sink(1);
        return min;
    }

    @Override
    public int size() {
        return keys.size() - 1;
    }
}
