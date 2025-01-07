package com.eightqueen.puzzle;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class EightQueensPuzzle {

    public List<List<Integer>> solveEightQueens() {
        return findSolutions(8);
    }

    public List<List<Integer>> solveNQueens(int n) {
        return findSolutions(n);
    }

    private List<List<Integer>> findSolutions(int n) {
        return permute(IntStream.range(0, n).boxed().collect(Collectors.toList()))
                .stream()
                .filter(EightQueensUtils::isValid)
                .collect(Collectors.toList());
    }

    private List<List<Integer>> permute(List<Integer> nums) {
        if (nums.isEmpty()) {
            List<List<Integer>> result = new ArrayList<>();
            result.add(new ArrayList<>());
            return result;
        }

        List<List<Integer>> permutations = new ArrayList<>();
        for (int i = 0; i < nums.size(); i++) {
            Integer current = nums.remove(i);
            List<List<Integer>> remainingPermutations = permute(nums);
            for (List<Integer> permutation : remainingPermutations) {
                permutation.add(0, current);
            }
            permutations.addAll(remainingPermutations);
            nums.add(i, current);
        }
        return permutations;
    }
}
