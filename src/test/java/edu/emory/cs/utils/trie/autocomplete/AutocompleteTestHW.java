/*
 * Copyright 2014, Emory University
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package edu.emory.cs.utils.trie.autocomplete;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.List;

/**
 * @author Jinho D. Choi ({@code jinho.choi@emory.edu})
 */
public class AutocompleteTestHW {
    class Eval {
        int correct = 0;
        int total = 0;
    }

    @Test
    public void test() throws FileNotFoundException {
        final String dict_file = "src/main/resources/dict.txt";
        final int max = 20;

        Autocomplete<?> ac = new AutocompleteXu<>(dict_file, max);
        Eval eval = new Eval();
        testRecency(ac, eval);

//        ac = new FrequentAutocomplete(dict_file, max);
//        eval = new Eval();
//        testFrequency(ac, eval);
    }

    private void testRecency(Autocomplete<?> ac, Eval eval) {
        String prefix, candidate;
        List<String> expected;

        // 0: empty prefix
        prefix = "";
        candidate = null;
        expected = List.of("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t");
        testCandidates(ac, eval, prefix, candidate, expected);

        // 1: prefix with only whitespaces
        prefix = "   ";
        candidate = null;
        testCandidates(ac, eval, prefix, candidate, expected);

        // 2: prefix that is not a word
        prefix = "shoc";
        candidate = null;
        expected = List.of("shock", "shocks", "shochet", "shocked", "shocker", "shochets", "shockers", "shocking", "shochetim", "shockable", "shockhead", "shocklike", "shockwave", "shockingly", "shockproof", "shockstall", "shockedness", "shockheaded", "shockability", "shockingness");
        testCandidates(ac, eval, prefix, candidate, expected);

        // 3: prefix with whitespaces at the front and back
        prefix = "  shoc   ";
        candidate = null;
        testCandidates(ac, eval, prefix, candidate, expected);

        // 4: prefix that is a word
        prefix = "she";
        candidate = null;
        expected = List.of("she", "shea", "shed", "shee", "shel", "shem", "shen", "sher", "shes", "shew", "sheaf", "sheal", "shean", "shear", "sheas", "sheat", "sheds", "shedu", "sheel", "sheen");
        testCandidates(ac, eval, prefix, candidate, expected);

        // 5: prefix with less than 20 candidates
        prefix = "zapa";
        candidate = null;
        expected = List.of("zapas", "zapara", "zaparo", "zaparan", "zapateo", "zaparoan", "zapateos", "zapatero", "zapateado", "zapateados");
        testCandidates(ac, eval, prefix, candidate, expected);

        // 6: non-existing prefix
        prefix = "jinho";
        candidate = null;
        expected = List.of();
        testCandidates(ac, eval, prefix, candidate, expected);

        // 7: existing prefix, existing candidate that is a word
        prefix = "study";
        candidate = "studys";
        expected = List.of(candidate, "study", "studying");
        testCandidates(ac, eval, prefix, candidate, expected);

        // 8: existing prefix, 1st existing candidate that is not a word
        prefix = "nonsubs";
        candidate = "nonsubsidi";
        expected = List.of(candidate, "nonsubsidy", "nonsubsidies", "nonsubsiding", "nonsubscriber", "nonsubsidiary", "nonsubsistent", "nonsubscribers", "nonsubscribing", "nonsubscripted", "nonsubsididies", "nonsubsistence", "nonsubstantial", "nonsubstantive", "nonsubstituted", "nonsubscription", "nonsubsidiaries", "nonsubstantival", "nonsubstitution", "nonsubstitutive");
        testCandidates(ac, eval, prefix, candidate, expected);

        // 9: after introducing 1st existing candidate that is not a word
        prefix = "nonsubsid";
        candidate = null;
        expected = List.of("nonsubsidi", "nonsubsidy", "nonsubsidies", "nonsubsiding", "nonsubsidiary", "nonsubsididies", "nonsubsidiaries");
        testCandidates(ac, eval, prefix, candidate, expected);

        // 10: existing prefix, 2nd existing candidate that is a word
        prefix = "nonsubs";
        candidate = "nonsubsidy";
        expected = List.of(candidate, "nonsubsidi", "nonsubsidies", "nonsubsiding", "nonsubscriber", "nonsubsidiary", "nonsubsistent", "nonsubscribers", "nonsubscribing", "nonsubscripted", "nonsubsididies", "nonsubsistence", "nonsubstantial", "nonsubstantive", "nonsubstituted", "nonsubscription", "nonsubsidiaries", "nonsubstantival", "nonsubstitution", "nonsubstitutive");
        testCandidates(ac, eval, prefix, candidate, expected);

        // 11: after introducing 2nd existing candidate that is a word
        prefix = "nonsubsid";
        candidate = null;
        expected = List.of("nonsubsidi", "nonsubsidy", "nonsubsidies", "nonsubsiding", "nonsubsidiary", "nonsubsididies", "nonsubsidiaries");
        testCandidates(ac, eval, prefix, candidate, expected);

        // 12: existing prefix, non-existing candidate
        prefix = "nonsubs";
        candidate = "nonsubsidz";
        expected = List.of(candidate, "nonsubsidy", "nonsubsidi", "nonsubsidies", "nonsubsiding", "nonsubscriber", "nonsubsidiary", "nonsubsistent", "nonsubscribers", "nonsubscribing", "nonsubscripted", "nonsubsididies", "nonsubsistence", "nonsubstantial", "nonsubstantive", "nonsubstituted", "nonsubscription", "nonsubsidiaries", "nonsubstantival", "nonsubstitution");
        testCandidates(ac, eval, prefix, candidate, expected);

        // 13: after introducing 2nd existing candidate that is a word
        prefix = "nonsubsid";
        candidate = null;
        expected = List.of("nonsubsidi", "nonsubsidy", "nonsubsidz", "nonsubsidies", "nonsubsiding", "nonsubsidiary", "nonsubsididies", "nonsubsidiaries");
        testCandidates(ac, eval, prefix, candidate, expected);

        // 14: non-existing prefix, existing candidate that is a word
        prefix = "jinho";
        candidate = "jingo";
        expected = List.of(candidate);
        testCandidates(ac, eval, prefix, candidate, expected);

        // 15: non-existing prefix, existing candidate that is not a word
        prefix = "jinhoo";
        candidate = "jinho";
        expected = List.of(candidate);
        testCandidates(ac, eval, prefix, candidate, expected);

        // 16: after introducing existing candidate that is not a word
        prefix = "jin";
        candidate = null;
        expected = List.of("jin", "jina", "jing", "jink", "jinn", "jins", "jinx", "jingo", "jingu", "jinho", "jinja", "jinks", "jinni", "jinns", "jinny", "jincan", "jinete", "jingal", "jingko", "jingle");
        testCandidates(ac, eval, prefix, candidate, expected);

        // 17: non-existing prefix, non-existing candidate
        prefix = "jinhu";
        candidate = "jinhochoi";
        expected = List.of(candidate);
        testCandidates(ac, eval, prefix, candidate, expected);

        // 18: after introducing non-existing candidate
        prefix = "jinho";
        candidate = null;
        expected = List.of("jingo", "jinho", "jinhochoi");
        testCandidates(ac, eval, prefix, candidate, expected);

        // 19: prefix with symbol
        prefix = "_";
        candidate = "_jinho";
        expected = List.of(candidate);
        testCandidates(ac, eval, prefix, candidate, expected);

        // 20: candidate with symbol
        prefix = "jinho";
        candidate = "jinho_choi";
        expected = List.of(candidate, "jingo", "jinho", "jinhochoi");
        testCandidates(ac, eval, prefix, candidate, expected);

        // 21: candidate with symbol sorted in alphabetical order
        prefix = "jinh";
        candidate = null;
        expected = List.of("jinho", "jinhochoi", "jinho_choi");
        testCandidates(ac, eval, prefix, candidate, expected);

        System.out.printf("Score: %d/%d\n", eval.correct, eval.total);
    }

