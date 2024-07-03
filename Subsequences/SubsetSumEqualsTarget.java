package DSA.DynamicProgramming.Basics.Subsequences;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SubsetSumEqualsTarget {
    static ArrayList<Integer> getArraySubsetSum(int[] Arr, int i) {
        if (i == Arr.length) {
            return new ArrayList<>();
        }
        int Curr = Arr[i];

        ArrayList<Integer> Res = new ArrayList<>();
         Res.add(Curr);
        ArrayList<Integer> Sums = getArraySubsetSum(Arr, i + 1);
        for (int Elem : Sums) {
            Res.add(Curr + Elem);
        }
        Res.addAll(Sums);

        System.out.println(Res);

        return Res;
    }

    public static boolean isSubSumEqualTarget(int[] Arr,int Target){

        int TotalSum = 0;
        for(int Elem:Arr){
            TotalSum+=Elem;
        }
        if(TotalSum%2==1){
            return false;
        }
        int[][] Dp=new int[Arr.length][TotalSum/2+1];
        for(int[] Elem:Dp) {
            Arrays.fill(Elem, -1);
        }
//        System.out.println("vdsv"+TotalSum/2);
//        System.out.println();

        int Res= isSubSumEqualTarget(Arr,0,TotalSum/2,Dp);

//        System.out.println(Arr.length*Target);
////        System.out.println(Arr.length*TotalSum/2);

        return Res==1;

    }

    public static int isSubSumEqualTarget(int[] Arr,int SC,int Target,int[][] Dp) {
        if (SC== Arr.length) {
            return 0;
        }

        if(SC==Arr.length-1){
            return Dp[SC][Target] = Arr[SC]==Target ? 1:0;
        }

        if(Target==0){
            return Dp[SC][Target] = 1;

        }

        if(Dp[SC][Target]!=-1){
            return Dp[SC][Target];
        }

        int NotPickCurrent = isSubSumEqualTarget(Arr,SC+1,Target,Dp);
        int PickCurrent = 0;
                if(Target>=Arr[SC]) {
                    PickCurrent = isSubSumEqualTarget(Arr, SC + 1, Target - Arr[SC], Dp);
                }


      return Dp[SC][Target] = PickCurrent | NotPickCurrent;

    }



    public static void main(String[] args) {
//        System.out.println(isSubSumEqualTarget(new int[]{38,7,20,83,13,44,87,70,45,54,23,72,81,62,33,55,16,96,9,64,15,88,45,97,43,55,56,43,13,29,79,27,26,50,25,5,24,61,48,32,52,62,25,77,18,4,59,73,70,92,2,36,94,4,24,71,42,11,41,94,20,82,14,71,45,80,35,61,31,61,46,47,40,80,52,90,52,6,75,28,67,68,8,77,19,2,85,69,35,14,58,67,45,66,87,6,24,88,11,24},7));
//        System.out.println(isSubSumEqualTarget(new int[]{5,79,2,4,8,16,32,64,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100},1));
        System.out.println(isSubSumEqualTarget(new int[]{1,2,3,4},21));
    }

}
