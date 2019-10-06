package edu.emory.cs.utils.tree.balanced;

import edu.emory.cs.utils.tree.AbstractBinaryNode;

public class RedBlackNode<T extends Comparable<T>> extends AbstractBinaryNode<T, RedBlackNode<T>> {
    /**
     * If {@code true}, this node is red; otherwise, black.
     */
    private boolean b_red;

    public RedBlackNode(T key) {
        super(key);
        setToRed();
    }

    //	============================== Setters ==============================
    public void setToRed() {
        b_red = true;
    }

    public void setToBlack() {
        b_red = false;
    }

    //	============================== Checks ==============================
    public boolean isRed() {
        return b_red;
    }

    public boolean isBlack() {
        return !b_red;
    }

    //	=================================================================
    @Override
    public String toString() {
        String color = isRed() ? "R" : "B";
        return key + ":" + color + " -> (" + left_child + ", " + right_child + ")";
    }
}
