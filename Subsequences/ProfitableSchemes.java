package DSA.DynamicProgramming.Basics.Subsequences;

import java.util.Arrays;

public class ProfitableSchemes {

    private static final int MOD = 1_000_000_007;

    public static int profitableSchemes(int maxMembers, int minProfit, int[] group, int[] profit) {
            int[][][] memo = new int[group.length + 1][maxMembers + 1][minProfit + 1];
            for (int[][] arr : memo) {
                for (int[] subArr : arr) {
                    Arrays.fill(subArr, -1);
                }
            }

            return countTotalProfitableSchemes(group, profit, 0, maxMembers, minProfit, memo);
        }

        private static int countTotalProfitableSchemes(int[] group, int[] profit, int SC, int maxMembers, int minProfit, int[][][] memo) {
            if (SC == group.length) {
                return minProfit <= 0 ? 1 : 0;
            }

            if (memo[SC][maxMembers][minProfit] != -1) {
                return memo[SC][maxMembers][minProfit];
            }

            int PickCurrentCrimeCount = 0;
            if (group[SC] <= maxMembers) {
                PickCurrentCrimeCount = countTotalProfitableSchemes(group, profit, SC + 1, maxMembers - group[SC], Math.max(0, minProfit - profit[SC]), memo);
            }

            int NotPickCurrentCrimeCount = countTotalProfitableSchemes(group, profit, SC + 1, maxMembers, minProfit, memo);

            memo[SC][maxMembers][minProfit] = (PickCurrentCrimeCount + NotPickCurrentCrimeCount) % MOD;
            return memo[SC][maxMembers][minProfit];
        }

    public static void main(String[] args) {
        System.out.println(profitableSchemes(10, 5, new int[]{2, 3, 5}, new int[]{6, 7, 8}));
    }
}
