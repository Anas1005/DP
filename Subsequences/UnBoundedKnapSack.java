package DSA.DynamicProgramming.Basics.Subsequences;

import java.util.Arrays;

public class UnBoundedKnapSack {

    //      Infinite Choices : ==> Pick 0 or Pick 1 or Pick 2 Or.........till...........Pick (maxWeight/weight[SC])

    static final int MINUS_INFINITY = -1000000000;
    static int RecCount=0;
    static int DpCount=0;
    static int unBoundedKnapsack(int[] weight, int[] value,int maxWeight) {
        int[][] memo = new int[weight.length][maxWeight + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }

       int ResRec = maxValueStolenUKS(weight,value,0,maxWeight);
       System.out.println("ResRec: "+ResRec+":"+RecCount);
        int ResDP = maxValueStolenUKS(weight,value,0,maxWeight,memo);
        System.out.println("ResDP: "+ResDP+":"+DpCount);

        return -1;

    }

    private static int maxValueStolenUKS(int[] weight, int[] value, int SC, int maxWeight) {

        if (SC == weight.length-1) {
            return weight[SC] <= maxWeight ? (maxWeight/weight[SC])*value[SC]:0;
        }

        RecCount++;

        int MaxValueStolen = MINUS_INFINITY;
        for (int i = 0; i <= maxWeight / weight[SC]; i++) {
            if (weight[SC] * i <= maxWeight) {
                MaxValueStolen = Math.max(MaxValueStolen, maxValueStolenUKS(weight,value, SC + 1, maxWeight - weight[SC] * i) + value[SC]*i);
            }
        }

        return MaxValueStolen;

    }

    private static int maxValueStolenUKS(int[] weight, int[] value, int SC, int maxWeight, int[][] memo) {
        if (SC == weight.length - 1) {
            return weight[SC] <= maxWeight ? (maxWeight / weight[SC]) * value[SC] : 0;
        }



        if (memo[SC][maxWeight] != -1) {
            return memo[SC][maxWeight];
        }
        DpCount++;

        int maxValueStolen = MINUS_INFINITY;
        for (int i = 0; i <= maxWeight / weight[SC]; i++) {
            if (weight[SC] * i <= maxWeight) {
                maxValueStolen = Math.max(maxValueStolen, maxValueStolenUKS(weight, value, SC + 1, maxWeight - weight[SC] * i, memo) + value[SC] * i);
            }
        }

        memo[SC][maxWeight] = maxValueStolen;
        return maxValueStolen;
    }


    public static void main(String[] args) {
        System.out.println(unBoundedKnapsack(new int[]{1,2,3},new int[]{2,5,7},3));
    }
}
