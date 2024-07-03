package DSA.DynamicProgramming.Basics.Subsequences;

import java.util.ArrayList;
import java.util.Arrays;

public class TotalWaysToCollectCoins {

    static int GetPathsCount = 0;
    static int CountPathsCount = 0;

    public static int change(int[] Denom, int CoinSumTarget) {
        int[][] memo = new int[Denom.length][CoinSumTarget + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        ArrayList<String> allPossibleCoinPaths = getCoinsPathToCollectCoins(Denom, 0, CoinSumTarget);
        System.out.println(allPossibleCoinPaths);
        int totalWays = countTotalWaysToCollectCoins(Denom, 0, CoinSumTarget,memo);
        System.out.println(totalWays + ":" + CountPathsCount);

//        for(int[] Elem:memo){
//            System.out.println(Arrays.toString(Elem));
//        }
        return -1;
    }

    public static ArrayList<String> getCoinsPathToCollectCoins(int[] Arr, int SC, int CoinSumTarget) {
        if (SC == Arr.length - 1) {
            ArrayList<String> temp = new ArrayList<>();
            if (CoinSumTarget % Arr[SC] == 0) {
                temp.add(String.valueOf(currentCoinsCollected(Arr[SC], CoinSumTarget / Arr[SC])));
            }
            return temp;
        }
        GetPathsCount++;

        ArrayList<String> res = new ArrayList<>();
        for (int i = 0; i <= CoinSumTarget / Arr[SC]; i++) {
            String currentCoinsCollected = currentCoinsCollected(Arr[SC], i);
            if (Arr[SC] * i <= CoinSumTarget) {
                ArrayList<String> otherCoinPaths = getCoinsPathToCollectCoins(Arr, SC + 1, CoinSumTarget - Arr[SC] * i);
                for (String path : otherCoinPaths) {
                    res.add(currentCoinsCollected + path);
                }
            }
        }
        return res;
    }

    public static int countTotalWaysToCollectCoins(int[] Arr, int SC, int CoinSumTarget, int[][] memo) {
        if (SC == Arr.length - 1) {
            return memo[SC][CoinSumTarget]=CoinSumTarget % Arr[SC] == 0 ? 1 : 0;
        }


        if (memo[SC][CoinSumTarget] != -1) {
            System.out.println("Hello");
            return memo[SC][CoinSumTarget];
        }

        CountPathsCount++;

        int totalWays = 0;
        for (int i = 0; i <= CoinSumTarget / Arr[SC]; i++) {
            if (Arr[SC] * i <= CoinSumTarget) {
                totalWays += countTotalWaysToCollectCoins(Arr, SC + 1, CoinSumTarget - Arr[SC] * i, memo);
            }
        }

        memo[SC][CoinSumTarget] = totalWays;
        return totalWays;
    }

    public static String currentCoinsCollected(int CoinValue, int Count) {
        return String.valueOf(CoinValue).repeat(Math.max(0, Count));
    }

    public static void main(String[] args) {
        System.out.println(change(new int[]{1,2,3}, 4));
    }
}
