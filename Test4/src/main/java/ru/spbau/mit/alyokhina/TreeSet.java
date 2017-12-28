package ru.spbau.mit.alyokhina;

import org.jetbrains.annotations.NotNull;

import java.util.AbstractSet;
import java.util.Comparator;
import java.util.Iterator;

/**
 * Implemented interface using a binary search tree. Each item is stored in a single copy
 *
 * @param <E> type of data that is stored in the set
 */
public class TreeSet<E> extends AbstractSet<E> implements TreeSetInterface<E> {
    private BinarySearchTree<E> tree;

    /**
     * Constructor
     */
    public TreeSet() {
        tree = new BinarySearchTree<>();
    }

    /**
     * Iterator
     *
     * @return iterator over the elements in this TreeSet
     */
    @Override
    public Iterator<E> iterator() {
        return tree.iterator();
    }

    /**
     * Constructor
     *
     * @param newCmp comparator with the help of which a tree will be built
     */
    public TreeSet(@NotNull Comparator<E> newCmp) {
        tree = new BinarySearchTree<>(newCmp);
    }

    /**
     * Iterator in descending order
     *
     * @return the iterator by elements in reverse order
     */
    @Override
    public Iterator<E> descendingIterator() {
        return tree.descendingIterator();
    }

    @Override
    public void clear() {
        tree.clear();
    }
    /**
     * Set in reverse order
     *
     * @return Set in reverse order
     */
    @Override
    public TreeSetInterface<E> descendingSet() {
        return tree.descendingSet();
    }

    /**
     * Find  minimum element in the set
     *
     * @return the minimum element in the set
     */
    @Override
    public E first() {
        return tree.first();
    }

    /**
     * Find  maximum element in the set
     *
     * @return the maximum element in the set
     */
    @Override
    public E last() {
        return tree.last();
    }

    /**
     * Find element less than passed as a parameter
     *
     * @return element less than passed as a parameter
     */
    @Override
    public E lower(E e) {
        return tree.lower(e);
    }

    /**
     * Find element less or equal than passed as a parameter
     *
     * @return element less than passed as a parameter
     */
    @Override
    public E floor(E e) {
        return tree.floor(e);
    }

    /**
     * Find element greater than passed as a parameter
     *
     * @return element greater than passed as a parameter
     */
    @Override
    public E ceiling(E e) {
        return tree.ceiling(e);
    }

    /**
     * Find element greater or equal than passed as a parameter
     *
     * @return element greater or equal than passed as a parameter
     */
    @Override
    public E higher(E e) {
        return tree.higher(e);
    }

    /**
     * Size
     *
     * @return size TreeSet
     */
    @Override
    public int size() {
        return tree.size();
    }

    /**
     * Checks whether an element is contained in TreeSet
     *
     * @param o element, for which there will be a check
     * @return true, If this element was contained in the TreeSet, else - false
     */
    @Override
    public boolean contains(Object o) {
        return tree.contains(o);
    }

    /**
     * Add element in TreeSet
     *
     * @param e item to be added
     * @return true, if this element has not yet been, else - false
     */
    @Override
    public boolean add(E e) {
        return tree.add(e);
    }

    /**
     * Delete an item
     *
     * @param o The item to be deleted
     * @return false, if this element was not contained in the TreeSet, else - true;
     */
    @Override
    public boolean remove(Object o) {
        return tree.remove(o);
    }


}
