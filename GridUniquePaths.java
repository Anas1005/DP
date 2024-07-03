package DSA.DynamicProgramming.Basics;

import java.util.Arrays;

public class GridUniquePaths {

    static int CountUniquePaths(int[][] Arr){
        int[][] Dp=new int[Arr.length][Arr[0].length];
        for(int[] Elem:Dp) {
            Arrays.fill(Elem, -1);
        }
//        return CountUniquePaths(Arr,0,0);
//        int Res= CountUniquePathsByMemoization(Arr,0,0,Dp);
//        for(int[] Elem:Dp){
//            System.out.println(Arrays.toString(Elem));
//        }
//        return Res;
        return CountUniquePathsByTabulation(Arr);
    }

    static int CountUniquePaths(int[][] Arr,int SR,int SC){
        if(SR<0||SC<0||SR>=Arr.length||SC>=Arr[0].length){
            return 0;
        }

        if(SR== Arr.length-1&&SC==Arr[0].length-1){
            return 1;
        }

        int Right=CountUniquePaths(Arr,SR,SC+1);
        int Down=CountUniquePaths(Arr,SR+1,SC);
        int Res=Right+Down;
        return Res;
    }
    static int CountUniquePathsByMemoization(int[][] Arr,int SR,int SC,int[][] Dp){
        if(SR<0||SC<0||SR>=Arr.length||SC>=Arr[0].length){
            return 0;
        }
        if(SR== Arr.length-1&&SC==Arr[0].length-1){
            return Dp[SR][SC]=1;
        }
        if(Dp[SR][SC]!=-1){
            return Dp[SR][SC];
        }
        int Right=CountUniquePathsByMemoization(Arr,SR,SC+1,Dp);
        int Down=CountUniquePathsByMemoization(Arr,SR+1,SC,Dp);
        int Res=Right+Down;
        return Dp[SR][SC]=Res;
    }
    static int CountUniquePathsByTabulation(int[][] Arr) {
        int[][] Dp = new int[Arr.length][Arr[0].length];
        for (int[] Elem : Dp) {
            Arrays.fill(Elem, -1);
        }
        Arrays.fill(Dp[Dp.length - 1], 1);
        for (int i = 0; i < Dp.length; i++) {
            Dp[i][Dp[0].length - 1] = 1;
        }

        for(int SR=Arr.length-2;SR>=0;SR--){
            for(int SC=Arr[0].length-2;SC>=0;SC--){
                int RightWays=Dp[SR][SC+1];
                int DownWays=Dp[SR+1][SC];
                Dp[SR][SC]=RightWays+DownWays;
            }
        }
        for(int[] Elem:Dp){
            System.out.println(Arrays.toString(Elem));
        }
        return Dp[0][0];
    }
    static int CountUniquePathsByMostOptimized(int[][] Arr) {
        int[] HelperRight = new int[Arr.length];
        int[] HelperDown= new int[Arr[0].length];
        Arrays.fill(HelperDown, 1);
        Arrays.fill(HelperRight,1);

        for(int SR=Arr.length-2;SR>=0;SR--){
            int[] Curr=new int[Arr[0].length];
            for(int SC=Arr[0].length-2;SC>=0;SC--){
                int RightWays=HelperRight[SR];
                int DownWays=HelperDown[SC];
                Curr[SC]=RightWays+DownWays;
                HelperRight[SR]=Curr[SC];
            }
            HelperDown=Curr;
        }
            System.out.println(Arrays.toString(HelperDown));

        return HelperDown[0];
    }


    public static void main(String[] args) {
        int[][] Arr ={{0,0,0,0},
                {1,0,0,1},
                {0,1,0,0},
                {1,1,0,0}};
//        boolean[][] Visited =new boolean[4][4];
        System.out.println(CountUniquePathsByTabulation(Arr));
        System.out.println(CountUniquePathsByMostOptimized(Arr));
    }
}
