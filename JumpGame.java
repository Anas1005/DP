package DSA.DynamicProgramming.Basics;

import java.util.ArrayList;
import java.util.Arrays;

public class JumpGame {
    public static boolean canJump(int[] Arr){
        int[] Dp=new int[Arr.length];
        Arrays.fill(Dp,-1);
        return CanJump(Arr,0,Dp)==1;
    }

    private static int CanJump(int[] Arr, int SC, int[] Dp) {
        if(SC>=Arr.length){
            return 0;
        }
        if (SC == Arr.length-1) {
            return Dp[SC]=1;
        }

        if(Dp[SC]!=-1){
            return Dp[SC];
        }
        int Horizontal=0;
        for (int i = 1; i <= Arr[SC]; i++) {
            Horizontal = CanJump(Arr,SC+i,Dp);
            if (Horizontal==1) return Dp[SC]=1;
        }

        return Dp[SC]=0;
    }



    static ArrayList<String> GetJumpPaths(int[] Arr,int SC){
        System.out.println("HelloJiii..........");
        if(SC>=Arr.length){
            return new ArrayList<>();
        }
        if (SC == Arr.length-1) {
            ArrayList<String> Temp = new ArrayList<>();
            Temp.add("");
            return Temp;
        }
        ArrayList<String> Result = new ArrayList<>();
        //Horizontal Moves....(H1,H2,H3......).....
        for (int i = 1; i <= Arr[SC]; i++) {
                ArrayList<String> Horizontal = GetJumpPaths(Arr,SC+i);
                for (String Elem : Horizontal) {
                    Result.add(("H" + i) + Elem);
                }
        }

        return Result;
    }

    public static void main(String[] args) {
          int[] Arr={2,3,3,2,0};
//        int[] Arr={3,2,1,1,4,2,3,4,1,2,3,4};
        System.out.println(GetJumpPaths(Arr,0));
        System.out.println(canJump(Arr));
    }
}
