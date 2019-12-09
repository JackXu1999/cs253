package edu.emory.cs.utils.dynamic;

import edu.emory.cs.utils.dynamic.hanoi.AbstractHanoi;
import edu.emory.cs.utils.dynamic.hanoi.DHanoi;
import edu.emory.cs.utils.dynamic.hanoi.RHanoi;
import org.junit.Ignore;
import org.junit.Test;

import java.util.StringJoiner;
import static org.junit.Assert.assertEquals;

public class HanoiTest {
    @Test
    public void compare() {
        final char source = 'S';
        final char intermediate = 'I';
        final char destination = 'D';

        AbstractHanoi recursive = new RHanoi();
        AbstractHanoi dynamic = new DHanoi();

        for (int k = 1; k < 20; k++)
            assertEquals(recursive.solve(k, source, intermediate, destination), dynamic.solve(k, source, intermediate, destination));
    }

    @Test
    @Ignore
    public void testSpeed() {
        final int ITERATIONS = 100;
        final int MAX_K = 20;

        AbstractHanoi recursive = new RHanoi();
        AbstractHanoi dynamic = new DHanoi();

        for (int k = 2; k < MAX_K; k++)
            System.out.println(testSpeed(ITERATIONS, k, dynamic, recursive));
    }

    String testSpeed(final int iterations, final int k, AbstractHanoi... solver) {
        StringJoiner join = new StringJoiner("\t");
        int i, j, len = solver.length;
        long st, et;

        join.add(Integer.toString(k));
        for (i = 0; i < len; i++) {
            st = System.currentTimeMillis();
            for (j = 0; j < iterations; j++) solver[i].solve(k, 'S', 'I', 'D');
            et = System.currentTimeMillis();
            join.add(Long.toString(et - st));
        }

        return join.toString();
    }
}