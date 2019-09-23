package edu.emory.cs.utils.sort.comparison;

import java.util.Collections;
import java.util.Comparator;

public class ShellSortPratt<T extends Comparable<T>> extends ShellSort<T> {
    public ShellSortPratt() {
        this(Comparator.naturalOrder());
    }

    public ShellSortPratt(Comparator<T> comparator) {
        this(comparator, 1000);
    }

    public ShellSortPratt(Comparator<T> comparator, int n) {
        super(comparator, n);
    }

    @Override
    protected void populateSequence(int n) {
//        n /= 3;
//        for (int t = sequence.size() + 1; ; t++) {
//            int h = (int) ((Math.pow(3, t)));
//            if (h <= n) sequence.add(h);
//            else break;
//        }
        for (int i = sequence.size(); i <= n; i++) {
            if ((Math.pow(2, i) <= n) || (Math.pow(3, i) <= n)) sequence.add(i);
        }
    }

    @Override
    protected int getSequenceStartIndex(int n) {
        int index = Collections.binarySearch(sequence, n / 3);
        if (index < 0) index = -(index + 1);
        if (index == sequence.size()) index--;
        return index;
    }
}
