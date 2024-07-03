package DSA.DynamicProgramming.Basics.StocksDP;

import java.util.*;
public class BuyAndSellStocksIV {
        public int maxProfit(int K, int[] Prices) {
            int n = Prices.length;
            int[][][] memo = new int[n][2][K+1];
            for (int[][] row : memo) {
                for (int[] elem : row) {
                    Arrays.fill(elem, -1);
                }
            }

            return calculateMaxProfit(Prices, 0, 1, K, memo);
        }

        private int calculateMaxProfit(int[] prices, int currentDay, int isBuyingAllowed, int maxTransLimit, int[][][] memo) {

            if (maxTransLimit == 0 || currentDay == prices.length) {
                return 0;
            }

            if (memo[currentDay][isBuyingAllowed][maxTransLimit] != -1) {
                return memo[currentDay][isBuyingAllowed][maxTransLimit];
            }

            if (isBuyingAllowed == 1) {
                int buyCurrent = calculateMaxProfit(prices, currentDay + 1, 0, maxTransLimit, memo) - prices[currentDay];
                int notBuyCurrent = calculateMaxProfit(prices, currentDay + 1, 1, maxTransLimit, memo);

                int result = Math.max(buyCurrent, notBuyCurrent);
                memo[currentDay][1][maxTransLimit] = result;
                return result;
            } else {
                int sellCurrent = calculateMaxProfit(prices, currentDay + 1, 1, maxTransLimit - 1, memo) + prices[currentDay];
                int notSellCurrent = calculateMaxProfit(prices, currentDay + 1, 0, maxTransLimit, memo);

                int result = Math.max(sellCurrent, notSellCurrent);
                memo[currentDay][0][maxTransLimit] = result;
                return result;
            }

    }
}
