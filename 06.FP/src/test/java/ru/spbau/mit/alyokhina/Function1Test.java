package ru.spbau.mit.alyokhina;
import org.junit.Test;
import static org.junit.Assert.*;

public class Function1Test {
    @Test
    public void testApplyIfArgumentsTheSameType() {
        Function1 <Integer, Integer> inc = x -> x + 1;
        assertEquals(Integer.valueOf(4), inc.apply(3));

    }

    @Test
    public void testApplyIfTheArgumentsDifferentTypes() {
        Function1 <Integer, Double> f = x -> x + 0.1;
        assertEquals(Double.valueOf(3.1), f.apply(3));

    }

    @Test
    public void testComposeIfArgumentsTheSameType() {
        Function1 <Integer, Integer> f = x -> x + 1;
        Function1 <Integer, Integer> g = x -> x * 2;
        assertEquals(Integer.valueOf(8), f.compose(g).apply(3));

    }

    @Test
    public void testComposeIfArgumentsDifferentsTheSameType() {
        Function1 <Integer, Integer> f = x -> x + 1;
        Function1 <Integer, Double> g = x -> x * 2.1;
        assertEquals(Double.valueOf(8.4), f.compose(g).apply(3));

    }
}