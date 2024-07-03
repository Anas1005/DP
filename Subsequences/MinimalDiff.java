package DSA.DynamicProgramming.Basics.Subsequences;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MinimalDiff {

    static int RecCount=0;
    static int MapCount=0;
    static int TwoDPCount=0;

    static int GFGCount=0;


    static final int INFINITY = 1000000000;

    static int findMinimalDiff(int[] arr) {
        int totalSum = Arrays.stream(arr).sum();

        // Create a memoization map
        Map<String, Integer> memoMap = new HashMap<>();

        int resultMap = findMinimalDiffWithMap(arr, 0, 0, 0, memoMap);
        System.out.println("Map: " + resultMap+":"+MapCount);


        int[][] memo2D = new int[arr.length+1][totalSum + 1];
        for (int i = 0; i < arr.length; i++) {
            Arrays.fill(memo2D[i], -1);
        }
        int result2D = findMinimalDiffWith2D(arr, 0, 0, 0, memo2D);
        System.out.println("2D: " + result2D+":"+TwoDPCount);

        int resultNormal = findMinimalDiffRecursive(arr, 0, 0, 0);
        System.out.println("Recursive: " + resultNormal +":"+RecCount);

        int resultGFG = findMinRec(arr, arr.length, 0, totalSum);
        System.out.println("GFG: " + resultGFG+":"+GFGCount);


        return -1;
    }

    static int findMinimalDiffRecursive(int[] arr, int currentIndex, int subSum1, int subSum2) {
        if (currentIndex == arr.length) {
                return Math.abs(subSum1 - subSum2);

        }
        RecCount++;

        // Include the current element in the first subset
        int sub1Min = findMinimalDiffRecursive(arr, currentIndex + 1, subSum1 + arr[currentIndex], subSum2);

        // Include the current element in the second subset
        int sub2Min = findMinimalDiffRecursive(arr, currentIndex + 1, subSum1, subSum2 + arr[currentIndex]);

        // Return the minimal difference only when the current element is included in one subset
        return Math.min(sub1Min, sub2Min);
    }



    static int findMinimalDiffWithMap(int[] arr, int currentIndex, int subSum1, int subSum2, Map<String, Integer> memo) {
        if (currentIndex == arr.length) {
            return Math.abs(subSum1 - subSum2);
        }
        MapCount++;

        String key = currentIndex + "|" + subSum1;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        int sub1Min = findMinimalDiffWithMap(arr, currentIndex + 1, subSum1 + arr[currentIndex], subSum2, memo);
        int sub2Min = findMinimalDiffWithMap(arr, currentIndex + 1, subSum1, subSum2 + arr[currentIndex], memo);

        int minResult = Math.min(sub1Min, sub2Min);
        memo.put(key, minResult);

        return minResult;
    }

    static int findMinimalDiffWith2D(int[] arr, int currentIndex, int subSum1, int subSum2, int[][] memo) {
        if (currentIndex == arr.length) {
            return Math.abs(subSum1 - subSum2);
        }
        TwoDPCount++;

        if (memo[currentIndex][subSum1] != -1) {
            return memo[currentIndex][subSum1];
        }

        int sub1Min = findMinimalDiffWith2D(arr, currentIndex + 1, subSum1 + arr[currentIndex], subSum2, memo);
        int sub2Min = findMinimalDiffWith2D(arr, currentIndex + 1, subSum1, subSum2 + arr[currentIndex], memo);

        memo[currentIndex][subSum1] = Math.min(sub1Min, sub2Min);

        return memo[currentIndex][subSum1];
    }

    public static int findMinRec(int[] arr, int i, int sumCalculated, int sumTotal) {
        if (i == 0)
            return Math.abs((sumTotal - sumCalculated) - sumCalculated);

        GFGCount++;
        return Math.min(
                findMinRec(arr, i - 1, sumCalculated + arr[i - 1], sumTotal),
                findMinRec(arr, i - 1, sumCalculated, sumTotal));
    }

    public static void main(String[] args) {
        System.out.println(findMinimalDiff(new int[]{1,4,6,-1}));
    }
}
