package edu.emory.cs.utils.sort.hybrid;

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

    private int determine(T[] input) {
        int count = 0;
        int j = 0;
        while (j <= 12) {
            if (input[j].compareTo(input[j++]) <= 0) {
                count++;
                j++;
            }
            else {
                count--;
                j++;
            }
        }
        return count;
    }

    @Override
    public T[] sort(T[][] input) {
        int NumberOfRows = input.length; // number of the rows
        PriorityQueue<T> Pqueue = new PriorityQueue<>();
//        ArrayList<Integer> RowLength = new ArrayList<>(); // arrayList of number of columns
//        ArrayList<Integer> sequence = new ArrayList<>(); // arrayList of Integers used to determine the row cases


        for (int i = 0; i < NumberOfRows; i++) {
            if (input[i].length <= 15) {
                InsertionSort.sort(input[i]);
            } else {
                if (determine(input[i]) == 12 ) { // ascending order

                } else if (determine(input[i]) == -12) { // descending order
                    ShellSort.sort(input[i]);
                } else if (determine(input[i]) > 6) { // mostly in ascending order
                    ShellSort.sort(input[i]);
                } else if (determine(input[i]) < -6) { // mostly in descending order
                    ShellSort.sort(input[i]);
                } else {
                    IntroSort.sort(input[i]);
                }
            }
        }

        for (int i = 0; i < NumberOfRows; i++)
            for (int j = 0; j < input[i].length; j++)
                Pqueue.add(input[i][j]); // use a PriorityQueue to store the sorted results

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
