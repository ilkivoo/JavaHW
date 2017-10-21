package ru.spbau.mit.alyokhina.HashTable;

import ru.spbau.mit.alyokhina.List.List;

/**
 * Hash-Table
 * Коллизии решаются методом цепочек (закрытая адресация).
 */
public class HashTable {
    /** количество списков в хеш-таблице */
    private int capacity = 4;

    /** хеш-таблица */
    private List[] arrayOfLists;

    /** общее количество неповторяющихся ключей */
    private int numberOfKeys = 0;

    public HashTable() {
        arrayOfLists = new List[capacity];
        for (int i = 0; i < capacity; i++) {
            arrayOfLists[i] = new List();
        }
    }

    /** Перестройка хеш-таблицы. Увеличивается количество списков. А все элементы перехешируются */
    private void rebuild() {
        capacity *= 2;
        List[] newArrayOfLists = new List[capacity];
        for (int i = 0; i < capacity; i++) {
            newArrayOfLists[i] = new List();
        }

        for (int i = 0; 2 * i < capacity; i++) {
            while (arrayOfLists[i].getHeadKey() != null) {
                int newHash = hashFunction(arrayOfLists[i].getHeadKey());
                newArrayOfLists[newHash].pushFront(arrayOfLists[i].getHeadKey(), arrayOfLists[i].getHeadValue());
                arrayOfLists[i].pop(arrayOfLists[i].getHeadKey());
            }
        }
        arrayOfLists = newArrayOfLists;
    }


    /**
     * @param str по ней будет найдено значение
     * @return хеш-функцию данной строки
     */
    private int hashFunction(String str) {
        return str.hashCode() % capacity;
    }

    /** @return количество неповторяющихся ключей */
    public int size() {
        return numberOfKeys;
    }

    /**
     * @param key Содержится ли этот ключ в хеш-таблице
     * @return true если в хеш-таблице найден элемент с таким key, иначе - false
     */
    public boolean contains(String key) {
        return (get(key) != null);
    }

    /** @return значение по ключу */
    public String get(String key) {
        int hash = hashFunction(key);
        return arrayOfLists[hash].getValue(key);
    }

    /**
     * добавляет элемент в хеш-таблицу с заданными key и value. Если элемент с таким key уже был, то перезаписывается
     * @return предыдущее значение по этому ключу. Эсли такого не было - null
     */
    public String put(String key, String value) {
        int hash = hashFunction(key);
        String valueByKey = arrayOfLists[hash].getValue(key);
        if (valueByKey != null) {
            arrayOfLists[hash].pop(key);
            numberOfKeys--;
        }
        arrayOfLists[hash].pushFront(key, value);
        numberOfKeys++;
        if (numberOfKeys == capacity) {
            rebuild();
        }
        return valueByKey;
    }

    /**
     *  удаляет элемент за заданным ключом из хеш-таблицы
     * @return удаленное значение
     */
    public String remove(String key) {
        int hash = hashFunction(key);
        String result = arrayOfLists[hash].getValue(key);
        if (result != null) {
            arrayOfLists[hash].pop(key);
            numberOfKeys--;
        }
        return result;
    }

    /** очищает хеш-таблицу */
    public void clear() {
        for (int i = 0; i < capacity; i++) {
            arrayOfLists[i].clear();
        }
        numberOfKeys = 0;
    }

    /** @return capacity */
    public int getCapacity() {
        return capacity;
    }
}