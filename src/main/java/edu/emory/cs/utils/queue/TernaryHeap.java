package edu.emory.cs.utils.queue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TernaryHeap<T extends Comparable<T>> extends AbstractPriorityQueue<T> {
    private List<T> keys;

    public TernaryHeap(Comparator<T> comparator) {
        super(comparator);
        keys = new ArrayList<>();
        keys.add(null);
    }

    public TernaryHeap() { this(Comparator.naturalOrder()); }

    private void swim(int k) {
        while (1 < k && comparator.compare(keys.get((k+1) / 3), keys.get(k)) < 0) {
            Collections.swap(keys, (k+1)/3, k);
            k = (k + 1) / 3;
        }
    }


    private void sink(int k) {
        for (int i = k * 3 - 1; i <= size(); k = i, i = i * 3 - 1) {
            if (i < size() && comparator.compare(keys.get(i), keys.get(i + 1)) < 0) {
                i++;
                if (i < size() && comparator.compare(keys.get(i), keys.get(i + 1)) < 0) i++;
            } else if (i < size() - 1 && comparator.compare((keys.get(i)), keys.get(i + 2)) < 0) i += 2;
            if (comparator.compare(keys.get(k), keys.get(i)) >= 0) break;
            Collections.swap(keys, k, i);
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
        T max = keys.remove(size());
        sink(1);
        return max;
    }

    @Override
    public int size() {
        return keys.size() - 1;
    }
}
