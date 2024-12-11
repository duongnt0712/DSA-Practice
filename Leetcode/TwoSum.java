class Solution {
    /**
     * Original Solution: Brute Force
     *  Using nested loop to check every pair of elements whether their sum equals the target.
     *  Array length: n
     *  Total comparisons: n * n
     *  Time complexity: O(n^2)
     */
    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{};
    }

    /**
     * Optimized Solution
     * Using Hashmap, store the number as key and its index as value. Calculate the complement target - currentNum,
     * and check the Hashmap whether the complement exist.
     *  Array length: n
     *  Total comparisons: n
     *  Time complexity: O(n)
     */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> result = new HashMap<>();
        for (int i = 0; i < nums.length; i ++) {
            int complement = target - nums[i];
            if (result.containsKey(complement)) {
                return new int[]{result.get(complement), i};
            }
            result.put(nums[i], i);
        }
        return new int[]{};
    }
}