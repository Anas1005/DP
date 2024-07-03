package DSA.DynamicProgramming.Basics.StocksDP;

import java.util.Arrays;

public class BuyAndSellStocksII {

    public static int maxProfit(int[] prices) {
        int n = prices.length;
        int[][] memo = new int[n][2]; // 2D array for memoization, [day][buy/sell flag]
        for (int[] Elem : memo) {
            Arrays.fill(Elem, -1);
        }

        return calculateMaxProfit(prices, 0, 1, memo);
    }

    private static int calculateMaxProfit(int[] prices, int currentDay, int isBuyingAllowed, int[][] memo) {
        if (currentDay == prices.length) {
            return 0;
        }

        if (memo[currentDay][isBuyingAllowed] != -1) {
            return memo[currentDay][isBuyingAllowed];
        }

        if (isBuyingAllowed == 1) {
            int buyCurrent = calculateMaxProfit(prices, currentDay + 1, 0, memo) - prices[currentDay];
            int notBuyCurrent = calculateMaxProfit(prices, currentDay + 1, 1, memo);

            int result = Math.max(buyCurrent, notBuyCurrent);
            memo[currentDay][1] = result;
            return result;
        } else {
            int sellCurrent = calculateMaxProfit(prices, currentDay + 1, 1, memo) + prices[currentDay];
            int notSellCurrent = calculateMaxProfit(prices, currentDay + 1, 0, memo);

            int result = Math.max(sellCurrent, notSellCurrent);
            memo[currentDay][0] = result;
            return result;
        }
    }
}
