package edu.emory.cs.utils.sort.distribution;

import java.util.Comparator;
import java.util.function.Function;

public class IntegerBucketSort extends BucketSort<Integer> {
    private final int GAP;

    /**
     * @param min the minimum integer (inclusive).
     * @param max the maximum integer (exclusive).
     */
    public IntegerBucketSort(int min, int max) {
        super(max - min);
        GAP = -min;
    }

    @Override
    public void sort(Integer[] array, int beginIndex, int endIndex) {
        sort(array, beginIndex, endIndex, null);
    }

    @Override
    protected int getBucketIndex(Integer key, Function<Integer, Integer> f) {
        return key + GAP;
    }
}