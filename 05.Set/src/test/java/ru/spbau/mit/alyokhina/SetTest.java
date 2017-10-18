package ru.spbau.mit.alyokhina;

import org.junit.Test;

import static org.junit.Assert.*;

/** Test for set */
public class SetTest {

    @Test
    public void testAddWithHelpConteins() throws Exception {
        Set<Integer> set = new Set();
        set.add(1);
        set.add(2);
        assertEquals(true, set.conteins(1));
        assertEquals(true, set.conteins(2));
    }

    @Test
    public void testAddWithHelpSize() throws Exception {
        Set<Integer> set = new Set();
        set.add(1);
        set.add(2);
        assertEquals(2, set.size());
    }

    @Test
    public void testConteinsEmptySet() throws Exception {
        Set<Integer> set = new Set();
        assertEquals(0, set.size());
    }

    @Test
    public void testConteinsIfAddSameNumber() throws Exception {
        Set<Integer> set = new Set();
        set.add(1);
        set.add(2);
        set.add(1);
        set.add(3);
        assertEquals(true, set.conteins(1));
        assertEquals(true, set.conteins(2));
        assertEquals(true, set.conteins(3));
    }

    @Test
    public void testSizeIfAddSameNumber() throws Exception {
        Set<Integer> set = new Set();
        set.add(1);
        set.add(2);
        set.add(1);
        set.add(3);
        assertEquals(3, set.size());
    }

    @Test
    public void testSizeIfAddDifferentNumber() throws Exception {
        Set<Integer> set = new Set();
        set.add(1);
        set.add(2);
        set.add(3);
        set.add(4);
        assertEquals(4, set.size());
    }

}