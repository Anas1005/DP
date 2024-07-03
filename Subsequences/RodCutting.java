package DSA.DynamicProgramming.Basics.Subsequences;

import java.util.Arrays;

public class RodCutting {

    //      Infinite Choices : ==> Pick 0 or Pick 1 or Pick 2 Or.........till...........Pick (Target/Arr[SC])
    static final int MINUS_INFINITY = -1000000000;
    static int RecCount=0;
    static int DpCount=0;
    public static int rodCutting(int[] profit, int desiredLength) {
        int[][] memo = new int[profit.length + 1][desiredLength + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }

        int resDP = maxProfitFromRodCuttingDP(1, profit, desiredLength, memo);
        System.out.println("ResDP: " + resDP);
        return resDP;
    }

    private static int maxProfitFromRodCuttingDP(int currentLengthSegment, int[] profit, int desiredLength, int[][] memo) {
        if (currentLengthSegment > desiredLength) {
            return 0;
        }

        if (memo[currentLengthSegment][desiredLength] != -1) {
            return memo[currentLengthSegment][desiredLength];
        }

        int maxProfit = MINUS_INFINITY;
        for (int i = 0; i <= desiredLength / currentLengthSegment; i++) {
            if (currentLengthSegment * i <= desiredLength) {
                maxProfit = Math.max(maxProfit, maxProfitFromRodCuttingDP(currentLengthSegment + 1, profit, desiredLength - currentLengthSegment * i, memo) + profit[currentLengthSegment - 1] * i);
            }
        }

        memo[currentLengthSegment][desiredLength] = maxProfit;
        return maxProfit;
    }

    public static void main(String[] args) {
        int[] profit = {1, 5, 8, 9, 10, 17, 17, 20};
        int desiredLength = 8;
        System.out.println(rodCutting(profit, desiredLength));
    }
}
