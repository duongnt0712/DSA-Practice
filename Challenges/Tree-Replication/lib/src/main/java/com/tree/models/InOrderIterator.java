package com.tree.models;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class InOrderIterator<T extends Comparable<T>> implements Iterator<T> {
    private final Deque<Node<T>> deque = new ArrayDeque<>();

    public InOrderIterator(Node<T> root) {
        pushLeftBranch(root);
    }

    private void pushLeftBranch(Node<T> node) {
        while (node != null) {
            deque.push(node);
            node = node.getLeft();
        }
    }

    @Override
    public boolean hasNext() {
        return !deque.isEmpty();
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        
        Node<T> node = deque.pop();
        T value = node.getValue();

        if (node.getRight() != null) {
            pushLeftBranch(node.getRight());
        }
        return value;
    }
}
