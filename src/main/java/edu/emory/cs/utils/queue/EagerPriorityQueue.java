package edu.emory.cs.utils.queue;

import java.util.*;

public class EagerPriorityQueue<T extends Comparable<T>> extends AbstractPriorityQueue<T> {
    private List<T> keys;

    public EagerPriorityQueue(Comparator<T> comparator) {
        super(comparator);
        keys = new ArrayList<>();
    }

    public EagerPriorityQueue() { this(Comparator.naturalOrder()); }

    @Override
    public void add(T key) {
        int index = Collections.binarySearch(keys, key, comparator);
        if (index < 0) index = -(index + 1);
        keys.add(index, key);
    }

    @Override
    public T remove() { return isEmpty() ? null : keys.remove(keys.size() - 1); }

    @Override
    public int size() { return keys.size(); }
}