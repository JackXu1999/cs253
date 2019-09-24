package edu.emory.cs.utils.sort.comparison;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;

public class ShellSortPratt<T extends Comparable<T>> extends ShellSort<T> {
    public ShellSortPratt() {
        this(Comparator.naturalOrder());
    }

    public ShellSortPratt(Comparator<T> comparator) {
        this(comparator, 10000);
    }

    public ShellSortPratt(Comparator<T> comparator, int n) {
        super(comparator, n);
    }

    @Override
    protected void populateSequence(int n) {
        n /= 3;
        for (int t = sequence.size(); t <= n; t++) {
            int h = (int) (Math.pow(2, t));
            if (h <= n) sequence.add(h);
            for (int s = t; s <= n; s++) {
                int k = (int) (Math.pow(3, t));
                if (k > n) break;
                if (h * k <= n) {
                    sequence.add(k);
                } else {
                    break;
                }
            }
            if (h > n) break;
        }
        sequence = new ArrayList<> (new HashSet<>(sequence)); // remove the duplicates in the List
//        System.out.println(sequence);
        Collections.sort(sequence);
//        for (int i = sequence.size(); i <= n; i++) {
//            if ((Math.pow(2, i) <= n) || (Math.pow(3, i) <= n)) sequence.add(i);
//        }
//        System.out.println((sequence));
//        Collections.sort(sequence);
//        for (int i = sequence.size(); i <= n; i++) {
//            for (int j = sequence.size(); j <= i; j++) {
//                if ( i  Math.pow(3, i) * Math.pow(2, j) ) {
//
//                }
//        }
    }
    @Override
    protected int getSequenceStartIndex(int n) {
        int index = Collections.binarySearch(sequence, n / 3);
        if (index < 0) index = -(index + 1);
        if (index == sequence.size()) index--;
        return index;
    }
}
