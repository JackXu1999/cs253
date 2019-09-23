package edu.emory.cs.utils.sort.comparison;

import java.util.Collections;
import java.util.Comparator;

public class ShellSortHibbard<T extends Comparable<T>> extends ShellSort<T>  {
    public ShellSortHibbard() {
        this(Comparator.naturalOrder());
    }

    public ShellSortHibbard(Comparator<T> comparator) {
        this(comparator, 1000);
    }

    public ShellSortHibbard(Comparator<T> comparator, int n) {
        super(comparator, n);
    }

    @Override
    protected void populateSequence(int n) {
        n /= 2;

        for (int t = sequence.size() + 1; ; t++) {
            int h = (int) ((Math.pow(2, t) - 1) / 2);
            if (h <= n) sequence.add(h);
            else break;
        }
    }

    @Override
    protected int getSequenceStartIndex(int n) {
        int index = Collections.binarySearch(sequence, n / 2);
        if (index < 0) index = -(index + 1);
        if (index == sequence.size()) index--;
        return index;
    }
}