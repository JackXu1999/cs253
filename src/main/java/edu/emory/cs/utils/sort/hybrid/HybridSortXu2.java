package edu.emory.cs.utils.sort.hybrid;

import edu.emory.cs.utils.queue.AbstractPriorityQueue;
import edu.emory.cs.utils.queue.LazyPriorityQueue;
import edu.emory.cs.utils.sort.AbstractSort;
import edu.emory.cs.utils.sort.comparison.HeapSort;
import edu.emory.cs.utils.sort.comparison.InsertionSort;
import edu.emory.cs.utils.sort.comparison.ShellSortKnuth;
import edu.emory.cs.utils.sort.divide_conquer.IntroSort;
import edu.emory.cs.utils.sort.divide_conquer.QuickSort;

import java.lang.reflect.Array;
import java.util.*;

public class HybridSortXu2 <T extends Comparable<T>> implements HybridSort<T> {
    private AbstractSort<T> QuickSort; // random cases
    private AbstractSort<T> ShellSort; // mostly sorted cases and sorted cases
    private AbstractSort<T> IntroSort; // random cases
    private  AbstractSort<T> InsertionSort;



    public HybridSortXu2() {
        QuickSort = new QuickSort<>();
        ShellSort = new ShellSortKnuth<>();
        IntroSort = new IntroSort<T>(new HeapSort<T>());
        InsertionSort = new InsertionSort<>();



    }

    @Override
    public T[] sort(T[][] input) {
        int NumberOfRows = input.length; // number of the rows
        PriorityQueue<T> Pqueue = new PriorityQueue<>();
//        ArrayList<Integer> RowLength = new ArrayList<>(); // arrayList of number of columns
//        ArrayList<Integer> sequence = new ArrayList<>(); // arrayList of Integers used to determine the row cases

//        if (NumberOfRows <= 5) { // if there aren't many rows, we just simply merge and sort them
//            int size = Arrays.stream(input).mapToInt(t -> t.length).sum();
//            T[] output = (T[]) Array.newInstance(input[0][0].getClass(), size);
//            int beginIndex = 0;
//
//            for (T[] t : input) {
//                System.arraycopy(t, 0, output, beginIndex, t.length);
//                beginIndex += t.length;
//            }
//
//            QuickSort.sort(output);
//            return output;
//        }

//        for (int i = 0; i < NumberOfRows; i++) { // add the length of each row
//            RowLength.add(input[i].length);
//        }
//
//        for (int i = 0; i < 20; i++) {
//            sequence.add((int) ((Math.pow(3, i) - 1) / 2)); // use Knuth Sequence to generate the determinants
//        }
        for (int i = 0; i < NumberOfRows; i++) {
            if (input[i].length <= 10) {
                InsertionSort.sort(input[i]);
            } else {
                IntroSort.sort(input[i]);
            }
        }

        for (int i = 0; i < NumberOfRows; i++)
            for (int j = 0; j < input[i].length; j++)
                Pqueue.add(input[i][j]);

        T[] result = (T[]) Array.newInstance(input[0][0].getClass(), Pqueue.size());
        int k = 0;
        while (Pqueue.size() != 0)
            result[k++] = Pqueue.remove();
        return result;
    }

//    private ArrayList<T> merge(T[] input1, T[] input2) {
//        int a = input1.length;
//        int b = input2.length;
//        int length;
//        int fst = 0;
//        int snd = 0;
//        if (a >= b) length = a;
//        else length = b;
//        for (int i = 0; i < length; i++) {
//            if (input1[i] != null && input2[i] != null) {
//                if (input1[i].compareTo(input2[i]) <= 0) output.add(input1[i]);
//                else output.add(input2[i]);
//            }
//            else if (input1[i] == null && input2[i] != null) output.add(input2[i]);
//            else if (input1[i] != null && input2[i] == null) output.add(input1[i]);
//        }
//        return output;
//    }
}
