package ru.spbau.mit.alyokhina;

import org.jetbrains.annotations.NotNull;

import java.util.AbstractSet;
import java.util.Comparator;
import java.util.Iterator;

public class BinarySearchTree<E> extends AbstractSet<E> {
    /**
     * Root tree
     */
    protected Node root = null;

    /**
     * Size tree
     */
    protected int size = 0;

    /**
     * Comparator, with which you can compare elements of type E
     */
    protected Comparator<E> cmp;

    /** Constructor */
    public BinarySearchTree() {
        cmp = (e1, e2) -> ((Comparable<E>) e1).compareTo(e2);
    }

    /**
     * Constructor
     * @param newCmp comparator with the help of which a tree will be built
     */
    public BinarySearchTree(@NotNull Comparator<E> newCmp) {
        cmp = newCmp;
    }

    /**
     * Add element in BinarySearchTree
     * @param e item to be added
     * @return true, if this element has not yet been, else - false
     */
    @Override
    public boolean add(E e) {
        if (root == null) {
            root = new Node(e);
            size++;
            return true;
        } else {
            if (contains(e)) {
                return false;
            }
            Node cur = root;
            Node pred = null;
            while (cur != null) {
                int comparisonResult = cmp.compare(cur.value, e);
                pred = cur;
                cur = (comparisonResult > 0) ? cur.left : cur.right;
            }


            if (cmp.compare(pred.value, e) > 0) {
                pred.left = new Node(e);
                (pred.left).parent = pred;
            } else {
                pred.right = new Node(e);
                (pred.right).parent = pred;
            }
            size++;
            return true;
        }
    }

    /**
     * Checks whether an element is contained in BinarySearchTree
     * @param o element, for which there will be a check
     * @return true, If this element was contained in the set, else - false
     */
    @Override
    public boolean contains(Object o) {
        E e = (E) o;
        if (root == null) {
            return false;
        } else {
            Node cur = root;
            while (cur != null) {
                int comparisonResult = cmp.compare(cur.value, e);
                if (comparisonResult == 0) {
                    return true;
                } else {
                    cur = (comparisonResult > 0) ? cur.left : cur.right;
                }
            }
            return false;
        }
    }

    /**
     * Delete an item
     * @param o The item to be deleted
     * @return false, if this element was not contained in the set, else - true;
     */
    @Override
    public boolean remove(Object o) {
        E e = (E) o;
        if (root == null) {
            return false;
        } else {
            Node cur = root;
            while (cur != null) {
                int comparisonResult = cmp.compare(cur.value, e);
                if (comparisonResult == 0) {
                    deleteNode(cur);
                    return true;
                } else {
                    cur = (comparisonResult > 0) ? cur.left : cur.right;
                }
            }
            return false;
        }
    }

    /**
     * Iterator
     * @return iterator over the elements in this BinarySearchTree
     */
    @Override
    public Iterator<E> iterator() {
        return new BinarySearchTreeIterator();
    }

    /**
     * Size
     * @return size TreeSet
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Delete Node from BinarySearchTree
     * @param cur Node that will be deleted
     */
    private void deleteNode(Node cur) {
        size--;
        Node tmp = cur;
        Node pred = cur.parent;
        if (tmp.left == null || tmp.right == null) {
            tmp = (tmp.left == null) ? tmp.right : tmp.left;
        } else {
            tmp = tmp.right;
            while (tmp.left != null) {
                tmp = tmp.left;
            }
        }
        if (tmp != null) {
            cur.value = tmp.value;
            (tmp.parent).left = null;
        } else {
            if (pred != null) {
                if (cmp.compare(cur.value, pred.value) < 0) {
                    pred.left = null;
                } else {
                    pred.right = null;
                }
            }
        }
    }

    /** Iterator for BinarySearchTree */
    private class BinarySearchTreeIterator implements Iterator<E> {
        private Node curNode = null;

        /** Constructor */
        public BinarySearchTreeIterator() {
            Node cur = root;
            Node pred = null;
            while (cur != null) {
                pred = cur;
                cur = cur.left;
            }
            curNode = pred;
        }

        /**
         * Checks for availability next element
         * @return true - if exist, else - false
         */
        @Override
        public boolean hasNext() {
            return (curNode != null);
        }

        /**
         * Find next element
         * @return next element
         */
        @Override
        public E next() {
            if (curNode == null)
                return null;
            else {
                E answer = curNode.value;
                if (curNode.right != null) {
                    curNode = curNode.right;
                    while (curNode.left != null) {
                        curNode = curNode.left;
                    }
                } else {
                    Node pred = curNode.parent;
                    while (pred != null && cmp.compare(curNode.value, pred.value) > 0) {
                        curNode = pred;
                        pred = pred.parent;
                    }
                    curNode = pred;
                }
                return answer;
            }
        }

        /** @throws UnsupportedOperationException if there is a call */
        @Override
        public void remove() throws UnsupportedOperationException {
            throw new UnsupportedOperationException("call remove for TreeSetIterator");
        }
    }

    /** Node for BinarySearchTee */
    protected class Node {
        /** Left son in tree */
        protected Node left = null;
        /**  Right - right son in tree */
        protected Node right = null;
        /** Parent - predecessor in tree */
        protected Node parent = null;
        /** The value stored in the node */
        protected E value;

        /**
         * Constructor Node
         * @param e this element will be stored in the set
         */
        protected Node(E e) {
            value = e;
        }
    }
}
