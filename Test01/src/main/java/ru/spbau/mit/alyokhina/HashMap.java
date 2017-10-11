package ru.spbau.mit.alyokhina;


import java.util.*;

public class HashMap <K, V>  extends AbstractMap<K, V> implements Map<K, V > {
    private AbstractMap<K, V> data;
    public int size() {
        return data.size();
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    public boolean containsKey(Object o) {
        return data.containsKey(o);
    }

    public boolean containsValue(Object o) {
        return data.containsValue(o);
    }

    public V get(Object o) {
        return data.get(o);
    }

    public V put(K k, V v) {
        return data.put(k, v);
    }

    public V remove(Object o) {
        return data.remove(o);
    }

    public void putAll(Map<? extends K, ? extends V> map) {
        data.putAll(map);
    }

    public void clear() {
        data.clear();
    }

    public Set<K> keySet() {
        return data.keySet();
    }

    public Collection<V> values() {
        return data.values();
    }

    public Set<Entry<K, V>> entrySet() {
        return data.entrySet();
    }

    public class MyEntryMap extends AbstractSet<Entry<K, V>> {
        private Set<Map.Entry<K, V>> data;

        @Override
        public Iterator<Entry<K, V>> iterator() {
            return new IteratorForLinkedHM(data.iterator());
        }

        @Override
        public int size() {
            return data.size();
        }
    }

    public class IteratorForLinkedHM implements Iterator<Entry <K, V>> {
        Iterator<Map.Entry<K, V>> it;
        public IteratorForLinkedHM(Iterator<Map.Entry<K, V>> it) {
            this.it = it;
        }

        @Override
        public boolean hasNext() {
            return it.hasNext();
        }

        @Override
        public Entry<K, V> next() {
            return it.next();
        }

        @Override
        public void remove() {

        }
    }
}
