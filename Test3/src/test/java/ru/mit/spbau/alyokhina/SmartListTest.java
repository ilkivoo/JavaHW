package ru.mit.spbau.alyokhina;

import org.junit.Test;


import static org.junit.Assert.*;


public class SmartListTest {

    @Test
    public void testAdd() {
        SmartList<Integer> list = new SmartList<>();

        assertEquals(0, list.size());

        list.add(1);
        assertEquals(1, list.size());

        list.add(2);
        assertEquals(2, list.size());
    }

    @Test
    public void testGet() {
        SmartList<Integer> list = new SmartList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        assertEquals((Integer)3, list.get(2));
    }

    @Test
    public void testSet() {
        SmartList<Integer> list = new SmartList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        assertEquals((Integer) 2, list.set(1, (Integer) 4));
        assertEquals((Integer)4, list.get(1));
    }

    @Test
    public void testRemoveMany() {
        SmartList<Integer> list = new SmartList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(1);
        list.add(2);
        list.add(3);
        assertEquals((Integer) 2, list.remove(1));
        assertEquals((Integer) 3, list.remove(1));
    }

    @Test
    public void testRemoveOne() {
        SmartList<Integer> list = new SmartList<>();
        list.add(1);
        assertEquals((Integer) 1, list.remove(0));
        assertEquals(0, list.size());
    }

    @Test
    public void testRemove() {
        SmartList<Integer> list = new SmartList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(1);
        list.add(2);
        list.add(3);
        assertEquals(true, list.remove((Integer)1));
    }
}