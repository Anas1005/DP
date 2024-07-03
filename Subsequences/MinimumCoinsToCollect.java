package DSA.DynamicProgramming.Basics.Subsequences;

import java.util.Arrays;

public class MinimumCoinsToCollect {

    //      Infinite Choices : ==> Pick 0 or Pick 1 or Pick 2 Or.........till...........Pick (Target/Arr[SC])

    static final int INFINITY = 1000000000;
    static int MyCount=0;

    static int minimumCoinsToCollect(int[] Denom, int CoinSumTarget) {
        int n = Denom.length;
        int[][] memo = new int[n][CoinSumTarget + 1];

        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }

        int ResMy = minimumCoinsToCollect(Denom, 0, CoinSumTarget, memo);
        System.out.println("ResMy: "+ResMy+":"+MyCount);
        int ResOther = coinChange(Denom,CoinSumTarget);
        System.out.println("ResOther: "+ResOther+":"+OtherCount);
        return -1;
    }

    static int minimumCoinsToCollect(int[] Arr, int SC, int CoinSumTarget, int[][] memo) {
//        if (SC == Arr.length ) {
//            return CoinSumTarget==0 ? 0 : INFINITY;
//        }
        MyCount++;

        if (SC == Arr.length - 1) {
            return memo[SC][CoinSumTarget] = (CoinSumTarget) % Arr[SC] == 0 ? (CoinSumTarget) / Arr[SC] : INFINITY;
        }

        if (memo[SC][CoinSumTarget] != -1) {
            return memo[SC][CoinSumTarget];
        }

        int MinimumCoinsCollected = INFINITY;

        for (int i = 0; i <= (CoinSumTarget) / Arr[SC]; i++) {
            if (Arr[SC] * i <= CoinSumTarget) {
                MinimumCoinsCollected = Math.min(MinimumCoinsCollected, minimumCoinsToCollect(Arr, SC + 1, CoinSumTarget - Arr[SC] * i, memo) + i);
            }
        }

        memo[SC][CoinSumTarget] = MinimumCoinsCollected;
        return MinimumCoinsCollected;
    }

    static int[] dp;
    static int OtherCount=0;
    public static int coinChange(int[] coins, int amount) {

        dp = new int[amount + 1];
        Arrays.fill(dp, -1);
        int ans = coinCount(coins, amount);
        return (ans == Integer.MAX_VALUE) ?  -1 : ans;
    }

    static int coinCount(int[] coins, int amount) {

        OtherCount++;

        if(amount == 0) {
            return 0;
        }
        if(amount < 0) {
            return Integer.MAX_VALUE;
        }

        if(dp[amount] != -1) {
            return dp[amount];
        }

        int minCoins = Integer.MAX_VALUE;
        for(int i = 0; i < coins.length; i++) {
            int ans = coinCount(coins, amount - coins[i]);

            if(ans != Integer.MAX_VALUE) {

                //we have returned 0 in ans, so now we are updating the ans count
                //hence 1 + ans
                minCoins = Math.min(minCoins, 1 + ans);
            }
        }
        return dp[amount] = minCoins;
    }

    public static void main(String[] args) {
        System.out.println(minimumCoinsToCollect(new int[]{1,2,3}, 712));
    }
}
