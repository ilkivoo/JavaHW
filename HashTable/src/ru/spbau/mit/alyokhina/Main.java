package ru.spbau.mit.alyokhina;

import ru.spbau.mit.alyokhina.HashTable.HashTable;

/**
 * Для проверки
 */
public class Main {
    public static void main(String[] argv) {
        HashTable a = new HashTable();
        a.put("1", "44");
        a.put("2", "55");
        a.put("3", "66");
        System.out.println(a.getCapacity());
        System.out.println(a.remove("2"));
        System.out.println(a.contains("1"));
        System.out.println(a.size());
        a.clear();
        System.out.println(a.size());
        System.out.println(a.contains("5"));

    }
}