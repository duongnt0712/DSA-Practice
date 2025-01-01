package com.tree.models;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.Stack;

public class InOrderIterator<T extends Comparable<T>> implements Iterator<T> {

    private final Deque<Node<T>> stack = new ArrayDeque<>();

    public InOrderIterator(Node<T> root) {
        pushLeftBranch(root);
    }

    private void pushLeftBranch(Node<T> node) {
        while (node != null) {
            stack.push(node);
            node = node.getLeft();
        }
    }

    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    @Override
    public T next() {
        Node<T> node = stack.pop();
        T value = node.getValue();
        pushLeftBranch(node.getRight());
        return value;
    }
}
