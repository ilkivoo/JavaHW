/**
 * node implementation for list
 * @author Alekhina Olga
 * created 08.09.17
 */
package ru.spbau.MIT.Alyokhina.Node;

public class Node {
    public String value;
    public String key;
    public Node next;
    public Node prev;

    public Node(String newKey, String newValue, Node newNext, Node newPrev) {
        key = newKey;
        value = newValue;
        next = newNext;
        prev = newPrev;
    }
}
