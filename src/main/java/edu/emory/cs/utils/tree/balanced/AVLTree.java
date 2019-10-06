package edu.emory.cs.utils.tree.balanced;

public class AVLTree<T extends Comparable<T>> extends AbstractBalancedBinarySearchTree<T, AVLNode<T>> {
    @Override
    public AVLNode<T> createNode(T key) {
        return new AVLNode<T>(key);
    }

    @Override
    protected void rotateLeft(AVLNode<T> node) {
        super.rotateLeft(node);
        node.resetHeights();  // reset Height after rotation
    }

    @Override
    protected void rotateRight(AVLNode<T> node) {
        super.rotateRight(node);
        node.resetHeights();  // reset Height after rotation
    }

    @Override
    protected void balance(AVLNode<T> node) {
        /* Rotation cases (N = node, C = child)
         * Case 1:
         *     N
         *    /
         *   C
         *  /
         * O
         *
         * Case 2:
         *   N
         *  /
         * C
         *  \
         *   O
         *
         * Case 3:
         * N
         *  \
         *   C
         *    \
         *     O
         * Case 4:
         * N
         *  \
         *   C
         *  /
         * O
         */

        if (node == null) return;
        int bf = node.getBalanceFactor();

        if (bf == 2) {
            AVLNode<T> child = node.getLeftChild();

            if (child.getBalanceFactor() == -1)    //Case 2
                rotateLeft(child);

            rotateRight(node);        //Case 1
        } else if (bf == -2) {
            AVLNode<T> child = node.getRightChild();

            if (child.getBalanceFactor() == 1)        //Case 4
                rotateRight(child);

            rotateLeft(node);        //Case 3
        } else
            balance(node.getParent());        //Recurrsively balance parent
    }
}

