package ru.spbau.mit.alyokhina;


import java.util.Comparator;
import java.util.Iterator;

/**
 * Priority Queue
 *
 * @param <E> type stored value
 */
public class PQueue<E> {
    /**
     * number last added element
     */
    private Integer index = 0;

    /**
     * TreeSet which store elements
     */
    private TreeSet<Pair<E>> myTreeSet;


    /**
     * Store element and index
     *
     * @param <T>
     */
    private class Pair<T> {
        Integer i;
        T e;

        public Pair(T e1, Integer i1) {
            e = e1;
            i = i1;
        }

        /**
         * Return value
         */
        public T getKey() {
            return e;
        }

        /**
         * Return index
         */
        public Integer getValue() {
            return i;
        }
    }


    /**
     * Create treeSet with comparator, which compare elements type of E and then comparing their index
     */
    public PQueue() {
        myTreeSet = new TreeSet<>(new Comparator<Pair<E>>() {
            @Override
            public int compare(Pair<E> e1, Pair<E> e2) {
                if (((Comparable<E>) e1.getKey()).compareTo(e2.getKey()) == 0) {
                    return e1.getValue().compareTo(e2.getValue());
                } else {
                    return ((Comparable<E>) e1.getKey()).compareTo(e2.getKey());
                }
            }
        });
    }

    /**
     * Create treeSet with comparator, which compare elements type of E  and then comparing their index
     *
     * @param cmp compare elements type of E
     */
    public PQueue(Comparator<? super E> cmp) {
        myTreeSet = new TreeSet<>(new Comparator<Pair<E>>() {
            @Override
            public int compare(Pair<E> e1, Pair<E> e2) {
                if (cmp.compare(e1.getKey(), e2.getKey()) == 0) {
                    return e1.getValue().compareTo(e2.getValue());
                } else {
                    return cmp.compare(e1.getKey(), e2.getKey());
                }
            }
        });
    }

    /**
     * add element in PriorityQueue
     *
     * @param e this element will be added
     * @return true, if successfully added, else - false
     */
    public boolean add(Object e) {
        E newE = (E) e;
        Pair<E> newPair = new Pair<>(newE, index);
        index++;
        return myTreeSet.add(newPair);
    }

    /**
     * Returns the element with the highest priority and removes it from the queue
     */
    public E poll() {
        Pair<E> newPair = myTreeSet.first();
        myTreeSet.remove(newPair);
        return newPair.getKey();
    }

    /**
     * Clear priority queue
     */
    public void clear() {
        myTreeSet.clear();
    }

    /** Size priority queue */
    public int size() {
        return myTreeSet.size();
    }

    /**
     * Iterator
     *
     * @return an iterator, matching elements in order of priority
     */
    public Iterator<E> iterator() {
        return new PQIterator();
    }

    /**
     * Class for Iterator
     * We store iterator of TreeSet and return only key
     */
    private class PQIterator implements Iterator<E> {
        Iterator<Pair<E>> it;

        public PQIterator() {
            it = myTreeSet.iterator();
        }

        @Override
        public boolean hasNext() {
            return it.hasNext();
        }

        @Override
        public E next() {
            E returnValue = it.next().getKey();
            return returnValue;
        }
    }
}
