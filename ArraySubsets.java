package DSA.DynamicProgramming.Basics;

import DSA.Graphs.Basics.DisjointSet;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ArraySubsets {

    static int GetArraySubsetsMaxSumByRecursion(int[] Arr, int i) {
        if (i==Arr.length) {
            return 0;
        }
        int Ch = Arr[i];
        int MaxFromPick=GetArraySubsetsMaxSumByRecursion(Arr,i+1)+Ch;
        int MaxFromNonPick=GetArraySubsetsMaxSumByRecursion(Arr,i+1);
        int MaxSum=Math.max(MaxFromPick,MaxFromNonPick);
        return MaxSum;
    }
    static int GetArraySubsetsMaxSumByMemoization(int[] Arr, int i,int[] Dp) {
        if (i>=Arr.length) {
            return 0;
        }
        if(Dp[i]!=-1){
            return Dp[i];
        }
        int Ch = Arr[i];
        int MaxFromPick=GetArraySubsetsMaxSumByMemoization(Arr,i+2,Dp)+Ch;
        int MaxFromNonPick=GetArraySubsetsMaxSumByMemoization(Arr,i+1,Dp);
        int MaxSum=Math.max(MaxFromPick,MaxFromNonPick);
        Dp[i]=MaxSum;
        return MaxSum;
    }

    static int  GetArraySubsetsMaxSumByTabulation(int[] Arr,int n){
        int[] Dp=new int[n];
        Dp[n-1]=Arr[n-1];


        int MaxFromPick=Integer.MIN_VALUE;
        int MaxFromNonPick=Integer.MIN_VALUE;

        for(int i=n-2;i>=0;i--){
            if(i<=n-3) {
                MaxFromPick = Dp[i + 2] + Arr[i];
            }
                MaxFromNonPick=Dp[i+1];
            System.out.println(MaxFromPick+":"+MaxFromNonPick);
            Dp[i]=Math.max(MaxFromPick,MaxFromNonPick);
        }
        System.out.println(Arrays.toString(Dp));
        return Dp[0];
    }

    static int  GetArraySubsetsMaxSumByMostOptimised(int[] Arr,int n){
        int Next=Arr[n-1];
        int NextNext=0;
        int Curr=0;


        int MaxFromPick=Integer.MIN_VALUE;
        int MaxFromNonPick=Integer.MIN_VALUE;

        for(int i=n-2;i>=0;i--){
            if(i<=n-3) {
                MaxFromPick = NextNext + Arr[i];
            }
            MaxFromNonPick=Next;
            Curr=Math.max(MaxFromPick,MaxFromNonPick);
            NextNext=Next;
            Next=Curr;
        }
        return Curr;
    }


    public static void main(String[] args) {

        DisjointSet set = new DisjointSet(4);
//        Scanner in = new Scanner(System.in);
//        System.out.println("Enter The Array Elements");
//        String Str = in.nextLine();
//        String[] Strr = Str.split(" ");
//        int[] Arr = new int[Strr.length];
//
//        for (int i = 0; i < Strr.length; i++) {
//            Arr[i] = Integer.parseInt(Strr[i]);
//        }
      System.out.println(GetArraySubsetsMaxSumByRecursion(new int[]{1,-4,2,5,1,2},0));
//        int[] Dp=new int[Arr.length];
//        Arrays.fill(Dp, -1);
//        System.out.println(GetArraySubsetsMaxSumByMemoization(new int[]{1,4,1,4,2},0,Dp));

//        System.out.println(Arrays.toString(Dp));
//        System.out.println(GetArraySubsetsMaxSumByTabulation(Arr,Arr.length));
//        System.out.println(GetArraySubsetsMaxSumByMostOptimised(Arr,Arr.length));
    }
}
