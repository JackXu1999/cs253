package edu.emory.cs.utils.sort.divide_conquer;

import edu.emory.cs.utils.Utils;
import edu.emory.cs.utils.sort.AbstractSort;

import java.util.Comparator;

public class IntroSort<T extends Comparable<T>> extends QuickSort<T> {
    private AbstractSort<T> engine;

    public IntroSort(AbstractSort<T> engine) {
        this(engine, Comparator.naturalOrder());
    }

    public IntroSort(AbstractSort<T> engine, Comparator<T> comparator) {
        super(comparator);
        this.engine = engine;
    }

    @Override
    public void resetCounts() {
        super.resetCounts();
        if (engine != null) engine.resetCounts();
    }

    @Override
    public void sort(T[] array, int beginIndex, int endIndex) {
        final int maxdepth = getMaxDepth(beginIndex, endIndex);
        introsort(array, beginIndex, endIndex, maxdepth);
        comparisons += engine.getComparisonCount();
        assignments += engine.getAssignmentCount();
    }

    private void introsort(T[] array, int beginIndex, int endIndex, int maxdepth) {
        if (beginIndex >= endIndex) return;

        if (maxdepth == 0)    // encounter the worst case
            engine.sort(array, beginIndex, endIndex);
        else {
            int pivotIndex = partition(array, beginIndex, endIndex);
            introsort(array, beginIndex, pivotIndex, maxdepth - 1);
            introsort(array, pivotIndex + 1, endIndex, maxdepth - 1);
        }
    }

    protected int getMaxDepth(int beginIndex, int endIndex) {
        return 2 * (int) Utils.log2(endIndex - beginIndex);
    }
}

