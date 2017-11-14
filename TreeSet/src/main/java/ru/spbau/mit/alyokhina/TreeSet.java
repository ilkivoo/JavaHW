package ru.spbau.mit.alyokhina;

import org.jetbrains.annotations.NotNull;

import java.util.AbstractSet;
import java.util.Comparator;
import java.util.Iterator;

/**
 * Implemented interface using a binary search tree. Each item is stored in a single copy
 * @param <E> type of data that is stored in the set
 */
public class TreeSet<E> extends AbstractSet<E> implements TreeSetInterface<E> {
    /**  Root tree */
    private Node root = null;

    /** Size tree */
    private int size = 0;

    /** Comparator, with which you can compare elements of type E */
    private Comparator<E> cmp;

    /**
     * Function that compares two elements of type E
     * @param e1,e2  we want to compare these elements
     * @return  number is greater than 0 if e1 > e2, equal to 0, if e1 = e2, is less than 0, if e1 < e2
     */
    private int compare(E e1, E e2) {
        if (cmp == null) {
            return ((Comparable<E>) e1).compareTo(e2);
        } else {
            return cmp.compare(e1, e2);
        }
    }

    /** Constructor */
    public TreeSet() {
        cmp = null;
    }

    /**
     * Constructir
     * @param newCmp comparator with the help of which a tree will be built
     */
    public TreeSet(@NotNull Comparator<E> newCmp) {
        cmp = newCmp;
    }

    /**
     * Add element in set
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
                int comparisonResult = compare(cur.value, e);
                pred = cur;
                cur = (comparisonResult > 0) ? cur.left : cur.right;
            }


            if (compare(pred.value, e) > 0) {
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
     * Checks whether an element is contained in a set
     * @param o  element, for which there will be a check
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
                int comparisonResult = compare(cur.value, e);
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
                int comparisonResult = compare(cur.value, e);
                if (comparisonResult == 0) {
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
                            if (compare(cur.value, pred.value) < 0) {
                                pred.left = null;
                            } else {
                                pred.right = null;
                            }
                        }
                    }
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
     * @return iterator over the elements in this set
     */
    @Override
    public Iterator<E> iterator() {
        return new TreeSetIterator();
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
     * Iterator in descending order
     * @return the iterator by elements in reverse order
     */
    @Override
    public Iterator<E> descendingIterator() {
        return descendingSet().iterator();
    }

    /**
     * Set in reverse order
     * @return Set in reverse order
     */
    @Override
    public TreeSetInterface<E> descendingSet() {
        return new TreeDescendingSet();
    }

    /**
     * Find  minimum element in the set
     * @return the minimum element in the set
     */
    @Override
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
     * Find  maximum element in the set
     * @return the maximum element in the set
     */
    @Override
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
     * @return element less than passed as a parameter
     */
    @Override
    public E lower(@NotNull E e) {
        if (root == null) {
            return null;
        } else {
            Node cur = root;
            E answer = null;
            while (cur != null) {
                if (compare(cur.value, e) < 0) {
                    answer = cur.value;
                    cur = cur.right;
                } else {
                    cur = cur.left;
                }
            }
            return answer;
        }
    }

    /**
     * Find element less or equal than passed as a parameter
     * @return element less than passed as a parameter
     */
    @Override
    public E floor(@NotNull E e) {
        if (root == null) {
            return null;
        } else {
            Node cur = root;
            E answer = null;
            while (cur != null) {
                if (compare(cur.value, e) <= 0) {
                    answer = cur.value;
                    cur = cur.right;
                } else {
                    cur = cur.left;
                }
            }
            return answer;
        }
    }

    /**
     * Find element greater than passed as a parameter
     * @return element greater than passed as a parameter
     */
    @Override
    public E ceiling(@NotNull E e) {
        if (root == null) {
            return null;
        } else {
            Node cur = root;
            E answer = null;
            while (cur != null) {
                if (compare(cur.value, e) > 0) {
                    answer = cur.value;
                    cur = cur.left;
                } else {
                    cur = cur.right;
                }
            }
            return answer;
        }
    }

    /**
     * Find element greater or equal than passed as a parameter
     * @return element greater or equal than passed as a parameter
     */
    @Override
    public E higher(@NotNull E e) {
        if (root == null) {
            return null;
        } else {
            Node cur = root;
            E answer = null;
            while (cur != null) {
                if (compare(cur.value, e) >= 0) {
                    answer = cur.value;
                    cur = cur.left;
                } else {
                    cur = cur.right;
                }
            }
            return answer;
        }
    }

    /** TreeSet Node */
    private class Node {
        /** left - left son in tree
         *  right - right son in tree
         *  parent - predecessor in tree
         */
        private Node left, right, parent;
        E value;

        /**
         * Constructor Node
         * @param e this element will be stored in the set
         */
        private Node(E e) {
            value = e;
            left = null;
            right = null;
            parent = null;
        }
    }

    /**
     * Iterator for TreeSet
     */
    private class TreeSetIterator implements Iterator<E> {
        private Node curNode = null;

        /** Constructor */
        public TreeSetIterator() {
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
                    while (pred != null && compare(curNode.value, pred.value) > 0) {
                        curNode = pred;
                        pred = pred.parent;
                    }
                    curNode = pred;
                }
                return answer;
            }
        }

        @Override
        public void remove() {

        }
    }

    /**
     * The set of elements in the reverse order
     * The description for all functions and classes of this class is the same as the TreeSet in reverse order only
     */
    private class TreeDescendingSet extends AbstractSet<E> implements TreeSetInterface<E> {

        @NotNull
        public Iterator<E> iterator() {
            return new TreeDescendingSetIterator();
        }

        @Override
        public int size() {
            return size;
        }

        @Override
        public Iterator<E> descendingIterator() {
            return TreeSet.this.iterator();
        }

        @Override
        public TreeSetInterface<E> descendingSet() {
            return TreeSet.this;
        }

        @Override
        public E first() {
            return TreeSet.this.last();
        }

        @Override
        public E last() {
            return TreeSet.this.first();
        }

        @Override
        public E lower(@NotNull E e) {
            return TreeSet.this.higher(e);
        }

        @Override
        public E floor(@NotNull E e) {
            return TreeSet.this.ceiling(e);
        }

        @Override
        public E ceiling(@NotNull E e) {
            return TreeSet.this.floor(e);
        }

        @Override
        public E higher(E e) {
            return TreeSet.this.lower(e);
        }

        private class TreeDescendingSetIterator implements Iterator<E> {
            private Node curNode = null;

            public TreeDescendingSetIterator() {
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
                if (curNode.left != null) {
                    curNode = curNode.left;
                    while (curNode.right != null) {
                        curNode = curNode.right;
                    }
                } else {
                    Node pred = curNode.parent;
                    while (pred != null && compare(curNode.value, pred.value) < 0) {
                        curNode = pred;
                        pred = pred.parent;
                    }
                    curNode = pred;
                }
                return answer;
            }

            @Override
            public void remove() {
            }
        }
    }
}
