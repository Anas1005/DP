package DSA.DynamicProgramming.Basics;

import java.util.Arrays;

public class DungeonGame {
    public static int calculateMinimumHP(int[][] Arr) {
        int[][] Dp=new int[Arr.length][Arr[0].length];
        for(int[] Elem:Dp) {
            Arrays.fill(Elem, -1);
        }
        int Res=CalMinHealthByMemoization(Arr,0,0,Dp,"");
        for(int[] Elem:Dp){
            System.out.println(Arrays.toString(Elem));
        }
        return Res;
    }

    public static int CalMinHealthByMemoization(int[][] Arr,int SR,int SC,int[][] Dp,String Ans){
        if (SR >= Arr.length || SC >= Arr[0].length) {
            return Integer.MAX_VALUE;
        }

        if(SR==Arr.length-1&&SC==Arr[0].length-1){
            if(Arr[SR][SC]<0){
                return Dp[SR][SC]=Math.abs(Arr[SR][SC])+1;
            }
            else {
                return Dp[SR][SC]=1;
            }
        }

        if(Dp[SR][SC]!=-1){
            return Dp[SR][SC];
        }

        int Right=CalMinHealthByMemoization(Arr,SR,SC+1,Dp,Ans+'R');
        int RightMin=(Right==Integer.MAX_VALUE)?Integer.MAX_VALUE:(Right-Arr[SR][SC]>0)?Right-Arr[SR][SC]:1;

        int Down=CalMinHealthByMemoization(Arr,SR+1,SC,Dp,Ans+'D');
        int DownMin=(Down==Integer.MAX_VALUE)?Integer.MAX_VALUE:(Down-Arr[SR][SC]>0)?Down-Arr[SR][SC]:1;

        int Res=Math.min(RightMin,DownMin);
        System.out.println(RightMin+":"+DownMin);


        return Dp[SR][SC]=Res;

    }

    public static int calculateMinimumHealthByTabulation(int[][] Arr) {
        int[][] Dp=new int[Arr.length][Arr[0].length];
        for(int[] Elem:Dp) {
            Arrays.fill(Elem, -1);
        }
        int LastElement=Arr[Arr.length-1][Arr[0].length-1];
        Dp[Arr.length-1][Arr[0].length-1]=(LastElement<0)?Math.abs(LastElement)+1:1;

        for(int SC=Arr[0].length-2;SC>=0;SC--){
            int RightDiff=Dp[Arr.length-1][SC+1]-Arr[Arr.length-1][SC];
            Dp[Arr.length-1][SC]=RightDiff>0?RightDiff:1;
        }
        for(int SR=Arr.length-2;SR>=0;SR--){
            int DownDiff=Dp[SR+1][Arr[0].length-1]-Arr[SR][Arr[0].length-1];
            Dp[SR][Arr[0].length-1]=DownDiff>0?DownDiff:1;
        }


        for(int SR=Arr.length-2;SR>=0;SR--){
            for(int SC=Arr[0].length-2;SC>=0;SC--){
                int RightDiff=Dp[SR][SC+1]-Arr[SR][SC];
                int RightMin=RightDiff>0?RightDiff:1;
                int DownDiff=Dp[SR+1][SC]-Arr[SR][SC];
                int DownMin=DownDiff>0?DownDiff:1;
                Dp[SR][SC]=Math.min(RightMin,DownMin);
            }
        }
        return Dp[0][0];
    }

    public static int calculateMinimumHealthByMostOptimised(int[][] Arr) {
        int[] HelperRight = new int[Arr.length];
        int[] HelperDown= new int[Arr[0].length];

        int LastElement=Arr[Arr.length-1][Arr[0].length-1];
        HelperDown[HelperDown.length-1]=(LastElement<0)?Math.abs(LastElement)+1:1;
        HelperRight[HelperRight.length-1]=(LastElement<0)?Math.abs(LastElement)+1:1;

        for(int SC=Arr[0].length-2;SC>=0;SC--){
            int RightDiff=HelperDown[SC+1]-Arr[Arr.length-1][SC];
            HelperDown[SC]=RightDiff>0?RightDiff:1;
        }
        for(int SR=Arr.length-2;SR>=0;SR--){
            int DownDiff=HelperRight[SR+1]-Arr[SR][Arr[0].length-1];
            HelperRight[SR]=DownDiff>0?DownDiff:1;
        }

        for(int SR=Arr.length-2;SR>=0;SR--){
            int[] Curr=new int[Arr.length];
            for(int SC=Arr[0].length-2;SC>=0;SC--){
                int RightDiff=HelperRight[SR]-Arr[SR][SC];
                int RightMin=RightDiff>0?RightDiff:1;
                int DownDiff=HelperDown[SC]-Arr[SR][SC];
                int DownMin=DownDiff>0?DownDiff:1;
                Curr[SC]=Math.min(RightMin,DownMin);
                HelperRight[SR]=Curr[SC];
            }
            HelperDown=Curr;
        }
        return HelperDown[0];
    }






    public static void main(String[] args) {
        int[][] Arr =

                        {{-2, -3, 3},
                        {-5, -10, 1},
                        {10, 30, -5}};

        System.out.println(calculateMinimumHP(Arr));
        System.out.println(calculateMinimumHealthByTabulation(Arr));
        System.out.println(calculateMinimumHealthByMostOptimised(Arr));
    }
}
