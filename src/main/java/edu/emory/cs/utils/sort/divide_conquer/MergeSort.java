package edu.emory.cs.utils.sort.divide_conquer;

import edu.emory.cs.utils.Utils;
import edu.emory.cs.utils.sort.AbstractSort;

import java.util.Arrays;
import java.util.Comparator;

public class MergeSort<T extends Comparable<T>> extends AbstractSort<T> {
    /** n-extra spaces. */
    private T[] temp;

    public MergeSort() {
        this(Comparator.naturalOrder());
    }

    public MergeSort(Comparator<T> comparator) {
        super(comparator);
    }

    @Override
    public void sort(T[] array, int beginIndex, int endIndex) {
        if (beginIndex + 1 >= endIndex) return;
        int middleIndex = Utils.getMiddleIndex(beginIndex, endIndex);

        // sort left partition
        sort(array, beginIndex, middleIndex);
        // sort Right partition
        sort(array, middleIndex, endIndex);
        // merge partitions
        merge(array, beginIndex, middleIndex, endIndex);
    }

    /**
     * @param beginIndex  the beginning index of the 1st half (inclusive).
     * @param middleIndex the ending index of the 1st half (exclusive).
     * @param endIndex    the ending index of the 2nd half (exclusive).
     */
    private void merge(T[] array, int beginIndex, int middleIndex, int endIndex) {
        int fst = beginIndex, snd = middleIndex;
        copy(array, beginIndex, endIndex);

        for (int k = beginIndex; k < endIndex; k++) {
            if (fst >= middleIndex)                    // no key left in the 1st half
                assign(array, k, temp[snd++]);
            else if (snd >= endIndex)                  // no key left in the 2nd half
                assign(array, k, temp[fst++]);
            else if (compareTo(temp, fst, snd) < 0)    // 1st key < 2nd key
                assign(array, k, temp[fst++]);
            else
                assign(array, k, temp[snd++]);
        }
    }

    private void copy(T[] array, int beginIndex, int endIndex) {
        int N = array.length;

        if (temp == null || temp.length < N)
            temp = Arrays.copyOf(array, N);
        else {
            N = endIndex - beginIndex;
            System.arraycopy(array, beginIndex, temp, beginIndex, N);
        }

        assignments += N;
    }
}
