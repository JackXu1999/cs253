package edu.emory.cs.utils.queue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BinaryHeap<T extends Comparable<T>> extends AbstractPriorityQueue<T> {
    private List<T> keys;

    public BinaryHeap(Comparator<T> comparator) {
        super(comparator);
        keys = new ArrayList<>();
        keys.add(null);
    }

    public BinaryHeap() { this(Comparator.naturalOrder()); }

    @Override
    public int size() {
        return keys.size() - 1;
    }

    @Override
    public void add(T key) {
        keys.add(key);
        swim(size());
    }

    private void swim(int k) {
        while(k > 1 && comparator.compare(keys.get(k / 2), keys.get(k)) < 0) {
            Collections.swap(keys, k, k / 2);
            k = k / 2;
        }
    }

    @Override
    public T remove() {
        if (isEmpty()) return null;
        Collections.swap(keys, 1, size());
        T max = keys.remove(size());
        sink(1);
        return max;
    }

    private void sink(int k) {
        for (int i = 2 * k; i <= size(); k = i, i *= 2) {
            if (i < size() && comparator.compare(keys.get(i), keys.get(i + 1)) < 0) i++;
            if (comparator.compare(keys.get(k), keys.get(i)) >= 0) break;
            Collections.swap(keys, i, k);
        }
    }
}
