package edu.emory.cs.utils.queue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class LazyPriorityQueue<T extends Comparable<T>> extends AbstractPriorityQueue<T> {
    private List<T> keys;

    public LazyPriorityQueue(Comparator<T> comparator) {
       super(comparator);
       keys = new ArrayList<>();
    }

    public LazyPriorityQueue() { this(Comparator.naturalOrder()); }

    @Override
    public void add(T key) {
        keys.add(key);
    }

    @Override
    public T remove() {
        if (isEmpty()) return null;
        T max = Collections.max(keys, comparator);
        keys.remove(max);
        return max;
    }

    @Override
    public int size() {
        return keys.size();
    }
}
