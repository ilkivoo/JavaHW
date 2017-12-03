package ru.spbau.mit.alyokhina;

import org.junit.Test;

import static org.junit.Assert.*;

public class Function2Test {
    @Test
    public void testApply() {
        Function2<Integer, Integer, Integer> f = (a, b) -> a * b;
        assertEquals(Integer.valueOf(6), f.apply(2, 3));
    }

    @Test
    public void testCompose() {
        Function2<Integer, Integer, Integer> f = (a, b) -> a * b;
        Function1<Integer, Double> g = x -> x + 0.1;
        assertEquals(Double.valueOf(6.1), f.compose(g).apply(2, 3));
    }

    @Test
    public void testBind1() {
        Function2<Integer, Integer, Integer> f = (a, b) -> a * b;
        assertEquals(Integer.valueOf(6), f.bind1(2).apply(3));
    }

    @Test
    public void testBind2() {
        Function2<Integer, Integer, Integer> f = (a, b) -> a * b;
        assertEquals(Integer.valueOf(10), f.bind2(2).apply(5));
    }

    @Test
    public void testCurry() {
        Function2<Integer, Integer, Integer> f = (a, b) -> a * b;
        Function1<Integer, Function1<Integer, Integer>> g = f.curry();
        assertEquals(Integer.valueOf(6), g.apply(2).apply(3));
    }

}