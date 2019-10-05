package edu.emory.cs.utils.sort.distribution;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MSDRadixSort extends RadixSort{
    @Override
    public void sort(Integer[] array, int beginIndex, int endIndex) {
        int maxBit = getMaxBit(array, beginIndex, endIndex);
        sort(array, beginIndex, endIndex, maxBit - 1);
    }

    private void sort(Integer[] array, int beginIndex, int endIndex, int bit) {
        if (bit < 0) return;
        int div = (int) Math.pow(10, bit), bi, ei, i, j;
        int[] init_sizes = buckets.stream().mapToInt(Deque::size).toArray(); // (objectOfClass::methodName)

        for (i = beginIndex; i < endIndex; i++) {
            buckets.get(getBucketIndex(array[i], k -> k / div)).add(array[i]);
        }
        for (bit--, bi = beginIndex, i = 0; i < 10; i++, bi = ei) {
            Deque<Integer> bucket = buckets.get(i);
            j = ei = bi + bucket.size() - init_sizes[i];
            while (bucket.size() > init_sizes[i]) array[--j] = bucket.removeLast();
            sort(array, bi, ei, bit);
        }
    }
}
