package ru.spbau.mit.alyokhina.List;

import org.junit.Test;

import static org.junit.Assert.*;

public class ListTest {

    @Test
    public void testConstructor() throws Exception {
        List l = new List();
    }

    @Test
    public void testPushFrontToEmptyList() throws Exception {
        List l = new List();
        l.pushFront("1", "one");
        assertEquals("one", l.getHeadValue());
    }

    @Test
    public void testPushFront() throws Exception {
        List l = new List();
        l.pushFront("1", "one");
        l.pushFront("2", "two");
        l.pushFront("3", "three");
        assertEquals("three", l.getHeadValue());
    }

    @Test
    public void testPopNullKey() throws Exception {
        List l = new List();
        try {
            l.pop(null);
            assertEquals("not fount exeption", "" );
        }
        catch (RuntimeException e) {
            System.out.println("found the correct exception");
        }
    }

    @Test
    public void testPop() throws Exception {
        List l = new List();
        l.pushFront("1", "one");
        l.pushFront("2", "two");
        l.pushFront("3", "three");
        l.pop("2");
        assertEquals(null, l.getValue("2"));
    }

    @Test
    public void testPopWithHelpSize() throws Exception {
        List l = new List();
        l.pushFront("1", "one");
        l.pushFront("2", "two");
        l.pushFront("3", "three");
        l.pop("2");
        assertEquals(2, l.getSize());
    }

    @Test
    public void testPopHead() throws Exception {
        List l = new List();
        l.pushFront("1", "one");
        l.pushFront("2", "two");
        l.pushFront("3", "three");
        l.pop(l.getHeadKey());
        assertEquals("two", l.getHeadValue());
    }


    @Test
    public void testGetValue() throws Exception {
        List l = new List();
        l.pushFront("1", "one");
        l.pushFront("2", "two");
        l.pushFront("3", "three");
        assertEquals("two", l.getValue("2"));
    }

    @Test
    public void testClear() throws Exception {
        List l = new List();
        l.pushFront("1", "one");
        l.pushFront("2", "two");
        l.pushFront("3", "three");
        l.clear();
        assertEquals(null, l.getHeadKey());
    }

    @Test
    public void TestClearEmptyList() throws Exception {
        List l = new List();
        l.clear();
        assertEquals(null, l.getHeadKey());
    }

    @Test
    public void testGetHeadKey() throws Exception {
        List l = new List();
        l.pushFront("1", "one");
        l.pushFront("2", "two");
        l.pushFront("3", "three");
        assertEquals("3", l.getHeadKey());
    }

    @Test
    public void testGetHeadKeyEmptyList() throws Exception {
        List l = new List();
        assertEquals(null, l.getHeadKey());
    }

    @Test
    public void testGetHeadValue() throws Exception {
        List l = new List();
        l.pushFront("1", "one");
        l.pushFront("2", "two");
        l.pushFront("3", "three");
        assertEquals("three", l.getHeadValue());
    }

    @Test
    public void testGetHeadValueEmptyList() throws Exception {
        List l = new List();
        assertEquals(null, l.getHeadValue());
    }

    @Test
    public void testGetSize() throws Exception {
        List l = new List();
        l.pushFront("1", "one");
        l.pushFront("2", "two");
        l.pushFront("3", "three");
        assertEquals(3, l.getSize());
    }

    @Test
    public void testGetSizeAfterClear() throws Exception {
        List l = new List();
        l.pushFront("1", "one");
        l.pushFront("2", "two");
        l.pushFront("3", "three");
        l.clear();
        assertEquals(0, l.getSize());
    }
}