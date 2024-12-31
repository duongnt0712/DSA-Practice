package com.tree;

import com.tree.exception.DuplicateItemException;

import java.util.Iterator;

public class BinaryTree<T extends Comparable<T>> implements Tree<T> {

    private Node<T> root;

    @Override
    public Tree<T> add(T value) {
        root = addRecursive(root, value);
        return this;
    }

    private Node<T> addRecursive(Node<T> current, T value) {
        if (current == null) {
            return new Node<>(value);
        }

        int compareResult = value.compareTo(current.getValue());
        if (compareResult == 0) {
            throw new DuplicateItemException("Duplicate item: " + value);
        }

        if (compareResult < 0) {
            current.setLeft(addRecursive(current.getLeft(), value));
        } else {
            current.setRight(addRecursive(current.getRight(), value));
        }

        return current;
    }

    @Override
    public Tree<T> remove(T value) {
        root = removeRecursive(root, value);
        return this;
    }
    private Node<T> removeRecursive(Node<T> current, T value) {
        if (current == null) {
            return null;
        }

        int compareResult = value.compareTo(current.getValue());

        if (compareResult < 0) {
            current.setLeft(removeRecursive(current.getLeft(), value));
        } else if (compareResult > 0) {
            current.setRight(removeRecursive(current.getRight(), value));
        } else {
            current = handleNodeRemoval(current);
        }

        return current;
    }
    private Node<T> handleNodeRemoval(Node<T> current) {
        if (current.getLeft() == null) {
            return current.getRight();
        }
        if (current.getRight() == null) {
            return current.getLeft();
        }

        T smallestValue = findSmallestValue(current.getRight());
        current.setValue(smallestValue);
        current.setRight(removeRecursive(current.getRight(), smallestValue));
        return current;
    }

    private T findSmallestValue(Node<T> root) {
        return root.getLeft() == null ? root.getValue() : findSmallestValue(root.getLeft());
    }

    @Override
    public T root() {
        return root == null ? null : root.getValue();
    }

    public boolean containsNode(T value) {
        return containsNodeRecursive(root, value);
    }

    private boolean containsNodeRecursive(Node<T> current, T value) {
        if (current == null) {
            return false;
        }
        int compareResult = value.compareTo(current.getValue());
        return compareResult == 0 || ( compareResult < 0
                ? containsNodeRecursive(current.getLeft(), value)
                : containsNodeRecursive(current.getRight(), value));
    }

    @Override
    public Iterator<T> iterator() {
        return new InOrderIterator<>(root);
    }
}
