package ru.spbau.mit.alyokhina;

import org.junit.Test;

import static org.junit.Assert.*;

public class StackTest {
    @Test
    public void testConstructor() {
        Stack<Integer> s;
    }

    @Test
    public void testPush() {
        Stack<Integer> s = new Stack<Integer>();
        s.push(3);
        assertEquals((Integer) 3, s.peek());
        s.push(5);
        assertEquals((Integer) 5, s.peek());
    }

    @Test
    public void testPopFromEmptyStack() {
        Stack<Integer> s = new Stack<Integer>();
        assertEquals(null, s.pop());
    }


    @Test
    public void testPop() {
        Stack<Integer> s = new Stack<Integer>();
        s.push(3);
        s.push(5);
        assertEquals((Integer) 5, s.pop());
        assertEquals((Integer) 3, s.peek());
    }

    @Test
    public void testPeekFromEmptyStack() {
        Stack<Integer> s = new Stack<Integer>();
        assertEquals(null, s.peek());
    }

    @Test
    public void testPeek() {
        Stack<Integer> s = new Stack<Integer>();
        s.push(3);
        s.push(5);
        assertEquals((Integer) 5, s.peek());
    }


    @Test
    public void testIsEmptyIfTrue() {
        Stack<Integer> s = new Stack<Integer>();
        assertEquals(true, s.isEmpty());
    }

    @Test
    public void testIsEmptyIfFalse() {
        Stack<Integer> s = new Stack<Integer>();
        s.push(1);
        assertEquals(false, s.isEmpty());
    }

    @Test
    public void print() {
        Stack<Integer> s = new Stack<Integer>();
        s.push(3);
        s.push(5);
        String t = "5 3 ";
        assertEquals(t, s.toString());
    }

}