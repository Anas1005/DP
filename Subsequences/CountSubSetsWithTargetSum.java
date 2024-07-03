package DSA.DynamicProgramming.Basics.Subsequences;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CountSubSetsWithTargetSum {

    static int Calls=0;

    private static final int MOD = 1_000_000_007;
    static int mapCount=0;
    static int TwoDPCount=0;

    public static int countSubsetsWithTargetSum(int[] Arr, int Target) {
        int n = Arr.length;
        int[][] memo2D = new int[n + 1][Target + 1];
        for (int[] row : memo2D) {
            Arrays.fill(row, -1);
        }
        Map<String, Integer> memoMap = new HashMap<>();
        int Res1= countSubsetsWithTargetSumMap(Arr, 0, Target, 0, memoMap);
        System.out.println("Map: " + Res1+":"+mapCount);
        int Res2= countSubsetsWithTargetSum2D(Arr, 0, Target, memo2D);
        System.out.println("2D: " + Res2+":"+TwoDPCount);
        return -1;

    }

     static int countSubsetsWithTargetSumMap(int[] Arr, int SC, int Target, int subSum, Map<String, Integer> memo) {
        if(subSum>Target){
            return 0;
        }


         Calls++;

        if (SC == Arr.length) {
            return subSum == Target ? 1 : 0;
        }

          mapCount++;
        // Check if the result for the current state is already memoized
        String key = SC + "|" + subSum;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        // Include the current element in the subset if (Arr[SC] <= Target) {
        int pickCurrent = countSubsetsWithTargetSumMap(Arr, SC + 1, Target, (subSum + Arr[SC]) % MOD, memo);
        // Exclude the current element from the subset
        int notPickCurrent = countSubsetsWithTargetSumMap(Arr, SC + 1, Target, subSum, memo);

        // Calculate the result using modular arithmetic
        int result = (pickCurrent + notPickCurrent) % MOD;

        // Memoize the result for the current state
        memo.put(key, result);

        return result;
    }
    private static int countSubsetsWithTargetSum2D(int[] Arr, int SC, int Target, int[][] memo) {
        if (Target == 0) {
            return 1; // Found a subset with the target sum
        }

        if (SC == Arr.length) {
            return 0; // Reached the end of the array
        }
        TwoDPCount++;

        // Check if the result for the current state is already memoized
        if (memo[SC][Target] != -1) {
            return memo[SC][Target];
        }

        int includeCurrent = 0;
        if (Arr[SC] <= Target) {
            // Include the current element in the subset
            includeCurrent = countSubsetsWithTargetSum2D(Arr, SC + 1, Target - Arr[SC], memo);
        }

        // Exclude the current element from the subset
        int excludeCurrent = countSubsetsWithTargetSum2D(Arr, SC + 1, Target, memo);

        // Calculate the result
        int result = includeCurrent + excludeCurrent;

        // Memoize the result for the current state
        memo[SC][Target] = result;

        return result;
    }

    public static void main(String[] args) {
        System.out.println(countSubsetsWithTargetSum(new int[]{1}, 2));
    }
}
