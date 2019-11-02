package edu.emory.cs.utils.trie;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Jinho D. Choi ({@code jinho.choi@emory.edu})
 */
public class TrieTest {
    @Test
    public void test() {
        final String[] vocab = {"she", "shell", "sell", "selling", "shore", "woman", "women", "won"};
        Trie<Integer> trie = new Trie<>();
        int i, len = vocab.length;

        for (i = 0; i < len; i++)
            trie.put(vocab[i], i);

        for (i = 0; i < len; i++)
            assertEquals(i, trie.get(vocab[i]).intValue());

        assertNull(trie.get("wom"));
        assertNull(trie.get("wons"));

        assertFalse(trie.remove("wom"));
        assertEquals(5, trie.get("woman").intValue());

        assertFalse(trie.remove("wons"));
        assertEquals(7, trie.get("won").intValue());

        trie.remove("won");
        assertNull(trie.get("won"));
        assertEquals(5, trie.get("woman").intValue());

        trie.remove("selling");
        assertNull(trie.get("selling"));
        assertEquals(2, trie.get("sell").intValue());

        trie.remove("she");
        assertNull(trie.get("she"));
        assertEquals(1, trie.get("shell").intValue());
        assertEquals(4, trie.get("shore").intValue());

        System.out.println("sell".compareTo("selling"));
    }
}