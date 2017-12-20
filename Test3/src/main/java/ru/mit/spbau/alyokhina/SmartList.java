package ru.mit.spbau.alyokhina;

import java.util.*;

/**
 * If the list is empty, then the value of this field must be null
 * If there is exactly one element in the list, then the link to it must be stored in this field
 * If the list contains 2 to 5 elements, then this field stores an array of size 5, the elements of which are listed on the corresponding list items
 * Otherwise, this field stores a reference to a normal ArrayList, which stores all the elements
 *
 * @param <E> type of element
 */
public class SmartList<E> extends AbstractList<E> implements List<E> {
    private int size = 0;
    private Object data = null;

    public SmartList() {
    }

    ;

    /**
     * Constructor
     *
     * @param collection this element will be add in SmartList
     */
    public SmartList(Collection<E> collection) {
        addAll(collection);
    }

    public int size() {
        return size;
    }

    /**
     * add element in SmartList
     *
     * @param newElement element will be added
     * @return true
     */
    public boolean add(E newElement) {
        if (size == 0) {
            data = newElement;
        } else if (size < 5) {
            if (size == 1) {
                Object[] array = new Object[5];
                array[0] = data;
                data = array;
            }
            ((Object[]) data)[size] = newElement;
        } else {
            if (size == 5) {
                ArrayList<Object> newList = new ArrayList<>();
                Collections.addAll(newList, (Object[]) data);
                data = newList;
            }
            ((ArrayList) data).add(newElement);
        }
        size++;
        return true;
    }

    /**
     * remove this element
     *
     * @param o
     * @return true if was removed, else - false
     */
    public boolean remove(Object o) {
        if (size == 0) {
            return false;
        } else if (size == 1) {
            if (data.equals(o)) {
                size = 0;
                data = null;
                return true;
            } else {
                return false;
            }
        } else if (size <= 5) {
            Object[] array = (Object[]) data;
            int index = -1;
            for (int i = 0; i < array.length; i++) {
                if (array[i].equals(o)) {
                    index = i;
                }
            }
            if (index == -1) {
                return false;
            } else {
                if (size == 2) {
                    data = array[1 - index];
                    size = 1;
                } else {
                    for (int i = index + 1; i < array.length; i++) {
                        array[i - 1] = array[i];
                    }
                    size--;
                }
            }
        } else {
            ArrayList<E> arrayList = (ArrayList) data;
            if (arrayList.contains(o)) {
                if (size > 6) {
                    return arrayList.remove(o);
                } else {
                    arrayList.remove(o);
                    data = arrayList.toArray();
                }
            } else {
                return false;
            }
        }
        return false;
    }

    /**
     * @param i index in SmartList
     * @return element
     */
    public E get(int i) {
        if (i < 0 || i >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (size == 1) {
            return (E) data;
        }
        return (size <= 5) ? (E) ((Object[]) data)[i] : (E) (((ArrayList) data).get(i));
    }

    public E set(int i, E element) {
        if (i < 0 || i >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (size == 1) {
            E returnValue = (E) data;
            data = element;
            return returnValue;
        } else if (size <= 5) {
            E returnValue = (E) ((Object[]) data)[i];
            ((Object[]) data)[i] = element;
            return returnValue;
        } else {
            E returnValue = (E) ((ArrayList<E>) data).get(i);
            ((ArrayList<Object>) data).set(i, element);
            return returnValue;
        }
    }

    @Override
    public E remove(int i) {
        if (i < 0 || i >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }

        if (size == 1) {
            E returnValue = (E) data;
            data = null;
            size = 0;
            return returnValue;
        } else if (size <= 5) {
            if (size == 2) {
                E returnValue = (E) ((Object[]) data)[i];
                data = (E) ((Object[]) data)[1 - i];
                size = 1;
                return returnValue;
            } else {
                E returnValue = (E) ((Object[]) data)[i];
                for (int j = i + 1; j < ((Object[]) data).length; j++) {
                    ((Object[]) data)[j - 1] = ((Object[]) data)[j];
                }
                size--;
                ((Object[]) data)[((Object[]) data).length - 1] = null;
                return returnValue;
            }

        } else if (size == 6) {
            E returnValue = (E) ((ArrayList) data).get(i);
            ((ArrayList) data).remove(i);
            data = ((ArrayList) data).toArray();
            size--;
            return returnValue;
        } else {
            E returnValue = (E) ((ArrayList) data).get(i);
            ((ArrayList) data).remove(i);
            size--;
            return returnValue;
        }
    }

}
