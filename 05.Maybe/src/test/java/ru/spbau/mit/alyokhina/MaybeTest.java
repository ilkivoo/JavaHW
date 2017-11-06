package ru.spbau.mit.alyokhina;

import org.junit.Test;
import static org.junit.Assert.*;

/** Test for Maybe */
public class MaybeTest {
    @Test
    public void testJustAndGet() throws Exception {
       Maybe<Integer> a = Maybe.just(5);
       assertEquals((Integer) 5, a.get());
    }

    @Test
    public void testJustWithHelpIsPresent() throws Exception {
        Maybe<Integer> a = Maybe.just(5);
        assertEquals(true, a.isPresent());
    }


    @Test(expected = ValueNotPresentException.class)
    public void testNothingAndGet() throws Exception {
        Maybe<Integer> a = Maybe.nothing();
        a.get();
        assertEquals(5, 6);
    }

    @Test
    public void testNothingWithHelpIsPresent() throws Exception {
        Maybe<Integer> a = Maybe.nothing();
        assertEquals(false, a.isPresent());
    }

    @Test
    public void testIsPresentIfTrue() throws Exception {
        Maybe<String> a = Maybe.just("Abacaba");
        assertEquals(true, a.isPresent());
    }

    @Test
    public void testIsPresentIfFalse() throws Exception {
        Maybe<String> a = Maybe.nothing();
        assertEquals(false, a.isPresent());
    }

    @Test
    public void testMapIfStoreValueNotNull() throws Exception {
        Maybe<Integer> a = Maybe.just(5);
        assertEquals((Integer)10, a.map(x -> x * 2).get());
    }

    @Test
    public void testMapIfStoreValueNull() throws Exception {
        Maybe<Integer> a = Maybe.nothing();
        assertEquals(false, a.map(x -> x * 2).isPresent());
    }
}