package com.tree.exceptions;

/**
 * Throw DuplicateItemException if the Item is existing
 */
public class DuplicateItemException extends RuntimeException {
    public DuplicateItemException() {}
    public DuplicateItemException(String message) { super(message); }
    public DuplicateItemException(Throwable throwable) { super(throwable); }
}
