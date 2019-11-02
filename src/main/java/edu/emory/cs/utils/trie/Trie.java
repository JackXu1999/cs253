package edu.emory.cs.utils.trie;

public class Trie<T> {
    private TrieNode<T> root;

    public Trie() {
        root = new TrieNode<>(null, (char) 0);
    }

    public TrieNode<T> getRoot() {
        return root;
    }

    public T get(String key) {
        TrieNode<T> node = find(key);
        return (node != null && node.isEndState()) ? node.getValue() : null;
    }

    public boolean contains(String key) {
        return get(key) != null;
    }

    /**
     * @return the previously inserted value for the specific key if exists; otherwise, {@code null}.
     */
    public T put(String key, T value) {
        char[] array = key.toCharArray();
        TrieNode<T> node = root;

        for (int i = 0; i < key.length(); i++)
            node = node.addChild(array[i]);

        node.setEndState(true);
        return node.setValue(value);
    }

    /**
     * @return the node with the specific key if exists; otherwise, {@code null}.
     */
    public TrieNode<T> find(String key) {
        char[] array = key.toCharArray();
        TrieNode<T> node = root;

        for (int i = 0; i < key.length(); i++) {
            node = node.getChild(array[i]);
            if (node == null) return null;
        }

        return node;
    }

    /**
     * @return {@code true} if a node with the specific key if exists; otherwise, {@code false}.
     */
    public boolean remove(String key) {
        TrieNode<T> node = find(key);

        if (node == null || !node.isEndState())
            return false;

        if (node.hasChildren()) {
            node.setEndState(false);
            return true;
        }

        TrieNode<T> parent = node.getParent();

        while (parent != null) {
            parent.removeChild(node.getKey());

            if (parent.hasChildren() || parent.isEndState())
                break;
            else {
                node = parent;
                parent = parent.getParent();
            }
        }

        return true;
    }
}