package com.linkedlist;

public class LinkedList<T> implements ILinkedList<T> {

    private Node<T> head;
    private Node<T> current;
    private Node<T> tail;

    public LinkedList() {
        this.head = null;
        this.current = null;
        this.tail = null;
    }

    @Override
    public LinkedList<T> add(T item) {
        Node<T> newNode = new Node<>(item);
        if (head == null) {
            head = newNode;
            current = head;
            tail = head;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        return this;
    }

    @Override
    public T current() {
        return current != null ? current.item : null;
    }

    @Override
    public T head() {
        return head != null ? head.item : null;
    }

    @Override
    public T tail() {
        return tail != null ? tail.item : null;
    }

    @Override
    public T removeCurrent() {
        if (current == null) {
            return null;
        }

        T removedData = current.item;
        if (current == head) {
            removeFirst();
        } else {
            Node<T> prev = head;
            while (prev.next != current) {
                prev = prev.next;
            }

            prev.next = current.next;
            if (current == tail) {
                tail = prev;
            }
            current = current.next;
        }

        return removedData;
    }

    private void removeFirst() {
        head = head.next;
        current = head;
        if (head == null) {
            tail = null;
        }
    }

    @Override
    public T next() {
        if (current != null) {
            current = current.next;
        }
        return current();
    }

    @Override
    public boolean hashNext() {
        return current != null && current.next != null;
    }

    private class Node<E> {
        E item;
        Node<E> next;

        Node(E element) {
            this.item = element;
        }
    }
}
