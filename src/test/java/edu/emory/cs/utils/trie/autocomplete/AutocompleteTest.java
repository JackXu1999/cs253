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
        final int max = 10;

        Autocomplete<?> ac = new AutocompleteXu(dict_file, max);
        Eval eval = new Eval();
        testAutocomplete(ac, eval);
    }

    private void testAutocomplete(Autocomplete<?> ac, Eval eval) {
        String prefix;
        List<String> expected;

//        prefix = "zoo";
//        expected = List.of("she", "ship", "shell");
//        testGetCandidates(ac, eval, prefix, expected);
//
//        prefix = "zoo";
//        expected = List.of("ship", "she", "shell");
//        ac.pickCandidate(prefix, "zoobenthos");
//        testGetCandidates(ac, eval, prefix, expected);
//
        prefix = "zoobenthosghgggs";
        expected = List.of("ship", "she", "shell");
        ac.pickCandidate(prefix, "zoobenthosdddd");
        testGetCandidates(ac, eval, prefix, expected);

        prefix = "zoobentho";
        expected = List.of("ship", "she", "shell");
        ac.pickCandidate(prefix, "zoobenthos");
        testGetCandidates(ac, eval, prefix, expected);

        prefix = "abcde";
        expected = List.of("ship", "she", "shell");
        testGetCandidates(ac, eval, prefix, expected);

        prefix = "zoobentho";
        expected = List.of("ship", "she", "shell");
        testGetCandidates(ac, eval, prefix, expected);

        prefix = "zoobenthosghgggs";
        expected = List.of("ship", "she", "shell");
        testGetCandidates(ac, eval, prefix, expected);

        prefix = "shi";
        expected = List.of("ship", "she", "shell");
        //ac.pickCandidate(prefix, "shishi");
        testGetCandidates(ac, eval, prefix, expected);

        prefix = "shi";
        expected = List.of("ship", "she", "shell");
        ac.pickCandidate(prefix, "ship");
        testGetCandidates(ac, eval, prefix, expected);

        prefix = "shi";
        expected = List.of("ship", "she", "shell");
        ac.pickCandidate(prefix, "ship");
        testGetCandidates(ac, eval, prefix, expected);

        prefix = "shi";
        expected = List.of("ship", "she", "shell");
        ac.pickCandidate(prefix, "shit");
        testGetCandidates(ac, eval, prefix, expected);

        prefix = "shit";
        expected = List.of("she", "ship", "shell");
        testGetCandidates(ac, eval, prefix, expected);

        prefix = "bi";
        expected = List.of("she", "ship", "shell", "school");
        testGetCandidates(ac, eval, prefix, expected);

        for (int i = 0; i < 3; i ++) {
            prefix = "bi";
            expected = List.of("ship", "she", "shell");
            ac.pickCandidate(prefix, "biacetylene");
            testGetCandidates(ac, eval, prefix, expected);
        }

        prefix = "bic";
        expected = List.of("ship", "she", "shell");
        testGetCandidates(ac, eval, prefix, expected);

        prefix = "bi";
        expected = List.of("ship", "she", "shell");
        ac.pickCandidate(prefix, "biabo");
        testGetCandidates(ac, eval, prefix, expected);

        prefix = "bi";
        expected = List.of("ship", "she", "shell");
        testGetCandidates(ac, eval, prefix, expected);

        prefix = "";
        expected = List.of("a", "b", "c", "d", "e");
        ac.pickCandidate(prefix, "hello");
        testGetCandidates(ac, eval, prefix, expected);

        prefix = "";
        expected = List.of("a", "b", "c", "d", "e");
        testGetCandidates(ac, eval, prefix, expected);

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