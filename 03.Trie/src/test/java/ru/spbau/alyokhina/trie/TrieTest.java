package ru.spbau.alyokhina.trie;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.junit.Assert.*;

/**тест для бора*/
public class TrieTest {
    /**проверка существования элемента в пустом файле*/
    @Test
    public void containsEmptyTree() {
        Trie trie = new Trie();
        assertEquals(false, trie.contains("one"));
    }

    /**проверка существования элемента, если он действительно там лежит*/
    @Test
    public void containsIfTrue() {
        Trie trie = new Trie();
        trie.add("one");
        assertEquals(true, trie.contains("one"));
    }

    /**проверка существования элемента, если его нет в боре*/
    @Test
    public void containsIfFalse() {
        Trie trie = new Trie();
        trie.add("one");
        assertEquals(false, trie.contains("two"));
    }

    /**добавление элемента в бор. Проверяем что добавили элемент с помощью contains */
    @Test
    public void testAddWithHelpContains() {
        Trie trie = new Trie();
        trie.add("one");
        assertEquals(true, trie.contains("one"));
    }

    /**добавление элемента в бор. А этот элемент уже в нем содержится*/
    @Test
    public void testAddReturnValuesIsTrue() {
        Trie trie = new Trie();
        assertEquals(true, trie.add("one"));
    }

    /**добавление нового элемента в бор*/
    @Test
    public void testAddReturnValuesIsFalse() {
        Trie trie = new Trie();
        trie.add("one");
        assertEquals(false, trie.add("one"));
    }

    /**удаление существующего в боре элемента */
    @Test
    public void testRemoveReturnValueIsTrue() {
        Trie trie = new Trie();
        trie.add("one");
        trie.add("two");
        trie.add("twobr");
        assertEquals(true, trie.remove("two"));
    }

    /**удаление несуществующего в боре элемента */
    @Test
    public void testRemoveReturnValueIsFalse() {
        Trie trie = new Trie();
        trie.add("one");
        trie.add("two");
        trie.add("onebr");
        assertEquals(false, trie.remove("four"));
    }

    /**удаление из пустого списка */
    @Test
    public void testRemoveFromEmptyTree() {
        Trie trie = new Trie();
        assertEquals(false, trie.remove("four"));
    }

    /** удаляем из бора и проверяем, что этого элемента нет с помощью contains*/
    @Test
    public void testRemoveWithHelpContains() {
        Trie trie = new Trie();
        trie.add("one");
        trie.add("two");
        trie.add("twobr");
        trie.remove("two");
        assertEquals(false, trie.contains("two"));
    }

    /**проверяем количество элементов в пустом боре */
    @Test
    public void testSizeEmptyTree() {
        Trie trie = new Trie();
        assertEquals(0, trie.size());
    }

    /** проверяем количество элементов в боре */
    @Test
    public void testSize() {
        Trie trie = new Trie();
        trie.add("one");
        trie.add("two");
        trie.add("onebr");
        assertEquals(3, trie.size());
    }

    /** проверяем размер после удаления */
    @Test
    public void testSizeAfterRemove() {
        Trie trie = new Trie();
        trie.add("one");
        trie.add("two");
        trie.add("onebr");
        trie.remove("one");
        assertEquals(2, trie.size());
    }


    /** проверка количества строк с суффиксом у пустого бора */
    @Test
    public void testHowManyStartsWithPrefixEmptyTrie() {
        Trie trie = new Trie();
        assertEquals(0, trie.howManyStartsWithPrefix("one"));
    }

    /** проверяем количество строк начинающихся на one */
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

    /** сериализуем и десериализуем. Проверяем, что получилось одно и тоже */
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