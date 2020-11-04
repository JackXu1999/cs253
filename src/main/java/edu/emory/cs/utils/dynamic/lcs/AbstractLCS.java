package edu.emory.cs.utils.dynamic.lcs;

public abstract class AbstractLCS {
    /**
     * @param a the first string.
     * @param b the second string.
     * @return a longest common sequence of the specific strings {@code a} and {@code b}.
     */
    public String solve(String a, String b) {
        return solve(a.toCharArray(), b.toCharArray(), a.length() - 1, b.length() - 1);
    }

    /**
     * @param c the first array of characters.
     * @param d the second array of characters.
     * @param i the index of {@code c} to be compared.
     * @param j the index of {@code d} to be compared
     * @return a longest common sequence of the specific strings {@code c} and {@code d}.
     */
    protected abstract String solve(char[] c, char[] d, int i, int j);
}