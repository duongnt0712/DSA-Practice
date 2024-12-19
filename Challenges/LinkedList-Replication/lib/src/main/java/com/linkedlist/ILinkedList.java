package com.linkedlist;

public interface ILinkedList<T>  {
    ILinkedList<T>  add(T item);
    T current();
    T head();
    T tail();
    T removeCurrent();
    T next();
    boolean hashNext();
}