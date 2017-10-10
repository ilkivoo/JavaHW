package ru.spbau.alyokhina.trie;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.junit.Assert.*;

/** Test for trie */
public class TrieTest {

    /** Checking the existence of an element in an empty file */
    @Test
    public void containsEmptyTree() {
        Trie trie = new Trie();
        assertEquals(false, trie.contains("one"));
    }

    /** Checking the existence of an element if it is really there */
    @Test
    public void containsIfTrue() {
        Trie trie = new Trie();
        trie.add("one");
        assertEquals(true, trie.contains("one"));
    }

    /** Verification of the existence of an element if it does not exist in the trie */
    @Test
    public void containsIfFalse() {
        Trie trie = new Trie();
        trie.add("one");
        assertEquals(false, trie.contains("two"));
    }

    /** Checking the situation when one string is the beginning of another string */
    @Test
    public void containsIfVertexIsEndForOneWord() {
        Trie trie = new Trie();
        trie.add("one");
        trie.add("onebr");
        assertEquals(true, trie.contains("one"));
        assertEquals(true, trie.contains("onebr"));
    }


    /** Adding an element to the trie. We check that the element was added using contains */
    @Test
    public void testAddWithHelpContains() {
        Trie trie = new Trie();
        trie.add("one");
        assertEquals(true, trie.contains("one"));
    }

    /** Adding an element to the boron. And this element already contains it */
    @Test
    public void testAddReturnValuesIsTrue() {
        Trie trie = new Trie();
        assertEquals(true, trie.add("one"));
    }

    /** Adding a new element to the trie */
    @Test
    public void testAddReturnValuesIsFalse() {
        Trie trie = new Trie();
        trie.add("one");
        assertEquals(false, trie.add("one"));
    }

    /** Removing an existing element in the trie */
    @Test
    public void testRemoveReturnValueIsTrue() {
        Trie trie = new Trie();
        trie.add("one");
        trie.add("two");
        trie.add("twobr");
        assertEquals(true, trie.remove("two"));
    }

    /** Removal of an element that does not exist in the trie */
    @Test
    public void testRemoveReturnValueIsFalse() {
        Trie trie = new Trie();
        trie.add("one");
        trie.add("two");
        trie.add("onebr");
        assertEquals(false, trie.remove("four"));
    }

    /** Deleting from an empty trie */
    @Test
    public void testRemoveFromEmptyTree() {
        Trie trie = new Trie();
        assertEquals(false, trie.remove("four"));
    }

    /** Remove from the trie and check that this item is not present with contains */
    @Test
    public void testRemoveWithHelpContains() {
        Trie trie = new Trie();
        trie.add("one");
        trie.add("two");
        trie.add("twobr");
        trie.remove("two");
        assertEquals(false, trie.contains("two"));
    }

    /** Check the number of elements in an empty trie */
    @Test
    public void testSizeEmptyTree() {
        Trie trie = new Trie();
        assertEquals(0, trie.size());
    }

    /** Check the number of elements in the trie */
    @Test
    public void testSize() {
        Trie trie = new Trie();
        trie.add("one");
        trie.add("two");
        trie.add("onebr");
        assertEquals(3, trie.size());
    }


    /** Check the number of elements in the trie after remove */
    @Test
    public void testSizeAfterRemove() {
        Trie trie = new Trie();
        trie.add("one");
        trie.add("two");
        trie.add("onebr");
        trie.remove("one");
        assertEquals(2, trie.size());
    }

    /** Checking the number of rows with a suffix for empty trie */
    @Test
    public void testHowManyStartsWithPrefixEmptyTrie() {
        Trie trie = new Trie();
        assertEquals(0, trie.howManyStartsWithPrefix("one"));
    }

    /** check the number of string starting with one*/
    @Test
    public void testHowManyStartsWithPrefix() {
        Trie trie = new Trie();
        trie.add("one");
        trie.add("two");
        trie.add("onebr");
        trie.add("omnomnom");
        trie.add("onenenenenene");
        assertEquals(3, trie.howManyStartsWithPrefix("one"));
    }

    /** Serialize and deserialize. We check that it turned out the same thing */
    @Test
    public  void  testSerializeAndDeserialize() throws IOException, ClassNotFoundException {
        Trie trie = new Trie();
        trie.add("one");
        trie.add("two");
        trie.add("onebr");
        trie.add("omnomnom");
        trie.add("onenenenenene");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        trie.serialize(baos);

        Trie trieNew = new Trie();
        trieNew.add("four");
        byte[] fromBaos = baos.toByteArray();
        ByteArrayInputStream bais = new ByteArrayInputStream(fromBaos);
        trieNew.deserialize(bais);
        assertEquals(5, trieNew.size());
        assertEquals(true, trieNew.contains("onebr"));
    }
}