    private void testFrequency(Autocomplete<?> ac, Eval eval) {
        String prefix, candidate;
        List<String> expected;

        // 0: existing prefix, existing candidate that is a word
        prefix = "study";
        candidate = "studys";
        expected = List.of(candidate, "study", "studying");
        testCandidates(ac, eval, prefix, candidate, expected);

        // 1: existing prefix, existing candidate that is a word
        prefix = "study";
        candidate = "studys";
        expected = List.of(candidate, "study", "studying");
        testCandidates(ac, eval, prefix, candidate, expected);

        // 2: existing prefix, existing candidate that is a word
        prefix = "study";
        candidate = "studying";
        expected = List.of("studys", candidate, "study");
        testCandidates(ac, eval, prefix, candidate, expected);

//      System.out.println(join(ac.getCandidates(prefix)));
        System.out.printf("Score: %d/%d\n", eval.correct, eval.total);
    }

    private void testCandidates(Autocomplete<?> ac, Eval eval, String prefix, String candidate, List<String> expected) {
        String log = String.format("%2d: ", eval.total);
        eval.total++;

        try {
            if (candidate != null) ac.pickCandidate(prefix, candidate);
            List<String> candidates = ac.getCandidates(prefix);

            if (expected.equals(candidates)) {
                eval.correct++;
                log += "PASS";
            } else
                log += String.format("FAIL -> expected = %s, returned = %s", expected, candidates);
        } catch (Exception e) {
            log += "ERROR";
        }

        System.out.println(log);
    }

//    private String join(List<String> list) {
//        StringJoiner join = new StringJoiner(", ");
//        for (String s : list) join.add("\""+s+"\"");
//        return join.toString().trim();
//    }
}
