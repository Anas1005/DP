package DSA.DynamicProgramming.Basics;

import java.util.Arrays;

public class MaxJumpsToReachLastIndex {
        public static int maximumJumps(int[] Arr,int K)

        {

            int[] Dp=new int[Arr.length];

            Arrays.fill(Dp,-1);

            int Res=MaximumJumps(Arr,0,K,Dp);

            return Res<0?-1:Res;

        }



        public static int MaximumJumps(int[] Arr,int SC, int K,int[] Dp){

            if(SC==Arr.length-1){

                return 0;

            }

            if(Dp[SC]!=-1){

                return Dp[SC];

            }

            int MaxJumps=Integer.MIN_VALUE;

            for(int i=1;i<=Arr.length-1-SC;i++){

                if(Math.abs(Arr[SC+i]-Arr[SC])<=K){

                    MaxJumps=Math.max(MaxJumps, MaximumJumps(Arr,SC+i,K,Dp));

                }

            }

            return Dp[SC]=MaxJumps+1;

        }
    }

