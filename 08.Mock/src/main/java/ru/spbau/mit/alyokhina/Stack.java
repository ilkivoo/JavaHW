package ru.spbau.mit.alyokhina;

import org.jetbrains.annotations.NotNull;

/**
 * Stack for Calculator
 * @param <T> type of stored items
 */
public class Stack<T> {
    /** First element in Stack */
    Node head = null;

    /**
     * Add element in Stack
     * @param newValue element, which will be add
     */
    public void push(@NotNull T newValue) {
        Node newHead = new Node();
        newHead.value = newValue;
        newHead.next = head;
        head = newHead;
    }

    /**
     * Delete last added item
     * @return item that was deleted or null, if there was no such element
     */
    public T pop() {
        if (head != null) {
            T returnValue = head.value;
            head = head.next;
            return returnValue;
        } else
            return null;
    }

    /**
     * last added item
     * @return last added item or null if stack is empty
     */
    public T peek() {
        if (head != null)
            return head.value;
        else
            return null;
    }

    /**
     * Checking the existence of elements in stack
     * @return tru, if stack is empty, else - false
     */
    public boolean isEmpty() {
        return (head == null);
    }

    /**
     * Moves the stack to a string, separated by a space
     * @return  resulting string
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node v = head;
        while (v != null) {
            sb.append((v.value).toString());
            sb.append(" ");
            v = v.next;
        }
        return sb.toString();
    }

    /** Class for Stack. Element of Stack */
    private class Node {
        private Node next;
        private T value;
    }
}
