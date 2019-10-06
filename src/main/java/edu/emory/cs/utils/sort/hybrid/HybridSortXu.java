package edu.emory.cs.utils.sort.hybrid;

import edu.emory.cs.utils.sort.distribution.LSDRadixSort;

import java.lang.reflect.Array;
import java.util.Arrays;

public class HybridSortXu <T extends Comparable<T>> extends LSDRadixSort implements HybridSort<T>  {
    private LSDRadixSort engine;
    private Integer[] outputPositive;
    private Integer[] outputNegative;
    private Integer[] outputFinal;

    public HybridSortXu() {
        engine = new LSDRadixSort();
    }

    private void merge (T[][] input) {
        int size = Arrays.stream(input).mapToInt(t -> t.length).sum();
        outputFinal = (Integer[])Array.newInstance(input[0][0].getClass(), size);
        int beginIndex = 0;
        for (T[] t : input) {
            System.arraycopy(t, 0, outputFinal, beginIndex, t.length);
            beginIndex += t.length;
        }
    }

    // TODO Has to use other methods, like stream to do
    private void divide (T[] input) {
        int a = 0;
        int b = 0;
        for (int i = 0; i < input.length; i++) {
            if (convertGenericsToInt(input[i]) >= 0) outputPositive[a++] = convertGenericsToInt(input[i]);
            else outputNegative[b++] = convertGenericsToInt(input[i]);
        }
    }

    @Override
    public T[] sort(T[][] input) {
        merge(input);
        divide(convertIntToGenerics(outputFinal));
        engine.sort(outputPositive, 0, outputPositive.length);
        return convertIntToGenerics(outputPositive);
    }


    private Integer convertGenericsToInt(T t) {
        try {
            return (Integer) t;
        } catch (ClassCastException e) {
            return null;
        }
    }

    private <T> T convertIntToGenerics(Integer i[]) {
        try {
            return (T) i;
        } catch (ClassCastException e) {
            return null;
        }
    }


}
