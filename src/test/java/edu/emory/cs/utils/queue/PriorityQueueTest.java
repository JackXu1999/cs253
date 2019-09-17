package edu.emory.cs.utils.queue;

import edu.emory.cs.utils.Utils;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

public class PriorityQueueTest {
    @Test
    public void testAccuracy() {
        List<Integer> keys = List.of(4, 1, 3, 2, 5, 6, 8, 3, 4, 7, 5, 9, 7);

        testAccuracy(new LazyPriorityQueue<>(), Comparator.reverseOrder(), new ArrayList<>(keys));
        testAccuracy(new EagerPriorityQueue<>(), Comparator.reverseOrder(), new ArrayList<>(keys));
        testAccuracy(new BinaryHeap<>(), Comparator.reverseOrder(), new ArrayList<>(keys));

        testAccuracy(new LazyPriorityQueue<Integer>(Comparator.reverseOrder()), Comparator.naturalOrder(), new ArrayList<>(keys));
        testAccuracy(new EagerPriorityQueue<Integer>(Comparator.reverseOrder()), Comparator.naturalOrder(), new ArrayList<>(keys));
        testAccuracy(new BinaryHeap<Integer>(Comparator.reverseOrder()), Comparator.naturalOrder(), new ArrayList<>(keys));
    }

    /**
     * @param q    a priority queue.
     * @param c    a comparator used for sorting.
     * @param keys a list of comparable keys.
     */
    private <T extends Comparable<T>> void testAccuracy(AbstractPriorityQueue<T> q, Comparator<T> c, List<T> keys) {
        keys.forEach(q::add);
        keys.sort(c);
        keys.forEach(key -> assertEquals(key, q.remove()));
    }

    @Test
    public void testSpeed() {
        testSpeed(new BinaryHeap<>(), new TernaryHeap<>());
    }

    @SafeVarargs
    private void testSpeed(AbstractPriorityQueue<Integer>... qs) {
        for (int size = 1000; size <= 10000; size += 1000) {
            // JVM warmup
            benchmark(qs, 10, size);
            // benchmark all priority queues with the same keys
            Time[] times = benchmark(qs, 1000, size);

            StringJoiner joiner = new StringJoiner("\t");
            joiner.add(Integer.toString(size));
            joiner.add(Arrays.stream(times).map(t -> Long.toString(t.add)).collect(Collectors.joining("\t")));
            joiner.add(Arrays.stream(times).map(t -> Long.toString(t.remove)).collect(Collectors.joining("\t")));
            System.out.println(joiner.toString());
        }
    }

    private class Time {
        long add = 0;
        long remove = 0;
    }

    private Time[] benchmark(AbstractPriorityQueue<Integer>[] qs, int iter, int size) {
        Time[] ts = Stream.generate(Time::new).limit(qs.length).toArray(Time[]::new);
        Random rand = new Random();

        for (int i = 0; i < iter; i++) {
            int[] keys = Utils.getRandomIntArray(rand, size);

            for (int j = 0; j < qs.length; j++)
                addRuntime(qs[j], ts[j], keys);
        }

        return ts;
    }

    private void addRuntime(AbstractPriorityQueue<Integer> q, Time t, int[] keys) {
        long st, et;

        // runtime for q.add()
        st = System.currentTimeMillis();
        Arrays.stream(keys).forEach(q::add);
        et = System.currentTimeMillis();
        t.add += et - st;

        // runtime for q.remove()
        st = System.currentTimeMillis();
        while (!q.isEmpty()) q.remove();
        et = System.currentTimeMillis();
        t.remove += et - st;
    }

//    @Test
//    public void testNaryHeap() {
//        List<Integer> keys = List.of(4, 1, 3, 2, 5, 6, 8, 3, 4, 7, 5, 9, 7);
//        testAccuracy(new NaryHeap<>(3), Comparator.reverseOrder(), new ArrayList<>(keys));
//        testAccuracy(new NaryHeap<Integer>(3, Comparator.reverseOrder()), Comparator.naturalOrder(), new ArrayList<>(keys));
//        testSpeed(new NaryHeap<>(2), new NaryHeap<>(3), new NaryHeap<>(4), new NaryHeap<>(5), new NaryHeap<>(6));
//    }
    @Test
    public void testTernaryHeap() {
        List<Integer> keys = List.of(4, 1, 3, 2, 5, 6, 8, 3, 4, 7, 5, 9, 7, 11, 43, 34, 22);
        testAccuracy(new TernaryHeap<>(), Comparator.reverseOrder(), new ArrayList<>(keys));
        testAccuracy(new TernaryHeap<Integer>(Comparator.reverseOrder()), Comparator.naturalOrder(), new ArrayList<>(keys));
    }
}