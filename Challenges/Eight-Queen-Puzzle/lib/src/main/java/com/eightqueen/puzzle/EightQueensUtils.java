package com.eightqueen.puzzle;

import java.util.List;

public class EightQueensUtils {
    public static boolean isValid(List<Integer> solution) {
        for (int i = 0; i < solution.size(); i++) {
            for (int j = i + 1; j < solution.size(); j++) {
                // Check if queens are on the same diagonal
                if (Math.abs(solution.get(i) - solution.get(j)) == Math.abs(i - j)) {
                    return false;
                }
            }
        }
        return true;
    }
}
