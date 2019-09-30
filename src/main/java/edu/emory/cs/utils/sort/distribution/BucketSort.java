package edu.emory.cs.utils.sort.distribution;

import edu.emory.cs.utils.sort.AbstractSort;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class BucketSort<T extends Comparable<T>> extends AbstractSort<T> {
    protected List<Deque<T>> buckets;

    /**
     * @param numBuckets the total number of buckets.
     */
    public BucketSort(int numBuckets) {
        super(null);
        buckets = Stream.generate(ArrayDeque<T>::new).limit(numBuckets).collect(Collectors.toList());
    }

    protected void sort(T[] array, int beginIndex, int endIndex, Function<T, T> f) {
        // add each element in the input array to the corresponding bucket
        for (int i = beginIndex; i < endIndex; i++)
            buckets.get(getBucketIndex(array[i], f)).add(array[i]);

        // merge elements in all buckets to the input array
        for (Deque<T> bucket : buckets) {
            while (!bucket.isEmpty())
                array[beginIndex++] = bucket.remove();
        }
    }

    /**
     * @param key a comparable key.
     * @param f   takes one argument of type T, and return a value of type T.
     * @return the index of the bucket that the key should be inserted.
     */
    abstract protected int getBucketIndex(T key, Function<T, T> f);
}