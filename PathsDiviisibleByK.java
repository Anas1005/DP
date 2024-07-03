package DSA.DynamicProgramming.Basics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class PathsDiviisibleByK {
    private static final int MOD = 1_000_000_007;

    public static int numberOfPaths(int[][] Arr, int K) {
        Map<String, int[]> memo = new HashMap<>();
        return numberOfPaths(Arr, 0, 0, K, memo)[0];
    }

    private static int[] numberOfPaths(int[][] Arr, int SR, int SC, int K, Map<String, int[]> memo) {
        if (SR >= Arr.length || SC >= Arr[0].length) {
            return new int[]{};
        }

        String key = SR + "-" + SC;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        if (SR == Arr.length - 1 & SC == Arr[0].length - 1) {
            int[] NumberOfPathRem = new int[K];
            NumberOfPathRem[Arr[SR][SC] % K]++;
            memo.put(key, NumberOfPathRem);
            return NumberOfPathRem;
        }

        int[] CurrCellPathRem = new int[K];

        int[] Right = numberOfPaths(Arr, SR, SC + 1, K, memo);
        if (Right.length != 0) {
            for (int i = 0; i < K; i++) {
                CurrCellPathRem[i] = (CurrCellPathRem[i] + Right[(K - Arr[SR][SC] % K + i) % K]) % MOD;
            }
        }

        int[] Down = numberOfPaths(Arr, SR + 1, SC, K, memo);
        if (Down.length != 0) {
            for (int i = 0; i < K; i++) {
                CurrCellPathRem[i] = (CurrCellPathRem[i] + Down[(K - Arr[SR][SC] % K + i) % K]) % MOD;
            }
        }

        memo.put(key, CurrCellPathRem);
        return CurrCellPathRem;
    }
    public static void main(String[] args) {
        int[][] Arr =
                {{5, 2,4},
                {3, 0, 5},
                {0, 7, 2}};

        System.out.println(numberOfPaths(Arr,1));
    }
}
