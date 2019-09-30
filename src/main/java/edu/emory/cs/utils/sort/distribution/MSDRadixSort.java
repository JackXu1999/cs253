package edu.emory.cs.utils.sort.distribution;

public class MSDRadixSort extends RadixSort{
    @Override
    public void sort(Integer[] array, int beginIndex, int endIndex) {
        int maxBit = getMaxBit(array, beginIndex, endIndex);
        for (int bit = maxBit; bit > 0; bit--) {
            int div = (int) Math.pow(10, bit);
            sort(array, beginIndex, endIndex, k -> k / div);
        }
    }


}
