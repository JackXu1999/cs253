package edu.emory.cs.utils.sort.distribution;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MSDRadixSort extends RadixSort{
    protected List<Deque<Integer>> buckets;

    @Override
    public void sort(Integer[] array, int beginIndex, int endIndex) {
        buckets = Stream.generate(ArrayDeque<Integer>::new).limit(10).collect(Collectors.toList()); // every collection is a stream
        int maxBit = getMaxBit(array, beginIndex, endIndex);
        for (int i = maxBit - 1; i >= 0; i--) {
            int div = (int) Math.pow(10, i);
            MSDSort(array, beginIndex, endIndex, div);
        }
    }


    protected void MSDSort(Integer[] array, int beginIndex, int endIndex, int power) {
        // add each element in the input array to the corresponding bucket
        for (int i = beginIndex; i < endIndex; i++) {
            int digit = array[i] / power;
            buckets.get(newGetBucketIndex(digit)).add(array[i]); // same integer adds to the same bucket
//            System.out.println(buckets);
        }
        // merge elements in all buckets to the input array
        for (Deque<Integer> bucket : buckets) {
            while (!bucket.isEmpty())
                array[beginIndex++] = bucket.remove();
        }
    }
    protected int newGetBucketIndex(Integer key) {
        return key % 10;
    }
}
