package edu.emory.cs.utils.trie.autocomplete;

import java.util.List;

public class AutocompleteChoi extends Autocomplete<List<String>> {
    public AutocompleteChoi(String dict_file, int max) {
        super(dict_file, max);
    }

    @Override
    public List<String> getCandidates(String prefix) {
        // TODO: must be modified
        if (prefix.equals("sh"))
            return List.of("she", "ship", "shell");

        return List.of("dummy", "candidate");
    }

    @Override
    public void pickCandidate(String prefix, String candidate) {
        // TODO: must be filled
    }
}
