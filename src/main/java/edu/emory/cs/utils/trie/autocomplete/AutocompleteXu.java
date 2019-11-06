package edu.emory.cs.utils.trie.autocomplete;



import edu.emory.cs.utils.trie.TrieNode;

import java.io.FileNotFoundException;
import java.util.*;

public class AutocompleteXu <T extends Comparable<T>> extends Autocomplete<T>{
    int max;
    /**
     * @param dict_path the path to the dictionary file (e.g., "src/main/resources/dict.txt").
     * @param max       the maximum number of candidates to be retrieved.
     */
    public AutocompleteXu(String dict_path, int max) throws FileNotFoundException {
        super(dict_path, max);
        this.max = max;
    }

    /**
     * @param prefix the prefix of candidate words to return.
     * @return the list of candidate words for the specific prefix.
     */
    @Override
    public List<String> getCandidates(String prefix) {
        String prefix_trimmed = prefix.trim();
        TrieNode<T> node = find(prefix_trimmed);
        Map<Character, TrieNode<T>> children = node.getChildrenMap();
        List<String> list = new ArrayList<>();
        if (node == getRoot()) {
            return null;
        }
        if (children == null) {
            return null;
        } else {
            wordSuggest(node, prefix, list);
        }
        Collections.sort(list);

        return (list.size() >= max) ? list.subList(0, max) : list.subList(0, list.size()) ;
    }

    public void wordSuggest(TrieNode node, String prefix, List<String> list) {
        if (node.isEndState()) {
            list.add(prefix);
        }
        node.getChildrenMap().forEach((c, t) -> wordSuggest((TrieNode)t, prefix + c, list));
    }

    /**
     * Memorize the specific candidate word for the specific prefix.
     *
     * @param prefix    the prefix.
     * @param candidate the selected candidate for the prefix.
     */
    @Override
    public void pickCandidate(String prefix, String candidate) {

    }
}
