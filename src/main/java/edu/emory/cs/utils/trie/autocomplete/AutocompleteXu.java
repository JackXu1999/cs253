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
        this.max = getMax();
    }

    /**
     * @param prefix the prefix of candidate words to return.
     * @return the list of candidate words for the specific prefix.
     */
    @Override
    public List<String> getCandidates(String prefix) {
        String prefix_trimmed = prefix.trim(); // trim out the white space
        List<String> list = new ArrayList<>();
        List<String> final_list = new ArrayList<>();
        if (find(prefix_trimmed)!= null) { // the case when we can find the prefix in the tries
            TrieNode<T> node = find(prefix_trimmed); // find the node with trimmed prefix and to lowercase
            Map<Character, TrieNode<T>> children = node.getChildrenMap(); // get all the children into a map

            if (children == null) { // if there's no children, return list
                return list;
            } else {
                wordSuggest(node, prefix_trimmed, list); // otherwise, call the recursive method to find all the candidates
            }
            Collections.sort(list); // sort the list so that it's alphabetically ordered
            if (node.getValue() != null) {
                final_list.addAll((List)node.getValue());
                for (int i = 0; i < ((List)node.getValue()).size(); i++) {
                    list.remove(((List)node.getValue()).get(i)); // remove duplicates
                }
            }
            final_list.addAll(list);
            return (final_list.size() >= max) ? final_list.subList(0, max) : final_list.subList(0, list.size()) ;
        } else { // the case when we cannot find the prefix in the tries
            return list;
        }
    }

    private void wordSuggest(TrieNode node, String prefix, List<String> list) {
        if (node.isEndState()) {
            list.add(prefix); // add to the list
        }
        node.getChildrenMap().forEach((c, t) -> wordSuggest((TrieNode)t, prefix + c, list));
        // traverse through the Map using lambda notation, and passing the node and char to the recursive call
    }

    /**
     * Memorize the specific candidate word for the specific prefix.
     *
     * @param prefix    the prefix.
     * @param candidate the selected candidate for the prefix.
     */
    @Override
    public void pickCandidate(String prefix, String candidate) {
        String prefix_trimmed = prefix.trim(); // trim out the white space
        List<String> picked_list = new ArrayList<>();
        if (find(prefix_trimmed) == null) {
            put(prefix_trimmed, null);
        }
        TrieNode node = find(prefix_trimmed);
        if (node.getValue() == null) {
            picked_list.add(0, candidate);
            node.setValue(picked_list);
        } else {
            ((List)node.getValue()).add(0, candidate);
        }

        // TODO remove the duplicates, how to match the pick_list to the prefix?
        // some checking, if (... == prefix) then...
    }

}
