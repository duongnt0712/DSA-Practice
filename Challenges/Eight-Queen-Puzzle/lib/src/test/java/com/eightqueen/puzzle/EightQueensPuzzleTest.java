package com.eightqueen.puzzle;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EightQueensPuzzleTest {

    @Test
    public void testSolveEightQueens() {
        EightQueensPuzzle eightQueensPuzzle = new EightQueensPuzzle();

        List<List<Integer>> solutions = eightQueensPuzzle.solveEightQueens();

        assertEquals(92, solutions.size());

        for (List<Integer> solution : solutions) {
            assertValidSolution(solution);
        }
    }

    private void assertValidSolution(List<Integer> solution) {
        for (int i = 0; i < solution.size(); i++) {
            for (int j = i + 1; j < solution.size(); j++) {
                // Not in the same row
                assertTrue(solution.get(i) != solution.get(j));
                // Not in the same diagonal
                assertTrue(Math.abs(solution.get(i) - solution.get(j)) != Math.abs(i - j));
            }
        }
    }

    @Test
    public void testEmptySolutionForSmallerBoard() {
        // Testing for smaller board sizes (e.g., n=2, n=3)
        EightQueensPuzzle smallerQueens = new EightQueensPuzzle();
        List<List<Integer>> solutions2 = smallerQueens.solveNQueens(2);
        List<List<Integer>> solutions3 = smallerQueens.solveNQueens(3);

        // No solutions should exist for n=2 and n=3
        assertEquals(0, solutions2.size());
        assertEquals(0, solutions3.size());
    }

    @Test
    public void testNoDuplicateSolutions() {
        EightQueensPuzzle eightQueensPuzzle = new EightQueensPuzzle();
        List<List<Integer>> solutions = eightQueensPuzzle.solveEightQueens();

        // Ensure no duplicate solutions exist
        long uniqueCount = solutions.stream().distinct().count();
        assertEquals(solutions.size(), uniqueCount);
    }

    @Test
    public void testSolutionFormat() {
        EightQueensPuzzle eightQueensPuzzle = new EightQueensPuzzle();
        List<List<Integer>> solutions = eightQueensPuzzle.solveEightQueens();

        for (List<Integer> solution : solutions) {
            assertEquals(8, solution.size());
            solution.forEach(col -> assertTrue(col >= 0 && col < 8));
        }
    }

    @Test
    public void testInvalidSolutionDetection() {
        List<Integer> invalidSolution = List.of(0, 1, 2, 3, 4, 5, 6, 7);

        assertThrows(AssertionError.class, () -> assertValidSolution(invalidSolution));
    }
}
