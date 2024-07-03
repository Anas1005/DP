package DSA.DynamicProgramming.Basics;

import javax.print.DocFlavor;
import java.util.Arrays;
import java.util.HashMap;

public class NinjasTraining {
    public static int ninjaTraining(int n, int[][] Points) {

        int[][] Dp=new int[n][4];
        for(int[] Elem:Dp) {
            Arrays.fill(Elem, -1);
        }


        int Ans= MaxNoAdjPoints(Points,0,3,Dp);
        for(int[] Elem:Dp){
            System.out.println(Arrays.toString(Elem));
        }
        return Ans;

    }

    private static int MaxNoAdjPoints(int[][] Points, int i, int LastActivity,int[][] Dp) {
        if (i >= Points.length) {
            return 0;
        }

        if (Dp[i][LastActivity] != -1) {
            return Dp[i][LastActivity];
        }

        int MaxPoints = Integer.MIN_VALUE;

        for (int j = 0; j <= 2; j++) {
            if (j!=LastActivity) {
                MaxPoints = Math.max(MaxPoints, MaxNoAdjPoints(Points, i + 1, j, Dp) + Points[i][j]);
            }
        }
        Dp[i][LastActivity]=MaxPoints ;
        return MaxPoints;
    }

    private static int MaxNoAdjPoints(int[][] Arr) {

        int MaxPoints = Integer.MIN_VALUE;
        for (int Activity = 0; Activity <= 2; Activity++) {
            int Points = Arr[0][Activity];
            int PrevCol = Activity;
            for (int i = 1; i < Arr.length; i++) {
                int Max = Integer.MIN_VALUE;
                int Prev = PrevCol;
                for (int j = 0; j < Arr[0].length; j++) {
                    if (Arr[i][j] > Max && j != Prev) {
                        Max = Arr[i][j];
                        PrevCol = j;
                    }
                }
                Points += Max;
            }
//            System.out.println(Points);
            MaxPoints = Math.max(MaxPoints, Points);
        }
        return MaxPoints;
    }


    public static void main(String[] args) {
//        int[][] Arr={{10,40,70},
//                     {20,50,80},
//                     {30,60,90}};
        int[][] Arr = {
                {73, 57, 31},
                {61, 30, 34},
                {87, 64, 41},
                {9, 69, 12},
                {33, 22, 62},
                {8, 15, 59},
                {74, 74, 51},
                {72, 5, 62},
                {23, 72, 93}
        };

        System.out.println(ninjaTraining(Arr.length,Arr));
        System.out.println(MaxNoAdjPoints(Arr));
    }


}


