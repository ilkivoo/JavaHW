package ru.spbau.mit.alyokhina;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;

/**
 * Test for TreeSet
 */
public class TreeSetTest {
    @Test
    public void testConstructor() {
        TreeSet<Integer> set = new TreeSet<>();
    }

    @Test
    public void testConstructorWithComparator() {
        TreeSet<Integer> set = new TreeSet<>((x, y) -> (x - y));
    }

    @Test
    public void testAddInEmptySet() {
        TreeSet<Integer> set = new TreeSet<>();
        set.add(5);
        assertEquals(1, set.size());
    }

    @Test
    public void testAddInEmptySetWithComparator() {
        TreeSet<Integer> set = new TreeSet<>((x, y) -> (x - y));
        set.add(5);
        assertEquals(1, set.size());
    }

    @Test
    public void testAdd() {
        TreeSet<Integer> set = new TreeSet<>();
        set.add(5);
        set.add(6);
        assertEquals(2, set.size());
    }

    @Test
    public void testAddWithComparator() {
        TreeSet<Integer> set = new TreeSet<>((x, y) -> (x - y));
        set.add(5);
        set.add(6);
        assertEquals(2, set.size());
    }

    @Test
    public void testAddReturnValueIsTrue() {
        TreeSet<Integer> set = new TreeSet<>();
        set.add(5);
        assertEquals(true, set.add(6));

    }

    @Test
    public void testAddReturnValueIsFalse() {
        TreeSet<Integer> set = new TreeSet<>();
        set.add(5);
        assertEquals(false, set.add(5));

    }

    @Test
    public void testAddReturnValueIsTrueWithComparator() {
        TreeSet<Integer> set = new TreeSet<>((x, y) -> (x - y));
        set.add(5);
        assertEquals(true, set.add(6));

    }

    @Test
    public void testAddReturnValueIsFalseWithComparator() {
        TreeSet<Integer> set = new TreeSet<>((x, y) -> (x - y));
        set.add(5);
        assertEquals(false, set.add(5));

    }


    @Test
    public void testContainsEmptySet() {
        TreeSet<Integer> set = new TreeSet<>();
        assertEquals(false, set.contains(5));
    }

    @Test
    public void testContainsIfTrue() {
        TreeSet<Integer> set = new TreeSet<>();
        set.add(5);
        set.add(6);
        set.add(4);
        set.add(7);
        assertEquals(true, set.contains(4));
        assertEquals(true, set.contains(7));
        assertEquals(true, set.contains(5));
        assertEquals(true, set.contains(6));
    }

    @Test
    public void testContainsWithComparatorIfTrue() {
        TreeSet<Integer> set = new TreeSet<>((x, y) -> (x - y));
        set.add(5);
        set.add(6);
        set.add(4);
        set.add(7);
        assertEquals(true, set.contains(4));
        assertEquals(true, set.contains(7));
        assertEquals(true, set.contains(5));
        assertEquals(true, set.contains(6));
    }

    @Test
    public void testContainsIfFalse() {
        TreeSet<Integer> set = new TreeSet<>();
        set.add(3);
        set.add(8);
        set.add(10);
        set.add(1);
        assertEquals(false, set.contains(4));
        assertEquals(false, set.contains(7));
        assertEquals(false, set.contains(5));
        assertEquals(false, set.contains(6));
    }

    @Test
    public void testContainsWithComparatorIfFalse() {
        TreeSet<Integer> set = new TreeSet<>((x, y) -> (x - y));
        set.add(3);
        set.add(8);
        set.add(10);
        set.add(1);
        assertEquals(false, set.contains(4));
        assertEquals(false, set.contains(7));
        assertEquals(false, set.contains(5));
        assertEquals(false, set.contains(6));
    }


    @Test
    public void testRemoveEmptySet() {
        TreeSet<Integer> set = new TreeSet<>();
        assertEquals(false, set.remove(5));
    }

    @Test
    public void testRemoveEmptySetWithComparator() {
        TreeSet<Integer> set = new TreeSet<>((x, y) -> (x - y));
        assertEquals(false, set.remove(5));
    }

