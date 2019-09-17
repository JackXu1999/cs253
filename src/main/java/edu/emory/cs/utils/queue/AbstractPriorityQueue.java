package edu.emory.cs.utils.queue;

import java.util.Comparator;

public abstract class AbstractPriorityQueue<T extends Comparable<T>> {
    protected Comparator<T> comparator;

    public AbstractPriorityQueue(Comparator<T> comparator) {this.comparator = comparator;}

    abstract public void add(T key);

    abstract public T remove();

    abstract public int size();

    public boolean isEmpty() {return size() == 0;}
}