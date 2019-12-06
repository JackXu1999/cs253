package edu.emory.cs.utils.dynamic.fibonacci;

public class LFibonacci extends AbstractFibonacci {
    @Override
    protected int get2p(int k) {
        int f0 = 0, f1 = 1, f2;

        for (int i = 2; i < k; i++) {
            f2 = f0 + f1;
            f0 = f1;
            f1 = f2;
        }

        return f0 + f1;
    }
}