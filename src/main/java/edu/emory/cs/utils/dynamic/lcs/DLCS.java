package edu.emory.cs.utils.dynamic.lcs;

public class DLCS extends AbstractLCS {
    @Override
    protected String solve(char[] c, char[] d, int i, int j) {
        return solve(c, d, i, j, createTable(c, d));
    }

    private String solve(char[] c, char[] d, int i, int j, int[][] table) {
        if (i < 0 || j < 0)
            return "";

        //Found common sequence
        if (c[i] == d[j])
            return solve(c, d, i - 1, j - 1, table) + c[i];

        //If search on String c has exhausted
        if (i == 0) return solve(c, d, i, j - 1, table);
        //If search on String d has exhausted
        if (j == 0) return solve(c, d, i - 1, j, table);

        //Recursively search for the table[i][j] with the largest element
        return (table[i - 1][j] > table[i][j - 1]) ? solve(c, d, i - 1, j, table) : solve(c, d, i, j - 1, table);
    }

    /**
     * Populating lcs dynamic table of the (sub)strings c and d
     *
     * @param c String 1
     * @param d String 2
     * @return dynamic table populated by finding the lcs of c[0~i] and d[0~j]
     */
    private int[][] createTable(char[] c, char[] d) {
        final int N = c.length, M = d.length;
        int[][] table = new int[N][M];

        for (int i = 1; i < N; i++)
            for (int j = 1; j < M; j++)
                table[i][j] = (c[i] == d[j]) ? table[i - 1][j - 1] + 1 : Math.max(table[i - 1][j], table[i][j - 1]);

        return table;
    }
}
