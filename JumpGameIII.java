package DSA.DynamicProgramming.Basics;

import java.util.Arrays;

public class JumpGameIII {
    public static boolean canReach(int[] Arr, int Start) {

        int[] Dp=new int[Arr.length];

        Arrays.fill(Dp,-1);

        return CanReach(Arr,Start,Dp,new boolean[Arr.length])==1;

    }



    public static int CanReach(int[] Arr, int SC, int[] Dp, boolean[] Visited){

        if(SC<0||SC>=Arr.length||Visited[SC]){
            return 0;
        }

        if(Arr[SC]==0){
            return Dp[SC]=1;
        }

        if(Dp[SC]!=-1){
            return Dp[SC];
        }

        Visited[SC]=true;
        int Right=CanReach(Arr,SC+Arr[SC],Dp,Visited);
        int Left=CanReach(Arr,SC-Arr[SC],Dp,Visited);
        int Res=Right|Left;
        Visited[SC]=false;
        return Dp[SC]=Res;

    }

    public static void main(String[] args) {
//        int[] Arr={4,2,3,0,3,1,2};
        int[] Arr={3,0,2,1,2};
        System.out.println(canReach(Arr,2));
    }
}
