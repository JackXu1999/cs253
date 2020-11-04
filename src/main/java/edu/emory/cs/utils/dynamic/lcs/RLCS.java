package edu.emory.cs.utils.dynamic.lcs;

public class RLCS extends AbstractLCS {
    @Override
    protected String solve(char[] c, char[] d, int i, int j) {
        if (i < 0 || j < 0)
            return "";

        if (c[i] == d[j])
            return solve(c, d, i - 1, j - 1) + c[i];

        //Get the lcs through searching from the right of string c
        String c1 = solve(c, d, i - 1, j);
        //Get the lcs through searching from the right of string d
        String d1 = solve(c, d, i, j - 1);

        return (c1.length() > d1.length()) ? c1 : d1;
    }
}