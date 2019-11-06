package edu.emory.cs.utils.trie.autocomplete;


import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.List;

/**
 * @author Jinho D. Choi ({@code jinho.choi@emory.edu})
 */
public class AutocompleteTest {
    class Eval {
        int correct = 0;
        int total = 0;
    }

    @Test
    public void test() throws FileNotFoundException {
        final String dict_file = "src/main/resources/dict.txt";
        final int max = 3;

        Autocomplete<?> ac = new AutocompleteXu(dict_file, max);
        Eval eval = new Eval();
        testAutocomplete(ac, eval);
    }

    private void testAutocomplete(Autocomplete<?> ac, Eval eval) {
        String prefix;
        List<String> expected;

        prefix = "ababdehww";
        expected = List.of("she", "ship", "shell");
        testGetCandidates(ac, eval, prefix, expected);

//        prefix = "sh";
//        expected = List.of("she", "ship", "shell", "school");
//        testGetCandidates(ac, eval, prefix, expected);
//
//        prefix = "sh";
//        expected = List.of("ship", "she", "shell");
//        ac.pickCandidate(prefix, "ship");
//        testGetCandidates(ac, eval, prefix, expected);

        System.out.printf("Score: %d/%d\n", eval.correct, eval.total);
    }

    private void testGetCandidates(Autocomplete<?> ac, Eval eval, String prefix, List<String> expected) {
        String log = String.format("%2d: ", eval.total);
        eval.total++;

        try {
            List<String> candidates = ac.getCandidates(prefix);

            if (expected.equals(candidates)) {
                eval.correct++;
                log += "PASS";
            }
            else
                log += String.format("FAIL -> expected = %s, returned = %s", expected, candidates);
        }
        catch (Exception e) {
            log += "ERROR";
        }

        System.out.println(log);
    }
}