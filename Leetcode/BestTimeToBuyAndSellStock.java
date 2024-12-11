class Solution {
    /**
     * Storing the lowest price and the highest profit, the loop through the array until reach the end of the array.
     *  Time complexity: O(n)
     */
    public int maxProfit(int[] prices) {
        int profit = 0;
        int lowestPrice = prices[0];
        for (int price : prices) {
            if (price < lowestPrice) {
                lowestPrice = price;
            } else if (price - lowestPrice > profit) {
                profit = price - lowestPrice;
            }
        }
        return profit;
    }
}