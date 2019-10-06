package edu.emory.cs.utils.tree;

public class BinaryNode<T extends Comparable<T>> extends AbstractBinaryNode<T, BinaryNode<T>> {
    /** @param key the key of this node. */
    public BinaryNode(T key) {
        super(key);
    }
}