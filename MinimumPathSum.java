package DSA.DynamicProgramming.Basics;

import java.util.Arrays;

public class MinimumPathSum {

    static final int INFINITY=1000000000;

    public static int minPathSum(int[][] Arr) {
        int[][] Dp=new int[Arr.length][Arr[0].length];
        for(int[] Elem:Dp) {
            Arrays.fill(Elem, -1);
        }

        int Res= MinPathSum(Arr, 0, 0,Dp);
        for(int[] Elem:Dp){
           System.out.println(Arrays.toString(Elem));
         }
        return Res;
    }

    private static int MinPathSum(int[][] Arr, int SR, int SC,int[][] Dp) {
        if (SR < 0 || SC < 0 || SR >= Arr.length || SC >= Arr[0].length) {
            return INFINITY;
        }
        if (SR == Arr.length - 1 && SC == Arr[0].length - 1) {
            return Dp[SR][SC]=Arr[SR][SC];
        }
        if(Dp[SR][SC]!=-1){
            return Dp[SR][SC];
        }

        int RightMin = MinPathSum(Arr, SR, SC + 1,Dp) + Arr[SR][SC];
        int DownMin =  MinPathSum(Arr, SR+1, SC,Dp) + Arr[SR][SC];
        int Res = Math.min(RightMin, DownMin);

        return Dp[SR][SC]=Res;
    }

    public static void main(String[] args) {
        int[][] Arr = {{1, 3, 1},
                      {1, 5, 1},
                      {4, 2, 1}};
        System.out.println(minPathSum(Arr));
    }
}

