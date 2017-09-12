/**
 * Hash-Table
 * @author Alekhina Olga
 * created 08.09.17
 */


package ru.spbau.MIT.Alyokhina.HashTable;

import ru.spbau.MIT.Alyokhina.List.List;
import ru.spbau.MIT.Alyokhina.Node.Node;

public class HashTable {
    private int initialSize;
    private List[] arrayOfLists;
    private int numberOfKeys;

    public HashTable() {
        initialSize = 32;
        arrayOfLists = new List[initialSize];
        for (int i = 0; i < initialSize; i++) {
            arrayOfLists[i] = new List();
        }
    }
    /**
     *rebuild Hash-table. Increases the number of lists
     */
    private void rebuild() {
        initialSize *= 2;
        List[] newArrayOfLists = new List[initialSize];
        for (int i = 0; i < initialSize; i++) {
            newArrayOfLists[i] = new List();
        }
        int newHash;
        for (int i =0; 2 * i < initialSize; i++) {
            Node node = arrayOfLists[i].head;
            while (node != null) {
                newHash = hashFunction(node.key);
                newArrayOfLists[newHash].push_front(node.key, node.value);
                node = node.next;
            }
        }
    }


    /**
     * @param str string for hash-function calculation
     * @return the number corresponding to the string
     */
    private int hashFunction(String str) {
        int s = 0;
        for (int i = 0; i < str.length(); i++) {
            s += str.charAt(i) - '0';
        }
        return s % initialSize;
    }

    /**
     *
     * @return number of keys
     */
    public int size() {
        return numberOfKeys;
    }

    /**
     *  Checks for the presence of an element
     *  @param key verifiable key
     *  @return true if a node with such a key is found in the table, else - false
     */
    public boolean contains(String key) {
        int hash = hashFunction(key);
        return !(arrayOfLists[hash].getNode(key) == null);
    }

    /**
     * @return a value by key
     */
    public String get(String key) {
        int hash = hashFunction(key);
        Node result = arrayOfLists[hash].getNode(key);
        if (result == null)
            return null;
        else
            return result.value;
    }

    /**
     * adds a value. If such a key already existed, then it overwrites
     * @return previous value
     */
    public String put(String key, String value) {
        int hash = hashFunction(key);
        Node nodeWithThisKey = arrayOfLists[hash].getNode(key);
        if (nodeWithThisKey == null) {
            arrayOfLists[hash].push_front(key, value);
            numberOfKeys++;
            if (numberOfKeys == initialSize) {
                rebuild();
            }
            return null;
        } else {
            String result = nodeWithThisKey.value;
            nodeWithThisKey.value = value;
            return result;
        }
    }

    /** remove value by key
     * @return value
     */
    public String remove(String key) {
        int hash = hashFunction(key);
        Node nodeWithThisKey = arrayOfLists[hash].getNode(key);
        if (nodeWithThisKey == null) {
            return null;
        } else {
            String result = nodeWithThisKey.value;
            arrayOfLists[hash].pop(nodeWithThisKey);
            numberOfKeys--;
            return result;
        }

    }

    /**
     * clear hash-table
     */
    public void clear() {
        for (int i = 0; i < initialSize; i++) {
            arrayOfLists[i].clear();
        }
        numberOfKeys = 0;
    }
}