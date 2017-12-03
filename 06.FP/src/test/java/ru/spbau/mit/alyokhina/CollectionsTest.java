package ru.spbau.mit.alyokhina;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class CollectionsTest {
    @Test
    public void testMap() {
        List<Integer> data = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> answer = Collections.map(x -> x + 1, data);
        Integer right = 2;
        for (Integer element : answer) {
            assertEquals(right, element);
            right++;
        }
    }

    @Test
    public void testFilter() {
        List<Integer> data = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> answer = Collections.filter(x -> x % 2 == 0, data);
        Integer right = 2;
        for (Integer element : answer) {
            assertEquals(right, element);
            right += 2;
        }
    }

    @Test
    public void testTakeWhile() {
        List<Integer> data = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> answer = Collections.takeWhile(x -> x <= 3, data);
        Integer right = 2;
        for (Integer element : answer) {
            assertEquals(true, element <= 3);
        }
    }

    @Test
    public void tstTakeUnless() {
        List<Integer> data = Arrays.asList(5, 4, 3, 2, 1);
        List<Integer> answer = Collections.takeUnless(x -> x <= 3, data);
        Integer right = 2;
        for (Integer element : answer) {
            assertEquals(true, element > 3);
        }

    }

    @Test
    public void testFoldl() {
        List<Integer> data = Arrays.asList(1, 2, 3, 4, 5);
        assertEquals(Integer.valueOf(57), Collections.foldl((a, b) -> 2 * a + b, 0, data));
    }

    @Test
    public void testFoldr() {
        List<Integer> data = Arrays.asList(1, -2, 3, -4, 5);
        assertEquals(Integer.valueOf(6), Collections.foldr((a, b) -> 2 * a + b, 0, data));
    }
}