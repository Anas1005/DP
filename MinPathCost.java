package DSA.DynamicProgramming.Basics;

import java.util.Arrays;

public class MinPathCost {
    public static int minPathCost(int[][] Arr, int[][] MoveCost) {
        int[][] Dp=new int[Arr.length][Arr[0].length];
        for(int[] Elem:Dp) {
            Arrays.fill(Elem, -1);
        }

        int Res=Integer.MAX_VALUE;
        for(int j=0;j<Arr[0].length;j++){
            Res=Math.min(Res,minPathCostByMemoization(Arr,0,j,MoveCost,Dp));
        }
        return Res;
    }

    private static int minPathCostByMemoization(int[][] Arr, int SR, int SC, int[][] MoveCost,int[][] Dp) {
        if (SR == Arr.length - 1) {
            return Dp[SR][SC] = Arr[SR][SC];
        }

        if (Dp[SR][SC] != -1) {
            return Dp[SR][SC];
        }

        int CellValue = Arr[SR][SC];
        int Res = Integer.MAX_VALUE;
        for (int j = 0; j < Arr[0].length; j++) {
            int MinPathCostForEachCell = minPathCostByMemoization(Arr, SR + 1, j, MoveCost, Dp) + CellValue + MoveCost[CellValue][j];
            Res = Math.min(Res, MinPathCostForEachCell);
        }
        return Dp[SR][SC] = Res;
    }

    public static int minPathCostByTabulation(int[][] Arr, int[][] MoveCost) {
        int[][] Dp = new int[Arr.length][Arr[0].length];
        Dp[Arr.length - 1]= Arr[Arr.length - 1];

        for (int SR = Arr.length - 2; SR >= 0; SR--) {
            for (int SC = 0; SC < Arr[0].length; SC++) {
                Dp[SR][SC] = minCostForEachCell(Arr, MoveCost, SR, SC, Dp);
            }
        }

        int MinPathCost = Integer.MAX_VALUE;
        for (int j = 0; j < Dp[0].length; j++) {
            MinPathCost = Math.min(MinPathCost, Dp[0][j]);
        }
        return MinPathCost;
    }

    private static int minCostForEachCell(int[][] Arr, int[][] MoveCost, int SR, int SC, int[][] Dp) {
        int CellValue = Arr[SR][SC];
        int Min = Integer.MAX_VALUE;
        for (int j = 0; j < Arr[0].length; j++) {
            Min = Math.min(Min, CellValue + Dp[SR+1][j] + MoveCost[CellValue][j]);
        }
        return Min;
    }


    public static int minPathCostByMostOptimized(int[][] Arr, int[][] MoveCost) {
        int[] Helper = new int[Arr[0].length];
        for (int Col = 0; Col < Arr[0].length; Col++) {
            Helper[Col] = Arr[Arr.length - 1][Col];
        }
        for (int SR = Arr.length - 2; SR >= 0; SR--) {
            int[] Curr=new int[Arr[0].length];
            for (int SC = 0; SC < Arr[0].length; SC++) {
                Curr[SC] = minCostForEachCell(Arr, MoveCost, SR, SC, Curr,Helper);
            }
            Helper=Curr;
        }

        int MinPathCost = Integer.MAX_VALUE;
        for (int Elem : Helper) {
            MinPathCost = Math.min(MinPathCost, Elem);
        }
        return MinPathCost;
    }
    private static int minCostForEachCell(int[][] Arr, int[][] MoveCost, int SR, int SC, int[] Curr,int[] Helper) {
        int CellValue = Arr[SR][SC];
        int Min = Integer.MAX_VALUE;
        for (int j = 0; j < Arr[0].length; j++) {
            Min = Math.min(Min, CellValue + Helper[j] + MoveCost[CellValue][j]);
        }
        return Min;
    }

    public static void main(String[] args) {
        int[][] Arr =
                {{5,1,2},
                {4,0,3}};
        int[][] MoveCost =
                        {{12,10,15},
                        {20,23,8},
                        {21,7,1},
                        {8,1,13},
                        {9,10,25},
                        {5,3,2}};

        System.out.println(minPathCostByTabulation(Arr, MoveCost));
        System.out.println(minPathCostByMostOptimized(Arr, MoveCost));
        System.out.println(minPathCost(Arr,MoveCost));
    }
}

