package DSA.DynamicProgramming.Basics;

import java.util.Arrays;

public class JumpGameII {
    final static int INFINITY = 1000000000;

    public static int jump(int[] Arr) {
        int[] Dp=new int[Arr.length];
        Arrays.fill(Dp,-1);
        int Res= MinimumJumpRequired(Arr,0,Dp);
        System.out.println(Arrays.toString(Dp));
        return Res;
    }

    private static int MinimumJumpRequired(int[] Arr, int SC, int[] Dp) {
        if(SC>=Arr.length){
            return Integer.MAX_VALUE;
        }

        if (SC == Arr.length-1) {
            return Dp[SC]=0;
        }

        if(Dp[SC]!=-1){
            return Dp[SC];
        }
        int MinJump=INFINITY;
        for (int i = 1; i <= Arr[SC]; i++) {
            MinJump=Math.min(MinJump,MinimumJumpRequired(Arr,SC+i,Dp));
        }

        int Res =MinJump+1;

        return Dp[SC]=Res;
    }
    public static void main(String[] args) {
        int[] Arr = {2,2,0,1,4};
        System.out.println(jump(Arr));
    }
}

