package ru.spbau.mit.alyokhina.List;

/**
 * Двусвязный список. Хранятся ссылки на предка и следующего.
 */
public class List {
    private Node head = null;
    private int size = 0;
    /**  элементы двусвязного списка */
    private class Node {
        private String value;
        private String key;
        private Node next;
        private Node prev;

        private Node(String newKey, String newValue, Node newNext, Node newPrev) {
            key = newKey;
            value = newValue;
            next = newNext;
            prev = newPrev;
        }
    }

    /**
     * @param str по этой строке мы надем node
     * @return node, key которой = str
     */
    private Node getNode(String str) {
        Node node = head;
        while (node != null) {
            if (node.key.equals(str)) {
                return node;
            }
            node = node.next;
        }
        return null;
    }

    /**
     * Создает Node со значениями key, value
     * Добавляет Node  в начало списка
     */
    public void pushFront(String key, String value) {
        if (head == null) {
            head = new Node(key, value, null, null);
        } else {
            Node newNode = new Node(key, value, head, null);
            head.prev = newNode;
            head = newNode;
        }
        size++;
    }

    /** @param key, ключ, по которому будет удалена строка */
    public void pop(String key) {
        Node node = getNode(key);
        if (node == null) {
            throw new RuntimeException("pop Node with Key = NULL");
        }
        if (node.prev != null) {
            (node.prev).next = node.next;
        } else {
            head = node.next;
        }
        if (node.next != null) {
            (node.next).prev = node.prev;
        }
        size--;
    }

    /**
     * @param str по этой строке мы найдем значение
     * @return значение, которое хранится в списке по ключу str
     */
    public String getValue(String str) {
        Node node = head;
        while (node != null) {
            if (node.key.equals(str)) {
                return node.value;
            }
            node = node.next;
        }
        return null;
    }

    /** Очищает список */
    public void clear() {
        head = null;
        size = 0;
    }

    /**
     * @return Ключ у головы списка
     */
    public String getHeadKey() {
        if (head != null) {
            return head.key;
        } else {
            return null;
        }
    }

    /** @return значение у головы списка */
    public String getHeadValue() {
        if (head != null) {
            return head.value;
        } else {
            return null;
        }
    }

    public int getSize() {
        return size;
    }
}
