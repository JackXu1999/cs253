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

    private void swim(int k) {
        while(k > 1 && comparator.compare(keys.get(k / 2), keys.get(k)) > 0) {
            Collections.swap(keys, k, k / 2);
            k = k / 2;
        }
    }

    private void sink(int k) {
        for (int i = 2 * k; i <= size(); k = i, i *= 2) {
            if (i < size() && comparator.compare(keys.get(i), keys.get(i + 1)) > 0) i++;
            if (comparator.compare(keys.get(k), keys.get(i)) <= 0) break;
            Collections.swap(keys, i, k);
        }
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
