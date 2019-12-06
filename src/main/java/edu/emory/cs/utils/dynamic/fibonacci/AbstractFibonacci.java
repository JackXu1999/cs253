package edu.emory.cs.utils.dynamic.fibonacci;

public abstract class AbstractFibonacci {
    /**
     * @return the k'th Fibonacci number.
     * @throws IllegalArgumentException if {@code k < 0}.
     */
    public int get(int k) {
        if (k < 0) throw new IllegalArgumentException("Invalid: " + k);

        switch (k) {
            //Base cases of Fibonacci sequence
            case 0:
                return 0;
            case 1:
                return 1;

            default:
                return get2p(k);
        }
    }

    /**
     * @param k must be greater than 1.
     * @return the k'th Fibonacci number.
     */
    protected abstract int get2p(int k);
}