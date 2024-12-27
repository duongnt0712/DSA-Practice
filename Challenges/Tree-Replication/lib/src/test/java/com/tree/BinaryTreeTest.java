package com.tree;

import com.tree.exception.DuplicateItemException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BinaryOperator;

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
    void testRootOnEmptyTree() {
        assertNull(tree.root());
    }

    @Test
    void testContainsNode() {
        tree.add(10).add(5).add(15);

        assertTrue(tree.containsNode(10));
        assertTrue(tree.containsNode(5));
        assertTrue(tree.containsNode(15));
        assertFalse(tree.containsNode(20));
    }

    @Test
    void testTransform() {
        tree.add(5).add(3).add(7);

        BinaryOperator<Integer> sumFunction = Integer::sum;
        int result = tree.transform(0, sumFunction);

        assertEquals(15, result);
    }

    @Test
    void testReduceMax() {
        tree.add(10).add(5).add(15).add(20);

        BinaryOperator<Integer> maxOperator = Integer::max;
        int max = tree.reduce(Integer.MIN_VALUE, maxOperator);

        assertEquals(20, max);
    }

    @Test
    void testScanCollectValuesInOrder() {
        tree.add(10).add(5).add(15);

        List<Integer> collectedValues = new ArrayList<>();
        tree.scan(collectedValues::add);

        List<Integer> expectedOrder = List.of(5, 10, 15);
        assertEquals(3, collectedValues.size());
        assertEquals(expectedOrder, collectedValues);
    }

    @Test
    void testToListWithElements() {
        tree.add(10).add(5).add(15);

        List<Integer> list = tree.toList();

        assertTrue(list.containsAll(List.of(5, 10, 15)));
        assertEquals(3, list.size());
    }
}
