package ru.spbau.alyokhina.trie;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/** бор. Структура данных для хранения набора строк, представляющая из себя подвешенное дерево с символами на рёбрах */
public class Trie implements Serializable{

    /** класс для вершины. В нем хранится map ( ребро по которому попадем в соседнюю вершину, сама вершина) */
    private class Node implements Serializable{
        private Map<Character, Node> childrenInTrie = new HashMap<Character, Node>();
        private boolean endOfWord = false;
        private int numWordWithPrefix = 0;
    }

    /**корень дерева*/
    private Node root = new Node();

    /**количесвтво строк в боре*/
    private int size = 0;

    /**
     * проверка на наличие строки в боре.
     * @param element строка, кторую мы хотим проверить
     * время O(|element|)
     * @return true, если строка найдена, иначе false
     */
    public boolean contains(String element) {
        Node vertex = root;
        for (char c : element.toCharArray()) {
            if (vertex.childrenInTrie.containsKey(c) && vertex.numWordWithPrefix > 0) {
                vertex = vertex.childrenInTrie.get(c);
            }
            else {
                return false;
            }
        }
        return vertex.endOfWord;
    }

    /**
     * добавление строки в бор.
     * @param element строка, которую мы хотим добавить
     * время O(|element|)
     * @return true, если этой строки раньше не было, иначе false
     */
    public boolean add(String element) {
        if (contains(element)) {
            return false;
        }
        size ++;
        Node vertex = root;
        for (char c : element.toCharArray()) {
            if (!vertex.childrenInTrie.containsKey(c)) {
                vertex.childrenInTrie.put(c, new Node());
            }
            vertex.numWordWithPrefix ++;
            vertex = vertex.childrenInTrie.get(c);
        }
        vertex.numWordWithPrefix ++;
        vertex.endOfWord = true;
        return  true;
    }


    /**удаление строки из бора.
     * @param element строка, которую мы хотим удалить
     * время O(|element|)
     * @return false, если такой строки в боре нет, false - иначе
     */
    public boolean remove(String element) {
        if (!contains(element)) {
            return false;
        }
        size --;
        Node vertex = root;
        for (char c : element.toCharArray()) {
            vertex.numWordWithPrefix --;
            vertex = vertex.childrenInTrie.get(c);
        }
        vertex.numWordWithPrefix --;
        vertex.endOfWord = false;
        return true;
    }

    /**количество элементов в боре
     * время О(1)
     */
    public int size() {
        return size;
    }


    /** количество строк в боре с данным префиксом
     * @param prefix cтрока, для которой найдется количество строк в боре с префиксом = prefix
     * время O(|element|)
     */
    public int howManyStartsWithPrefix(String prefix) {
        if (!contains(prefix)) {
            return 0;
        }
        Node vertex = root;
        for (char c : prefix.toCharArray()) {
            vertex = vertex.childrenInTrie.get(c);
        }
        return vertex.numWordWithPrefix;
    }

    /**сериализует  бор в введенный поток
     * @param out поток для вывода
     */
    public void serialize(OutputStream out) throws IOException {
        ObjectOutputStream objOutStream = new ObjectOutputStream(out);
        objOutStream.writeObject(this);
    }

    /**десериализует бор из данного потока. И сохранит в текущий
     * @param in поток для чтения
     */
    public void deserialize(InputStream in) throws IOException, ClassNotFoundException {
        ObjectInputStream objInStream = new ObjectInputStream(new BufferedInputStream(in));
        Trie trieNew = ((Trie) objInStream.readObject());
        root = trieNew.root;
        size = trieNew.size;
    }
}