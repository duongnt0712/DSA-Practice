package com.tree;

import com.tree.exception.DuplicateItemException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.function.BiFunction;

import static org.junit.jupiter.api.Assertions.*;

class BinaryTreeTest {
    private BinaryTree<Integer> tree;

    @BeforeEach
    void setUp() {
        tree = new BinaryTree<>();
    }

    @Test
    void testAddValues() {
        tree.add(5).add(3).add(7);
        assertEquals(5, tree.root());
    }

    @Test
    void testAddDuplicateValue() {
        tree.add(5);
        assertThrows(DuplicateItemException.class, () -> tree.add(5));
    }

    @Test
    void testRemoveNodes() {
        tree.add(5).add(3).add(7).add(6).add(8);
        tree.remove(7);
        assertFalse(tree.containsNode(7));
        tree.remove(5);
        assertEquals(6, tree.root());
    }

    @Test
    void testTransform() {
        tree.add(5).add(3).add(7);

        BiFunction<Integer, Integer, Integer> sumFunction = (sum, value) -> sum + value;
        int result = tree.transform(0, sumFunction);

        assertEquals(15, result);
    }

    @Test
    void testRootOnEmptyTree() {
        assertNull(tree.root());
    }

    @Test
    void testRemoveRootWithChild() {
        tree.add(5).add(3).add(7).add(6).add(8);
        tree.remove(5);
        assertEquals(6, tree.root());
    }
}
