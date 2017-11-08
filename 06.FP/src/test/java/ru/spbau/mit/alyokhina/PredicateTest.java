package ru.spbau.mit.alyokhina;

import org.junit.Test;

import static org.junit.Assert.*;

public class PredicateTest {
    @Test
    public void testOr() {
        Predicate<Integer> devided2 = x -> x % 2 == 0;
        Predicate<Integer> devided3 = x -> x % 3 == 0;
        assertEquals(true, devided2.or(devided3).apply(9));
    }

    @Test
    public void testAnd() {
        Predicate<Integer> devided2 = x -> x % 2 == 0;
        Predicate<Integer> devided3 = x -> x % 3 == 0;
        assertEquals(false, devided2.and(devided3).apply(9));
    }

    @Test
    public void testNot() {
        Predicate<Integer> devided2 = x -> x % 2 == 0;
        assertEquals(true, devided2.not().apply(9));
    }

    @Test
    public void testAlwaysTrue() {
        assertEquals(true, Predicate.alwaysTrue().apply(6));
    }

    @Test
    public void testAlwaysFalse(){
        assertEquals(false, Predicate.alwaysFalse().apply(6));
    }

}