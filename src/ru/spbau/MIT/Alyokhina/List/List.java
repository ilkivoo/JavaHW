/**
 * List implementation for hash-table
 * @author Alekhina Olga
 * created 08.09.17
 */
package ru.spbau.MIT.Alyokhina.List;

import ru.spbau.MIT.Alyokhina.Node.Node;

public class List {
    public Node head;

    public List() {
        head = null;
    }

    /**
     * Add to the beginning
     */
    public void push_front(String key, String value) {
        if (head == null) {
            head = new Node(key, value, null, null);
        } else {
            Node newNode = new Node(key, value, null, null);
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
    }

    /**
     * @param node node to be deleted
     */
    public void pop(Node node) {
        if (node.prev != null) {
            (node.prev).next = node.next;
        }
        if (node.next != null) {
            (node.next).prev = node.prev;
        }
    }

    /**
     * @param str the string by which the value is returned
     * @return node by key
     */
    public Node getNode(String str) {
        Node node = head;
        while (node != null) {
            if (node.key.equals(str)) {
                return node;
            }
            node = node.next;
        }
        return null;
    }

    /**
     * Clear list
     */
    public void clear() {
        head = null;
    }
}
