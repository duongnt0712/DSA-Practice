package com.tree;

import java.util.Iterator;
import java.util.Stack;

public class InOrderIterator<T extends Comparable<T>> implements Iterator<T> {
    private final Stack<Node<T>> stack = new Stack<>();

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
