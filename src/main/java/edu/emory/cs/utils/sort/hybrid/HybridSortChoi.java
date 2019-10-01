package edu.emory.cs.utils.sort.hybrid;

import edu.emory.cs.utils.sort.AbstractSort;
import edu.emory.cs.utils.sort.divide_conquer.QuickSort;

import java.lang.reflect.Array;
import java.util.Arrays;

public class HybridSortChoi<T extends Comparable<T>> implements HybridSort<T> {
    private AbstractSort<T> engine;

    public HybridSortChoi() {
        engine = new QuickSort<>();
    }

    @Override
    @SuppressWarnings("unchecked")
    public T[] sort(T[][] input) {
        int size = Arrays.stream(input).mapToInt(t -> t.length).sum();
        T[] output = (T[]) Array.newInstance(input[0][0].getClass(), size);
        int beginIndex = 0;

        for (T[] t : input) {
            System.arraycopy(t, 0, output, beginIndex, t.length);
            beginIndex += t.length;
        }

        engine.sort(output);
        return output;
    }
}