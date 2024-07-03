package DSA.DynamicProgramming.Basics;

import java.util.Arrays;

public class GridUniquePathsII {
    public static int uniquePathsWithObstacles(int[][] Arr) {
        int[][] Dp=new int[Arr.length][Arr[0].length];
        for(int[] Elem:Dp) {
            Arrays.fill(Elem, -1);
        }
        return CountUniquePathsWithObstacles(Arr, 0, 0,Dp);
    }

    private static int CountUniquePathsWithObstacles(int[][] Arr, int SR, int SC,int[][] Dp) {
        if (SR < 0 || SR >= Arr.length || SC < 0 || SC >= Arr[0].length || Arr[SR][SC] == 1) {
            return 0;
        }
        if (SR == Arr.length - 1 & SC == Arr[0].length - 1) {
            return Dp[SR][SC]=1;
        }
        if(Dp[SR][SC]!=-1){
            return Dp[SR][SC];
        }

        int Right = CountUniquePathsWithObstacles(Arr, SR, SC + 1,Dp);
        int Bottom = CountUniquePathsWithObstacles(Arr, SR + 1, SC,Dp);
        int Res=Right+Bottom;
        return Dp[SR][SC]=Res;
    }

    public static void main(String[] args) {
        int[][] Arr = {{0, 0, 0, 0},
                       {1, 0, 0, 1},
                       {0, 1, 0, 0},
                       {1, 1, 0, 0}};
        System.out.println(uniquePathsWithObstacles(Arr));
    }
}
