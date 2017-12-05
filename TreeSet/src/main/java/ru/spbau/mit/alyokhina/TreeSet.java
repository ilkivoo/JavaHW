package ru.spbau.mit.alyokhina;

import org.jetbrains.annotations.NotNull;

import java.util.AbstractSet;
import java.util.Comparator;
import java.util.Iterator;
import java.util.function.Predicate;

/**
 * Implemented interface using a binary search tree. Each item is stored in a single copy
 * @param <E> type of data that is stored in the set
 */
public class TreeSet<E> extends BinarySearchTree<E> implements TreeSetInterface<E> {
    /**
     * Constructor
     */
    public TreeSet() {
        super();
    }

    /**
     * Constructor
     * @param newCmp comparator with the help of which a tree will be built
     */
    public TreeSet(@NotNull Comparator<E> newCmp) {
        super(newCmp);
    }

    /**
     * Find  minimum element in the set
     * @return the minimum element in the set
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
     * Find  maximum element in the set
     * @return the maximum element in the set
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
     * @return element less than passed as a parameter
     */
    public E lower(@NotNull E e) {
        Node ans = findTheNearestItemGoLeft(e, x1 -> (x1 < 0));
        return (ans == null) ? null : ans.value;
    }

    /**
     * Find element less or equal than passed as a parameter
     * @return element less than passed as a parameter
     */
    public E floor(@NotNull E e) {
        Node ans = findTheNearestItemGoLeft(e, x1 -> (x1 <= 0));
        return (ans == null) ? null : ans.value;
    }

    /**
     * Find element greater than passed as a parameter
     * @return element greater than passed as a parameter
     */
    public E ceiling(@NotNull E e) {
        Node ans = findTheNearestItemGoRight(e, x1 -> (x1 > 0));
        return (ans == null) ? null : ans.value;
    }

    /**
     * Find element greater or equal than passed as a parameter
     * @return element greater or equal than passed as a parameter
     */
    public E higher(@NotNull E e) {
        Node ans = findTheNearestItemGoRight(e, x1 -> (x1 >= 0));
        return (ans == null) ? null : ans.value;
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

    /** Find an element that is less than or equal to a given and satisfies the predicate */
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


    /** Find an element that is greater than or equal to a given and satisfies the predicate */
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
                curNode = findTheNearestItemGoLeft(curNode.value, x1 -> (x1 < 0));
                return answer;
            }


            /**
             * @throws UnsupportedOperationException if there is a call
             */
            @Override
            public void remove() throws UnsupportedOperationException {
                throw new UnsupportedOperationException("call remove for TreeDescendingSetIterator");
            }
        }
    }
}
