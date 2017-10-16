package ru.spbau.alyokhina.trie;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/** Trie. A data structure that stores a set of strings representing a tree with symbols on the edges */
public class Trie implements Serializable, IOByte {

    /** Class for vertex. It stores map (symbol on edge, adjacent vertex) and number of strings that ended in an edge leading to this vertex */
    private class Node implements Serializable {
        private Map<Character, Node> childrenInTrie = new HashMap<Character, Node>();
        private int numberOfWordsEndingInVertex = 0;
    }

    /** Root tree*/
    private Node root = new Node();

    /** Size of trie */
    private int size = 0;

    /**
     * Checks for a row in the trie
     * @param element string we want to check
     * Time O(|element|)
     * @return true, if string fount in trie, else false
     */
    public boolean contains(String element) {
        Node vertex = root;
        for (char c : element.toCharArray()) {
            if (vertex.childrenInTrie.containsKey(c) && (vertex.childrenInTrie.size() + vertex.numberOfWordsEndingInVertex > 0)) {
                vertex = vertex.childrenInTrie.get(c);
            }
            else {
                return false;
            }
        }
        return (vertex.numberOfWordsEndingInVertex > 0);
    }

    /**
     * Add word in trie.
     * @param element string we want to add
     * Time O(|element|)
     * @return true, if this word didn't exist before, else false
     */
    public boolean add(String element) {
        if (contains(element)) {
            return false;
        }
        size++;
        Node vertex = root;
        for (char c : element.toCharArray()) {
            if (!vertex.childrenInTrie.containsKey(c)) {
                vertex.childrenInTrie.put(c, new Node());
            }
            vertex = vertex.childrenInTrie.get(c);
        }
        vertex.numberOfWordsEndingInVertex++;
        return true;
    }

    /**
     * Delete word from trie.
     * @param element string we want to delete
     * Time O(|element|)
     * @return false, if this word doesn't exist, else false
     */
    public boolean remove(String element) {
        if (!contains(element)) {
            return false;
        }
        size--;
        Node vertex = root;
        for (char c : element.toCharArray()) {
            Node vertexTmp = vertex.childrenInTrie.get(c);
            vertex.childrenInTrie.remove(c);
            vertex = vertexTmp;
        }
        vertex.numberOfWordsEndingInVertex--;
        return true;
    }

    /**
     * Number of words in trie
     * Time О(1)
     */
    public int size() {
        return size;
    }

    /**
     * Number of words start with this prefix
     * @param prefix on this prefix will be found the number of words
     * Time O(|element|)
     */
    public int howManyStartsWithPrefix(String prefix) {
        if (!contains(prefix)) {
            return 0;
        }
        Node vertex = root;
        for (char c : prefix.toCharArray()) {
            vertex = vertex.childrenInTrie.get(c);
        }
        return vertex.childrenInTrie.size() + vertex.numberOfWordsEndingInVertex;
    }

    /**
     * Serializes the trie into a given stream
     * @param out stream for write
     */
    public void serialize(OutputStream out) throws IOException {
        ObjectOutputStream objOutStream = new ObjectOutputStream(out);
        objOutStream.writeObject(this);
    }

    /**
     * Deserializes from a given stream and save them in this trie
     * @param in stream for read
     */
    public void deserialize(InputStream in) throws IOException, ClassNotFoundException {
        ObjectInputStream objInStream = new ObjectInputStream(new BufferedInputStream(in));
        Trie trieNew = ((Trie) objInStream.readObject());
        root = trieNew.root;
        size = trieNew.size;
    }
}