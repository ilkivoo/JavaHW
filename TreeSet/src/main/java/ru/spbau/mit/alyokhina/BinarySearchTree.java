package ru.spbau.mit.alyokhina;

import org.jetbrains.annotations.NotNull;

import java.util.AbstractSet;
import java.util.Comparator;
import java.util.Iterator;
import java.util.function.Predicate;

public class BinarySearchTree<E> extends AbstractSet<E> implements TreeSetInterface<E> {
    /**
     * Root tree
     */
    private Node root = null;

    /**
     * Size tree
     */
    private int size = 0;

    /**
     * Comparator, with which you can compare elements of type E
     */
    private Comparator<E> cmp;

    /**
     * Constructor
     */
    public BinarySearchTree() {
        cmp = (e1, e2) -> ((Comparable<E>) e1).compareTo(e2);
    }

    /**
     * Constructor
     *
     * @param newCmp comparator with the help of which a tree will be built
     */
    public BinarySearchTree(@NotNull Comparator<E> newCmp) {
        cmp = newCmp;
    }

    /**
     * Add element in BinarySearchTree
     *
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
     *
     * @param o element, for which there will be a check
     * @return true, If this element was contained in the tree, else - false
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
     *
     * @param o The item to be deleted
     * @return false, if this element was not contained in the tree, else - true;
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
     *
     * @return iterator over the elements in this BinarySearchTree
     */
    @Override
    public Iterator<E> iterator() {
        return new BinarySearchTreeIterator();
    }

    /**
     * Iterator in descending order
     *
     * @return the iterator by elements in reverse order
     */
    @Override
    public Iterator<E> descendingIterator() {
        return descendingSet().iterator();
    }

    /**
     * Tree in reverse order
     *
     * @return Tree in reverse order
     */
    @Override
    public TreeSetInterface<E> descendingSet() {
        return new DescendingBinarySearchTree();
    }

    /**
     * Size
     *
     * @return size BinarySearchTree
     */
    @Override
    public int size() {
        return size;
    }


    /**
     * Find  minimum element in the tree
     *
     * @return the minimum element in the tree
     */
    public E first() {
        if (root == null) {
            return null;
        } else {
            Node cur = root;
            while (cur.left != null) {
                cur = cur.left;
            }
            return cur.value;
        }
    }

    /**
     * Find  maximum element in the tree
     *
     * @return the maximum element in the tree
     */
    public E last() {
        if (root == null) {
            return null;
        } else {
            Node cur = root;
            while (cur.right != null) {
                cur = cur.right;
            }
            return cur.value;
        }
    }

    /**
     * Find element less than passed as a parameter
     *
     * @return element less than passed as a parameter
     */
    public E lower(@NotNull E e) {
        Node ans = findTheNearestItemGoLeft(e, x1 -> (x1 < 0));
        return (ans == null) ? null : ans.value;
    }

    /**
     * Find element less or equal than passed as a parameter
     *
     * @return element less than passed as a parameter
     */
    public E floor(@NotNull E e) {
        Node ans = findTheNearestItemGoLeft(e, x1 -> (x1 <= 0));
        return (ans == null) ? null : ans.value;
    }

    /**
     * Find element greater than passed as a parameter
     *
     * @return element greater than passed as a parameter
     */
    public E ceiling(@NotNull E e) {
        Node ans = findTheNearestItemGoRight(e, x1 -> (x1 > 0));
        return (ans == null) ? null : ans.value;
    }

    /**
     * Find element greater or equal than passed as a parameter
     *
     * @return element greater or equal than passed as a parameter
     */
    public E higher(@NotNull E e) {
        Node ans = findTheNearestItemGoRight(e, x1 -> (x1 >= 0));
        return (ans == null) ? null : ans.value;
    }

    /**
     * Delete Node from BinarySearchTree
     *
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

    /**
     * Iterator for BinarySearchTree
     */
    private class BinarySearchTreeIterator implements Iterator<E> {
        private Node curNode = null;

