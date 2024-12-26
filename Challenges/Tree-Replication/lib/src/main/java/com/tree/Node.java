package com.tree;

import lombok.Data;

@Data
public class Node<T> {
    private T value;
    private Node<T> left;
    private Node<T> right;

    public Node(T value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }
}
