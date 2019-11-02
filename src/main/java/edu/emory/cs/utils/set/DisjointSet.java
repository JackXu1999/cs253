package edu.emory.cs.utils.set;

import java.util.Arrays;

public class DisjointSet {
    private int[] s_root;

    public DisjointSet(DisjointSet set) {
        s_root = Arrays.copyOf(set.s_root, set.s_root.length);
    }

    public DisjointSet(int size) {
        s_root = new int[size];
        Arrays.fill(s_root, -1);
    }

    public int union(int id1, int id2) {
        int r1 = find(id1);
        int r2 = find(id2);
        if (r1 == r2) return r1;

        if (s_root[r1] < s_root[r2]) {
            s_root[r1] += s_root[r2];
            s_root[r2] = r1;
            return r1;
        } else {
            s_root[r2] += s_root[r1];
            s_root[r1] = r2;
            return r2;
        }
    }

    public int find(int id) {
        return (s_root[id] < 0) ? id : (s_root[id] = find(s_root[id]));
    }

    public boolean inSameSet(int id1, int id2) {
        return find(id1) == find(id2);
    }

    public String toString() {
        return Arrays.toString(s_root);
    }
}
