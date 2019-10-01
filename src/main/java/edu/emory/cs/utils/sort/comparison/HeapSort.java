package edu.emory.cs.utils.sort.comparison;

import edu.emory.cs.utils.queue.BinaryHeap;
import edu.emory.cs.utils.sort.AbstractSort;

import java.util.Comparator;

public class HeapSort<T extends Comparable<T>> extends AbstractSort<T> {
    public HeapSort() {
        this(Comparator.naturalOrder());
    }

    public HeapSort(Comparator<T> comparator) {
        super(comparator);
    }

    @Override
    public void sort(T[] array, int beginIndex, int endIndex) {
        // heapify all elements
        for (int i = getParentIndex(beginIndex, endIndex); i >= beginIndex; i--) {
            sink(array, i, beginIndex, endIndex);
        }

        // swap the endIndex element with the root element and sink it
            while (endIndex > beginIndex + 1) {
                swap(array, beginIndex, --endIndex);
                sink(array, beginIndex, beginIndex, endIndex);
            }
    }


    private void sink(T[] array, int k, int beginIndex, int endIndex) {
        for (int i = getLeftChildIndex(beginIndex, k); i < endIndex; k = i, i = getLeftChildIndex(beginIndex, k)) {
            if (i + 1 < endIndex && compareTo(array, i, i + 1) < 0) i++;
            if (compareTo(array, k, i) >= 0) break;
            swap(array, k, i);
        }
    }

    private int getParentIndex(int beginIndex, int k) { return beginIndex + (k - beginIndex) / 2 - 1; } // k is exclusive

    private int getLeftChildIndex(int beginIndex, int k) { return beginIndex + 2 * (k - beginIndex) + 1; }
}