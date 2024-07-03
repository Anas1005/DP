package DSA.DynamicProgramming.Basics.Subsequences;

import java.util.Arrays;
import java.util.Currency;

public class LongestIncreasingSubsequence {

    static final int MINUS_INFINITY = -1000000000;

    public static int lengthOfLIS(int[] Arr) {
        int[][] memo = new int[Arr.length][Arr.length + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        int Res = lengthOfLIS(Arr, 0, -1, memo);
        for(int[] Elem : memo){
            System.out.println(Arrays.toString(Elem));
        }

        return Res;
    }

    public static int lengthOfLIS(int[] Arr, int SC, int PrevIndex, int[][] memo) {
        if (SC >= Arr.length) {
            return 0;
        }

        if (memo[SC][PrevIndex + 1] != -1) {
            return memo[SC][PrevIndex + 1];
        }

        int PickLIS = MINUS_INFINITY;
        if (PrevIndex == -1 || Arr[SC] > Arr[PrevIndex]) {
            PickLIS =  1+ lengthOfLIS(Arr, SC + 1, SC, memo);
        }

        int NotPickLIS = lengthOfLIS(Arr, SC + 1, PrevIndex, memo);

        int result = Math.max(PickLIS, NotPickLIS);
        memo[SC][PrevIndex + 1] = result;

        return result;
    }

    public static int lengthOfLISByTab(int[] Arr){

        int Dp[] = new int[Arr.length];
        Arrays.fill(Dp,1);

        int MaxLIS=1;
        for(int current = 0 ; current<=Arr.length-1;current++){
            for(int prev = 0 ; prev<=current-1;prev++){
                if(Arr[prev]<Arr[current]){
                    Dp[current]= Math.max(Dp[current],1+Dp[prev]);
                }
            }
            MaxLIS=Math.max(MaxLIS,Dp[current]);
        }

        return MaxLIS  ;
    }

    public static int countLISByTab(int[] Arr){

        int Dp[] = new int[Arr.length];
        int Count[] = new int[Arr.length];
        Arrays.fill(Dp,1);
        Arrays.fill(Count,1);

        int MaxLIS=1;
        for(int current = 0 ; current<=Arr.length-1;current++) {
            for (int prev = 0; prev <= current - 1; prev++) {
                if (Arr[prev] < Arr[current] && 1 + Dp[prev] > Dp[current]) {
                    Dp[current] = 1 + Dp[prev];
                    Count[current] = Count[prev];
                } else if (Arr[prev] < Arr[current] && 1 + Dp[prev] == Dp[current]) {
                    Count[current] += Count[prev];

                }
            }
            MaxLIS = Math.max(MaxLIS, Dp[current]);
        }

        int LISCount=0;
        for(int i=0;i< Dp.length;i++){
            if(Dp[i]==MaxLIS){
                LISCount+=Count[i];
            }
        }

        return LISCount  ;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLIS(new int[]{1,3,5,4,7}));
    }
}
