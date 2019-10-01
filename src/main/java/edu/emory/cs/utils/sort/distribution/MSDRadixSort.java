package edu.emory.cs.utils.sort.distribution;

import java.util.Deque;
import java.util.List;
import java.util.function.Function;

public class MSDRadixSort extends RadixSort{
    protected List<Deque<Integer>> buckets;

    @Override
    public void sort(Integer[] array, int beginIndex, int endIndex) {
        int maxBit = getMaxBit(array, beginIndex, endIndex);
        for (int i = maxBit - 1; i >= 0; i--) {
            int div = (int) Math.pow(10, i);
            MSDSort(array, beginIndex, endIndex, div);
//            sort(array, beginIndex, endIndex, k -> k % div);
        }
    }

    protected void MSDSort(Integer[] array, int beginIndex, int endIndex, int power) {
        // add each element in the input array to the corresponding bucket
        for (int i = beginIndex; i < endIndex; i++)
            buckets.get(getBucketIndex(array[i], null)).add(array[i] / power); // same integer adds to the same bucket

        // merge elements in all buckets to the input array
        for (Deque<Integer> bucket : buckets) {
            while (!bucket.isEmpty())
                array[beginIndex++] = bucket.remove();
        }
    }
}
