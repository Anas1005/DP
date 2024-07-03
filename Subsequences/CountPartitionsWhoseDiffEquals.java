package DSA.DynamicProgramming.Basics.Subsequences;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CountPartitionsWhoseDiffEquals extends CountSubSetsWithTargetSum {

    static int Lengthy=0;

    public static int countPartitionsWhoseDiffEqualsTarget(int[] Arr, int Target) {
        int TotalSum = Arrays.stream(Arr).sum();
        if((Target+TotalSum)%2!=0){
            return 0;
        }
        int NewTarget = (Target+TotalSum)/2;
        Map<String,Integer> memo = new HashMap<>();

        int ResLengthy = countPartitionsWhoseDiffEqualsTarget(Arr,0,0,0,Target);
        System.out.println("ResLengthy: "+ResLengthy +":"+Lengthy);
        int ResBoiled = countSubsetsWithTargetSumMap(Arr,0,NewTarget,0,memo);
        System.out.println("ResBoiled: "+ResBoiled +":"+ Calls);

        return -1;

    }

    static int countPartitionsWhoseDiffEqualsTarget(int[] Arr,int SC,int subSum1,int subSum2,int Target){
//        if (SC == Arr.length) {
//            return subSum1-subSum2 == Target ? 1:0;
//


        if(SC==Arr.length-1){

            return (subSum1+Arr[SC])-subSum2 == Target ||  subSum1-(subSum2+Arr[SC]) == Target ? 1:0;
        }

         Lengthy++;

        // Include the current element in the first subset
        int sub1Counts = countPartitionsWhoseDiffEqualsTarget(Arr, SC + 1, subSum1 + Arr[SC], subSum2,Target);

        // Include the current element in the second subset
        int sub2Counts = countPartitionsWhoseDiffEqualsTarget(Arr, SC + 1, subSum1, subSum2 + Arr[SC],Target);

        // Return the minimal difference only when the current element is included in one subset
        return sub1Counts + sub2Counts;


    }

    public static void main(String[] args) {
        System.out.println(countPartitionsWhoseDiffEqualsTarget(new int[]{5,2,5,1,1,3,1,3,1,2,2,3,1,3,1,3,1,3,12},3));
    }




}
