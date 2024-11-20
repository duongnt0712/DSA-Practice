class Solution {
    /**
     * Using Set and loop through the array and check if the number exist in the Set.
     *  Time complexity: O(n)
     */
    public boolean containsDuplicate(int[] nums) {
        boolean result = false;
        Set<Integer> store = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (store.contains(nums[i])) {
                result = true;
                break;
            }
            store.add(nums[i]);
        }
        return result;
    }
}