    @Test
    public void testRemoveIfTrueOneElement() {
        TreeSet<Integer> set = new TreeSet<>();
        set.add(5);
        assertEquals(true, set.remove(5));
        assertEquals(0, set.size());
    }

    @Test
    public void testRemoveIfTrue() {
        TreeSet<Integer> set = new TreeSet<>();
        set.add(5);
        set.add(6);
        set.add(3);
        set.add(4);
        set.add(2);
        assertEquals(true, set.remove(4));
        assertEquals(4, set.size());
        assertEquals(false, set.contains(4));
    }


    @Test
    public void testRemoveIfTrueOneElementWithComparator() {
        TreeSet<Integer> set = new TreeSet<>((x, y) -> (x - y));
        set.add(5);
        assertEquals(true, set.remove(5));
        assertEquals(0, set.size());
    }

    @Test
    public void testRemoveIfTrueWithComparator() {
        TreeSet<Integer> set = new TreeSet<>((x, y) -> (x - y));
        set.add(5);
        set.add(6);
        set.add(3);
        set.add(4);
        set.add(2);
        assertEquals(true, set.remove(4));
        assertEquals(4, set.size());
        assertEquals(false, set.contains(4));
    }

    @Test
    public void testIteratorWithComparator() throws Exception {
        TreeSet<Integer> set = new TreeSet<>((x, y) -> (x - y));
        set.add(3);
        set.add(1);
        set.add(5);
        set.add(7);
        set.add(6);
        Iterator<Integer> iterator = set.iterator();
        for (Integer integer : Arrays.asList(1, 3, 5, 6, 7)) {
            assertTrue(iterator.hasNext());
            assertEquals(integer, iterator.next());
        }
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testIterator() throws Exception {
        TreeSet<Integer> set = new TreeSet<>();
        set.add(3);
        set.add(1);
        set.add(5);
        set.add(7);
        set.add(6);
        Iterator<Integer> iterator = set.iterator();
        for (Integer integer : Arrays.asList(1, 3, 5, 6, 7)) {
            assertEquals(true, iterator.hasNext());
            assertEquals(integer, iterator.next());
        }
        assertEquals(false, iterator.hasNext());
    }

    @Test
    public void testIteratorAfterRemove() throws Exception {
        TreeSet<Integer> set = new TreeSet<>();
        set.add(3);
        set.add(1);
        set.add(5);
        set.add(4);
        set.add(7);
        set.add(6);
        set.remove(4);
        Iterator<Integer> iterator = set.iterator();
        for (Integer integer : Arrays.asList(1, 3, 5, 6, 7)) {
            assertEquals(true, iterator.hasNext());
            assertEquals(integer, iterator.next());
        }
        assertEquals(false, iterator.hasNext());
    }

    @Test
    public void testIteratorAfterRemoveWithComparator() throws Exception {
        TreeSet<Integer> set = new TreeSet<>((x, y) -> (x - y));
        set.add(3);
        set.add(1);
        set.add(5);
        set.add(4);
        set.add(7);
        set.add(6);
        set.remove(4);
        Iterator<Integer> iterator = set.iterator();
        for (Integer integer : Arrays.asList(1, 3, 5, 6, 7)) {
            assertEquals(true, iterator.hasNext());
            assertEquals(integer, iterator.next());
        }
        assertEquals(false, iterator.hasNext());
    }


    @Test
    public void testSizeEmptySet() {
        TreeSet<Integer> set = new TreeSet<>();
        assertEquals(0, set.size());
    }

    @Test
    public void testSizeEmptySetWithComparator() {
        TreeSet<Integer> set = new TreeSet<>((x, y) -> (x - y));
        assertEquals(0, set.size());
    }

    @Test
    public void testSize() {
        TreeSet<Integer> set = new TreeSet<>();
        set.add(3);
        set.add(1);
        set.add(5);
        set.add(7);
        set.add(6);
        assertEquals(5, set.size());
    }


    @Test
    public void testSizeWithComparator() {
        TreeSet<Integer> set = new TreeSet<>((x, y) -> (x - y));
        set.add(3);
        set.add(1);
        set.add(5);
        set.add(7);
        set.add(6);
        assertEquals(5, set.size());
    }

    @Test
    public void testSizeAfterRemove() {
        TreeSet<Integer> set = new TreeSet<>();
        set.add(3);
        set.add(1);
        set.add(5);
        set.add(7);
        set.add(6);
        assertEquals(5, set.size());
        set.remove(3);
        assertEquals(4, set.size());
    }

    @Test
    public void testSizeAfterRemoveWithComparator() {
        TreeSet<Integer> set = new TreeSet<>((x, y) -> (x - y));
        set.add(3);
        set.add(1);
        set.add(5);
        set.add(7);
        set.add(6);
        assertEquals(5, set.size());
        set.remove(3);
        assertEquals(4, set.size());
    }


    @Test
    public void testDescendingIterator() {
        TreeSet<Integer> set = new TreeSet<>();
        set.add(3);
        set.add(1);
        set.add(5);
        set.add(7);
        set.add(6);
        Iterator<Integer> iterator = set.descendingIterator();
        for (Integer integer : Arrays.asList(7, 6, 5, 3, 1)) {
            assertEquals(true, iterator.hasNext());
            assertEquals(integer, iterator.next());
        }
        assertEquals(false, iterator.hasNext());
    }

    @Test
    public void testDescendingIteratorWithComparator() {
        TreeSet<Integer> set = new TreeSet<>((x, y) -> (x - y));
        set.add(3);
        set.add(1);
        set.add(5);
        set.add(7);
        set.add(6);
        Iterator<Integer> iterator = set.descendingIterator();
        for (Integer integer : Arrays.asList(7, 6, 5, 3, 1)) {
            assertEquals(true, iterator.hasNext());
            assertEquals(integer, iterator.next());
        }
        assertEquals(false, iterator.hasNext());
    }


    @Test
    public void testDescendingIteratorAfterRemove() throws Exception {
        TreeSet<Integer> set = new TreeSet<>();
        set.add(3);
        set.add(1);
        set.add(5);
        set.add(4);
        set.add(7);
        set.add(6);
        set.remove(4);
        Iterator<Integer> iterator = set.descendingIterator();
        for (Integer integer : Arrays.asList(7, 6, 5, 3, 1)) {
            assertEquals(true, iterator.hasNext());
            assertEquals(integer, iterator.next());
        }
        assertEquals(false, iterator.hasNext());
    }

    @Test
    public void testDescendingIteratorAfterRemoveWithComparator() throws Exception {
        TreeSet<Integer> set = new TreeSet<>((x, y) -> (x - y));
        set.add(3);
        set.add(1);
        set.add(5);
        set.add(4);
        set.add(7);
        set.add(6);
        set.remove(4);
        Iterator<Integer> iterator = set.descendingIterator();
        for (Integer integer : Arrays.asList(7, 6, 5, 3, 1)) {
            assertEquals(true, iterator.hasNext());
            assertEquals(integer, iterator.next());
        }
        assertEquals(false, iterator.hasNext());
    }

    @Test
    public void descendingSet() {
        TreeSet<Integer> set = new TreeSet<>();
        set.add(3);
        set.add(1);
        set.add(5);
        set.add(4);
        set.add(7);
        set.add(6);
        set.remove(4);
        TreeSetInterface<Integer> descendingSet = set.descendingSet();
        Iterator<Integer> iterator = descendingSet.iterator();
        for (Integer integer : Arrays.asList(7, 6, 5, 3, 1)) {
            assertEquals(true, iterator.hasNext());
            assertEquals(integer, iterator.next());
        }
        assertEquals(false, iterator.hasNext());
    }

    @Test
    public void descendingSetWithComparator() {
        TreeSet<Integer> set = new TreeSet<>((x, y) -> (x - y));
        set.add(3);
        set.add(1);
        set.add(5);
        set.add(4);
        set.add(7);
        set.add(6);
        set.remove(4);
        TreeSetInterface<Integer> descendingSet = set.descendingSet();
        Iterator<Integer> iterator = descendingSet.iterator();
        for (Integer integer : Arrays.asList(7, 6, 5, 3, 1)) {
            assertEquals(true, iterator.hasNext());
            assertEquals(integer, iterator.next());
        }
        assertEquals(false, iterator.hasNext());
    }

    @Test
    public void testFirst() {
        TreeSet<Integer> set = new TreeSet<>();
        set.add(3);
        set.add(1);
        set.add(5);
        set.add(4);
        set.add(7);
        set.add(6);
        set.remove(4);
        assertEquals((Integer) 1, set.first());
    }

    @Test
    public void testLast() {
        TreeSet<Integer> set = new TreeSet<>();
        set.add(3);
        set.add(1);
        set.add(5);
        set.add(4);
        set.add(7);
        set.add(6);
        set.remove(4);
        assertEquals((Integer) 7, set.last());
    }

    @Test
    public void testLower() {
        TreeSet<Integer> set = new TreeSet<>();
        set.add(3);
        set.add(1);
        set.add(5);
        set.add(4);
        set.add(7);
        set.add(6);
        set.remove(4);
        assertEquals((Integer) 1, set.lower(3));
    }

    @Test
    public void testFloor() {
        TreeSet<Integer> set = new TreeSet<>();
        set.add(3);
        set.add(1);
        set.add(5);
        set.add(4);
        set.add(7);
        set.add(6);
        set.remove(4);
        assertEquals((Integer) 3, set.floor(3));
    }

    @Test
    public void testCeiling() {
        TreeSet<Integer> set = new TreeSet<>();
        set.add(3);
        set.add(1);
        set.add(5);
        set.add(4);
        set.add(7);
        set.add(6);
        set.remove(4);
        assertEquals((Integer) 5, set.ceiling(3));
    }

    @Test
    public void testHigher() {
        TreeSet<Integer> set = new TreeSet<>();
        set.add(3);
        set.add(1);
        set.add(5);
        set.add(4);
        set.add(7);
        set.add(6);
        set.remove(4);
        assertEquals((Integer) 5, set.higher(5));
    }

    @Test
    public void testFirstWithComparator() {
        TreeSet<Integer> set = new TreeSet<>((x, y) -> (x - y));
        set.add(3);
        set.add(1);
        set.add(5);
        set.add(4);
        set.add(7);
        set.add(6);
        set.remove(4);
        assertEquals((Integer) 1, set.first());
    }

    @Test
    public void testLastWithComparator() {
        TreeSet<Integer> set = new TreeSet<>((x, y) -> (x - y));
        set.add(3);
        set.add(1);
        set.add(5);
        set.add(4);
        set.add(7);
        set.add(6);
        set.remove(4);
        assertEquals((Integer) 7, set.last());
    }

    @Test
    public void testLowerWithComparator() {
        TreeSet<Integer> set = new TreeSet<>((x, y) -> (x - y));
        set.add(3);
        set.add(1);
        set.add(5);
        set.add(4);
        set.add(7);
        set.add(6);
        set.remove(4);
        assertEquals((Integer) 1, set.lower(3));
    }

    @Test
    public void testFloorWithComparator() {
        TreeSet<Integer> set = new TreeSet<>((x, y) -> (x - y));
        set.add(3);
        set.add(1);
        set.add(5);
        set.add(4);
        set.add(7);
        set.add(6);
        set.remove(4);
        assertEquals((Integer) 3, set.floor(3));
    }

    @Test
    public void testCeilingWithComparator() {
        TreeSet<Integer> set = new TreeSet<>((x, y) -> (x - y));
        set.add(3);
        set.add(1);
        set.add(5);
        set.add(4);
        set.add(7);
        set.add(6);
        set.remove(4);
        assertEquals((Integer) 5, set.ceiling(3));
    }

    @Test
    public void testHigherWithComparator() {
        TreeSet<Integer> set = new TreeSet<>((x, y) -> (x - y));
        set.add(3);
        set.add(1);
        set.add(5);
        set.add(4);
        set.add(7);
        set.add(6);
        set.remove(4);
        assertEquals((Integer) 5, set.higher(5));
    }

}