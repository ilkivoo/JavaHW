package ru.spbau.mit.alyokhina;

/**  The structure of the data is stored in a single copy. Implemented using a binary search tree */
public class Set <T extends Comparable> {
    /** Class for tree node */
    private class Node {
        Node left;
        T valueInVertex;
        Node right;
    }

    /** Tree root */
    private Node root;
    /** size of set*/
    private int size = 0;

    /**
     * Add vertex with value in tree
     * @param vertex current vertex
     * @param value we want to add node with this value
     */
    private void addInSet(Node vertex, T value) {
        if (vertex.valueInVertex != value) {
            if (vertex.valueInVertex.compareTo(value) > 0) {
                if (vertex.left == null) {
                    vertex.left = new Node();
                    vertex.left.valueInVertex = value;
                    size++;
                }
                else {
                    addInSet(vertex.left, value);
                }
            }
            else {
                if (vertex.right == null) {
                    vertex.right = new Node();
                    vertex.right.valueInVertex = value;
                    size++;
                }
                else {
                    addInSet(vertex.right, value);
                }
            }
        }
    }

    /** Add value in set */
    public void add (T value) {
        if (root == null) {
            root = new Node();
            root.valueInVertex = value;
            size++;
        }
        else {
            addInSet(root, value);
        }

    }

    /**
     * The presence of an element in the set
     * @param value the presence of an element in the set
     * @return true if this value in set, else false
     */
    public boolean conteins(T value) {
        Node currentVertex = root;
        while (currentVertex != null && currentVertex.valueInVertex!= value) {
            if (currentVertex.valueInVertex.compareTo(value) > 0) {
                currentVertex = currentVertex.left;
            }
            else {
                currentVertex = currentVertex.right;
            }
        }
        return (currentVertex != null);
    }

    /** Get size set */
    public int size() {
        return size;
    }

}
