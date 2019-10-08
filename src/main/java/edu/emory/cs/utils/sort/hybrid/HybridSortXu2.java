package edu.emory.cs.utils.sort.hybrid;

import edu.emory.cs.utils.queue.MinHeap;
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
    private PriorityQueue<T> Pqueue;
//    private AbstractSort<T> HeapSort;



    public HybridSortXu2() {
        QuickSort = new QuickSort<>();
        ShellSort = new ShellSortKnuth<>();
        IntroSort = new IntroSort<T>(new HeapSort<T>());
        InsertionSort = new InsertionSort<>();
        Pqueue = new PriorityQueue<>();
//        HeapSort = new HeapSort<>();
    }

    private int determine(T[] input) {
        int count = 0;
        int j = 0;
        while (j <= 20) {
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

    private void individualSort(T[][] input, int i) {
        int NumberOfRows = input.length; // number of the rows

//        for (int i = 0; i < NumberOfRows; i++) {
            if (input[i].length <= 25) {
                InsertionSort.sort(input[i]);
            } else {
                if (determine(input[i]) == 20) { // ascending order

                } else if (determine(input[i]) == -20) { // descending order
                    ShellSort.sort(input[i]);
                } else if (determine(input[i]) > 10) { // mostly in ascending order
                    ShellSort.sort(input[i]);
                } else if (determine(input[i]) < -10) { // mostly in descending order
                    ShellSort.sort(input[i]);
                } else {
                    IntroSort.sort(input[i]);
                }
            }
//        }
    }

    private T[] merge (T[] input1, T[] input2, int index) {
        int length1 = input1.length;
        int length2 = input2.length;
        int totalLength = length1 + length2;
        T[] output = (T[]) Array.newInstance(input1[0].getClass(), totalLength);
        int i = 0, j = 0, k = 0;
        while (i < length1 && j < length2) {
            if (input1[i].compareTo(input2[j]) < 0)
                output[k++] = input1[i++];
            else
                output[k++] = input2[j++];
        }
        while (i < length1)
            output[k++] = input1[i++];
        while (j < length2)
            output[k++] = input2[j++];
        return output;
    }

    @Override
    public T[] sort(T[][] input) {
        int NumberOfRows = input.length; // number of the rows

//        individualSort(input);

        for (int i = 0; i < NumberOfRows; i++) {
            merge()
        }

//        for (int i = 0; i < NumberOfRows; i++)
//            for (int j = 0; j < input[i].length; j++){
//                Pqueue.add(input[i][j]); // use a PriorityQueue to store the sorted results
//            }

//        T[] result = (T[]) Array.newInstance(input[0][0].getClass(), Pqueue.size());
//        int k = 0;
//        while (Pqueue.size() != 0)
//            result[k++] = Pqueue.remove();

        return result;
    }


}
