package ru.spbau.mit.alyokhina.HashTable;

import org.junit.Test;

import static org.junit.Assert.*;


/** Test for hash-table */
public class HashTableTest {

    @Test
    public void testConstructor() throws Exception {
        HashTable hashTable = new HashTable();
    }

    @Test
    public void testSize() throws Exception {
        HashTable hashTable = new HashTable();
        hashTable.put("1", "one");
        hashTable.put("2", "two");
        hashTable.put("3", "three");
        hashTable.put("4", "four");
        hashTable.put("3", "five");
        assertEquals(4, hashTable.size());
    }

    @Test
    public void testSizeAfterRemove() throws Exception {
        HashTable hashTable = new HashTable();
        hashTable.put("1", "one");
        hashTable.put("2", "two");
        hashTable.put("3", "three");
        hashTable.put("4", "four");
        hashTable.remove("3");
        assertEquals(3, hashTable.size());
    }

    @Test
    public void testContainsIfTrue() throws Exception {
        HashTable hashTable = new HashTable();
        hashTable.put("1", "one");
        assertEquals(true, hashTable.contains("1"));
    }

    @Test
    public void testContainsIfFalse() throws Exception {
        HashTable hashTable = new HashTable();
        hashTable.put("1", "one");
        assertEquals(false, hashTable.contains("2"));
    }

    @Test
    public void testContainsAfterRemove() throws Exception {
        HashTable hashTable = new HashTable();
        hashTable.put("1", "one");
        hashTable.put("2", "two");
        hashTable.remove("1");
        assertEquals(false, hashTable.contains("1"));
    }

    @Test
    public void testGet() throws Exception {
        HashTable hashTable = new HashTable();
        hashTable.put("1", "one");
        assertEquals("one", hashTable.get("1"));
    }

    @Test
    public void testPut() throws Exception {
        HashTable hashTable = new HashTable();
        hashTable.put("1", "one");
        assertEquals("one", hashTable.get("1"));
    }

    @Test
    public void testPutReturnValueIsNull() throws Exception {
        HashTable hashTable = new HashTable();
        assertEquals(null, hashTable.put("1", "one"));
    }

    @Test
    public void testPutReturnValuesIsNotNull() throws Exception {
        HashTable hashTable = new HashTable();
        hashTable.put("1", "one");
        assertEquals("one", hashTable.put("1", "two"));
    }

    @Test
    public void testPutWithReplacement() throws Exception {
        HashTable hashTable = new HashTable();
        hashTable.put("1", "one");
        assertEquals("one", hashTable.put("1", "two"));
        assertEquals("two", hashTable.get("1"));
    }

    @Test
    public void remove() throws Exception {
        HashTable hashTable = new HashTable();
        hashTable.put("1", "one");
        hashTable.put("2", "two");
        hashTable.put("3", "three");
        hashTable.put("4", "four");
        assertEquals("three", hashTable.remove("3"));
    }

    @Test
    public void testClear() throws Exception {
        HashTable hashTable = new HashTable();
        hashTable.put("1", "one");
        hashTable.put("2", "two");
        hashTable.put("3", "three");
        hashTable.put("4", "four");
        hashTable.clear();
        assertEquals(0, hashTable.size());
    }

    @Test
    public void testClearIfEmpty() throws Exception {
        HashTable hashTable = new HashTable();
        hashTable.clear();
        assertEquals(0, hashTable.size());
    }

    @Test
    public void testGetCapacity() throws Exception {
        HashTable hashTable = new HashTable();
        for (int i = 0; i < 35; i++) {
            hashTable.put(Integer.toString(i), Integer.toString(i + 1));
        }
        assertEquals(64, hashTable.getCapacity());
    }

    @Test
    public void testRebuild() throws Exception {
        HashTable hashTable = new HashTable();
        hashTable.put("1", "one");
        hashTable.put("2", "two");
        hashTable.put("3", "three");
        hashTable.put("4", "four");
        hashTable.put("5", "four");
        assertEquals(8, hashTable.getCapacity());
        assertEquals(true, hashTable.contains("1"));
        assertEquals(true, hashTable.contains("2"));
        assertEquals(true, hashTable.contains("3"));
        assertEquals(true, hashTable.contains("4"));
        assertEquals(true, hashTable.contains("5"));
    }

    @Test
    public void testCollisionSolution() throws Exception {
        HashTable hashTable = new HashTable();
        hashTable.put("Da", "one");
        hashTable.put("EB", "two");
        assertEquals(true, hashTable.contains("Da"));
        assertEquals(true, hashTable.contains("EB"));
    }

    @Test
    public void testCollisionSolutionWithHelpGet() throws Exception {
        HashTable hashTable = new HashTable();
        hashTable.put("Da", "one");
        hashTable.put("EB", "two");
        assertEquals("one", hashTable.get("Da"));
        assertEquals("two", hashTable.get("EB"));
    }
}