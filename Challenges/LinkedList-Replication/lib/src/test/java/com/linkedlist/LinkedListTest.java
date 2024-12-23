package com.linkedlist;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LinkedListTest {

    private LinkedList<Integer> list;

    @BeforeEach
    void setUp() {
        list = new LinkedList<>();
    }

    @Test
    void testAdd() {
        list.add(1).add(2).add(3);
        assertEquals(1, list.head());
        assertEquals(3, list.tail());
    }

    @Test
    void testCurrent() {
        list.add(10).add(20);
        assertEquals(10, list.current());
    }

    @Test
    void testNext() {
        list.add(5).add(15).add(25);
        assertEquals(5, list.current());
        list.next();
        assertEquals(15, list.current());
        list.next();
        assertEquals(25, list.current());
    }

    @Test
    void testHasNext() {
        list.add(1).add(2);
        assertTrue(list.hashNext());
        list.next();
        assertFalse(list.hashNext());
    }

    @Test
    void testRemoveCurrent() {
        list.add(1).add(2).add(3);

        assertEquals(1, list.current());
        assertEquals(1, list.removeCurrent());
        assertEquals(2, list.current());
        assertEquals(2, list.removeCurrent());
        assertEquals(3, list.current());
        assertEquals(3, list.removeCurrent());
        assertNull(list.current());
        assertNull(list.head());
        assertNull(list.tail());
    }

    @Test
    void testEmptyList() {
        assertNull(list.head());
        assertNull(list.tail());
        assertNull(list.current());
        assertFalse(list.hashNext());
    }

    @Test
    void testAddAfterTraversal() {
        list.add(1).add(2);
        while (list.hashNext()) {
            list.next();
        }
        list.add(3);
        assertEquals(3, list.tail());
    }
}
