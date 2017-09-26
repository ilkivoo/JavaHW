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
    public void testPopNullNode() throws Exception {
        List l = new List();
        l.pop(null);
    }

    @Test
    public void testPop() throws Exception {
        List l = new List();
        l.pushFront("1", "one");
        l.pushFront("2", "two");
        l.pushFront("3", "three");
        l.pop(l.getNode("2"));
        assertEquals(null, l.getValue("2"));
    }

    @Test
    public void testPopHead() throws Exception {
        List l = new List();
        l.pushFront("1", "one");
        l.pushFront("2", "two");
        l.pushFront("3", "three");
        l.pop(l.getHead());
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
    public void testGetNode() throws Exception {
        List l = new List();
        l.pushFront("1", "one");
        l.pushFront("2", "two");
        l.pushFront("3", "three");
        assertEquals(l.getHead(), l.getNode("3"));
    }

    @Test
    public void testClear() throws Exception {
        List l = new List();
        l.pushFront("1", "one");
        l.pushFront("2", "two");
        l.pushFront("3", "three");
        l.clear();
        assertEquals(null, l.getHead());
    }

    @Test
    public void TestClearEmptyList() throws Exception {
        List l = new List();
        l.clear();
        assertEquals(null, l.getHead());
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
    public void getHead() throws Exception {
        List l = new List();
        l.pushFront("1", "one");
        l.pushFront("2", "two");
        l.pushFront("3", "three");
        l.pop(l.getHead());
        assertEquals("two", l.getHeadValue());
    }

    @Test
    public void getHeadEmptyList() throws Exception {
        List l = new List();
        assertEquals(null, l.getHead());
    }
}