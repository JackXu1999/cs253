package edu.emory.cs.utils.dynamic.fibonacci;

public class RFibonacci extends AbstractFibonacci {
    @Override
    protected int get2p(int k) {
        switch (k) {
            case 0:
                return 0;
            case 1:
                return 1;
            default:
                return get2p(k - 1) + get2p(k - 2);
        }
    }
}