        /**
         * Constructor
         */
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
         *
         * @return true - if exist, else - false
         */
        @Override
        public boolean hasNext() {
            return (curNode != null);
        }

        /**
         * Find next element
         *
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

        /**
         * @throws UnsupportedOperationException if there is a call
         */
        @Override
        public void remove() throws UnsupportedOperationException {
            throw new UnsupportedOperationException("call remove for TreeSetIterator");
        }
    }


    /**
     * Find an element that is less than or equal to a given and satisfies the predicate
     */
    private Node findTheNearestItemGoLeft(@NotNull E e, Predicate<Integer> predicate) {
        if (root == null) {
            return null;
        } else {
            Node cur = root;
            Node answer = null;
            while (cur != null) {
                if (predicate.test(cmp.compare(cur.value, e))) {
                    answer = cur;
                    cur = cur.right;
                } else {
                    cur = cur.left;
                }
            }
            return answer;
        }
    }


    /**
     * Find an element that is greater than or equal to a given and satisfies the predicate
     */
    private Node findTheNearestItemGoRight(@NotNull E e, Predicate<Integer> predicate) {
        if (root == null) {
            return null;
        } else {
            Node cur = root;
            Node answer = null;
            while (cur != null) {
                if (predicate.test(cmp.compare(cur.value, e))) {
                    answer = cur;
                    cur = cur.left;
                } else {
                    cur = cur.right;
                }
            }
            return answer;
        }
    }


    /**
     * Node for BinarySearchTee
     */
    private class Node {
        /**
         * Left son in tree
         */
        private Node left = null;
        /**
         * Right - right son in tree
         */
        private Node right = null;
        /**
         * Parent - predecessor in tree
         */
        private Node parent = null;
        /**
         * The value stored in the node
         */
        private E value;

        /**
         * Constructor Node
         *
         * @param e this element will be stored in the tree
         */
        private Node(E e) {
            value = e;
        }
    }

    /**
     * The tree of elements in the reverse order
     * The description for all functions and classes of this class is the same as the BinarySearchTree in reverse order only
     */
    private class DescendingBinarySearchTree extends AbstractSet<E> implements TreeSetInterface<E> {

        @NotNull
        public Iterator<E> iterator() {
            return new DescendingBinarySearchTreeIterator();
        }

        @Override
        public int size() {
            return size;
        }

        @Override
        public Iterator<E> descendingIterator() {
            return BinarySearchTree.this.iterator();
        }

        @Override
        public TreeSetInterface<E> descendingSet() {
            return BinarySearchTree.this;
        }

        @Override
        public E first() {
            return BinarySearchTree.this.last();
        }

        @Override
        public E last() {
            return BinarySearchTree.this.first();
        }

        @Override
        public E lower(@NotNull E e) {
            return BinarySearchTree.this.higher(e);
        }

        @Override
        public E floor(@NotNull E e) {
            return BinarySearchTree.this.ceiling(e);
        }

        @Override
        public E ceiling(@NotNull E e) {
            return BinarySearchTree.this.floor(e);
        }

        @Override
        public E higher(E e) {
            return BinarySearchTree.this.lower(e);
        }

        private class DescendingBinarySearchTreeIterator implements Iterator<E> {
            private Node curNode = null;

            public DescendingBinarySearchTreeIterator() {
                Node cur = root;
                Node pred = null;
                while (cur != null) {
                    pred = cur;
                    cur = cur.right;
                }
                curNode = pred;
            }

            @Override
            public boolean hasNext() {
                return curNode != null;
            }

            @Override
            public E next() {
                if (curNode == null) {
                    return null;
                }
                E answer = curNode.value;
                curNode = findTheNearestItemGoLeft(curNode.value, x1 -> (x1 < 0));
                return answer;
            }


            /**
             * @throws UnsupportedOperationException if there is a call
             */
            @Override
            public void remove() throws UnsupportedOperationException {
                throw new UnsupportedOperationException("call remove for DescendingBinarySearchTreeIterator");
            }
        }
    }
}
