package ru.spbau.mit.alyokhina;

import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;

import static org.junit.Assert.*;

public class PQueueTest {
    @Test
    public void testAdd() throws Exception {
        PQueue<Integer> pq = new PQueue<>();
        pq.add(3);
        pq.add(1);
        pq.add(5);
        pq.add(7);
        pq.add(6);
        assertEquals(5, pq.size());
        pq.add(5);
        pq.add(6);
        assertEquals(7, pq.size());
    }

    @Test
    public void testPoll() throws Exception {
        PQueue<Integer> pq = new PQueue<>();
        pq.add(3);
        pq.add(1);
        pq.add(5);
        pq.add(7);
        pq.add(6);
        assertEquals((Integer)1, pq.poll());
        assertEquals(4, pq.size());
    }
    @Test
    public void testPollWithComparator() throws Exception {
        PQueue<Integer> pq = new PQueue<>((e1, e2) -> 0);
        pq.add(3);
        pq.add(1);
        pq.add(5);
        pq.add(7);
        pq.add(6);
        assertEquals((Integer)3, pq.poll());
        assertEquals(4, pq.size());
    }

    @Test
    public void testClear() throws Exception {
        PQueue<Integer> pq = new PQueue<>();
        pq.add(3);
        pq.add(1);
        pq.add(5);
        pq.add(7);
        pq.add(6);
        pq.clear();
        assertEquals(0, pq.size());
    }

    @Test
    public void testSize() throws Exception {
        PQueue<Integer> pq = new PQueue<>();
        pq.add(3);
        pq.add(1);
        pq.add(5);
        pq.add(7);
        pq.add(6);
        assertEquals(5, pq.size());
    }

    @Test
    public void testIterator() throws Exception {
        PQueue<Integer> pq = new PQueue<>();
        pq.add(3);
        pq.add(1);
        pq.add(5);
        pq.add(7);
        pq.add(6);
        Iterator<Integer> iterator = pq.iterator();
        for (Integer integer : Arrays.asList(1, 3, 5, 6, 7)) {
            assertEquals(true, iterator.hasNext());
            assertEquals(integer, iterator.next());
        }
        assertEquals(false, iterator.hasNext());
    }

    @Test
    public void testIteratorForComparePriorities() throws Exception {
        PQueue<Integer> pq = new PQueue<>((e1, e2) -> 0);
        pq.add(3);
        pq.add(1);
        pq.add(5);
        pq.add(7);
        pq.add(6);
        Iterator<Integer> iterator = pq.iterator();
        for (Integer integer : Arrays.asList(3, 1, 5, 7, 6)) {
            assertEquals(true, iterator.hasNext());
            assertEquals(integer, iterator.next());
        }
        assertEquals(false, iterator.hasNext());
    }

    @Test
    public void testIteratorWithComparator() throws Exception {
        PQueue<Integer> pq = new PQueue<>(((x, y) -> (y - x)));
        pq.add(3);
        pq.add(1);
        pq.add(5);
        pq.add(7);
        pq.add(6);
        Iterator<Integer> iterator = pq.iterator();
        for (Integer integer : Arrays.asList(7, 6, 5, 3, 1)) {
            assertEquals(true, iterator.hasNext());
            assertEquals(integer, iterator.next());
        }
        assertEquals(false, iterator.hasNext());
    }

}