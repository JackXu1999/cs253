package edu.emory.cs.utils.dynamic.fibonacci;

import java.util.Arrays;

public class DFibonacci extends AbstractFibonacci {
    @Override
    public int get2p(int k) {
        return get2p(k, createTable(k));
    }

    private int get2p(int k, int[] table) {
        if (table[k] < 0)
            table[k] = get2p(k - 1, table) + get2p(k - 2, table);

        return table[k];
    }

    /**
     * @param k size of dynamic table
     * @return dynamic table
     */
    private int[] createTable(int k) {
        int[] table = new int[k + 1];
        table[0] = 0;
        table[1] = 1;

        //Fill table[2~k] with -1
        Arrays.fill(table, 2, k + 1, -1);

        return table;
    }
}
