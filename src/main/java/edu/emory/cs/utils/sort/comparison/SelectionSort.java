package edu.emory.cs.utils.sort.comparison;

import edu.emory.cs.utils.sort.AbstractSort;

import java.util.Comparator;

public class SelectionSort<T extends Comparable<T>> extends AbstractSort<T> {
    public SelectionSort() { this(Comparator.naturalOrder()); }

    public SelectionSort(Comparator<T> comparator) {
        super(comparator);
    }

    @Override
    public void sort(T[] array, final int beginIndex, final int endIndex) {
        for (int i = beginIndex; i < endIndex - 1; i++) {
            int min = i;

            for (int j = i + 1; j < endIndex; j++) {
                if (compareTo(array, j, min) < 0)
                    min = j;
            }

            swap(array, i, min);
        }
    }
}