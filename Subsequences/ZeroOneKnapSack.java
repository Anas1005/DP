package DSA.DynamicProgramming.Basics.Subsequences;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ZeroOneKnapSack {

//    Only 2 Choices : Pick or Not Pick
//                ==> Pick 1 or Pick 0

    static final int MINUS_INFINITY = -1000000000;

    static int RecCount=0;
    static int MapCount=0;
    static int TwoDPCount=0;

    static int knapsack(int[] weight, int[] value,int maxWeight) {

        Map<String, Integer> memoMap = new HashMap<>();
        int[][] memo2D = new int[weight.length + 1][maxWeight + 1];
        for (int[] row : memo2D) {
            Arrays.fill(row, -1);
        }

        int resultRec = maxValueStolen(weight, value, 0, 0, maxWeight);
        System.out.println("Rec: " + resultRec+":"+RecCount);
        int resultMap = maxValueStolenMap(weight, value, 0, 0, maxWeight, memoMap);
        System.out.println("Map: " + resultMap+":"+MapCount);
        int result2D = maxValueStolen2D(weight, value, 0, 0, 0, maxWeight, memo2D);
        System.out.println("2D: " + result2D+":"+TwoDPCount);
        return -1;


    }

    static int maxValueStolen(int[] weight, int[] value, int SC,int weightSum,int maxWeight){
        if(weightSum>maxWeight){
            return MINUS_INFINITY;
        }

        RecCount++;
        if (SC == weight.length-1) {
            weightSum+=weight[SC];
            return weightSum <= maxWeight ? value[SC]:0;
        }

        int maxStolenPick = maxValueStolen(weight,value,SC+1,weightSum+weight[SC],maxWeight)+value[SC];
        int maxStolenNotPick = maxValueStolen(weight,value,SC+1,weightSum,maxWeight);

        return Math.max(maxStolenPick,maxStolenNotPick);

    }

    static int maxValueStolenMap(int[] weight, int[] value, int SC, int weightSum,int maxWeight, Map<String, Integer> memo) {
        MapCount++;

        if (SC == weight.length-1) {
            weightSum+=weight[SC];
            return weightSum <= maxWeight ? value[SC]:0;
        }


        String key = SC + "|" + weightSum;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        int maxStolenPick = MINUS_INFINITY;
        if(weightSum+weight[SC]<=maxWeight) {
            maxStolenPick = maxValueStolenMap(weight, value, SC + 1, weightSum + weight[SC], maxWeight, memo) + value[SC];
        }
        int maxStolenNotPick = maxValueStolenMap(weight, value, SC + 1, weightSum,  maxWeight, memo);

        int result = Math.max(maxStolenPick, maxStolenNotPick);
        memo.put(key, result);

        return result;
    }



    static int maxValueStolen2D(int[] weight, int[] value, int SC, int weightSum, int valueSum, int maxWeight, int[][] memo) {

        TwoDPCount++;

        if (SC == weight.length) {
            return valueSum;
        }

        if (memo[SC][weightSum] != -1) {
            return memo[SC][weightSum];
        }

        int maxStolenPick=MINUS_INFINITY;
        if(weightSum+weight[SC]<=maxWeight){
            maxStolenPick = maxValueStolen2D(weight, value, SC + 1, weightSum + weight[SC], valueSum + value[SC], maxWeight, memo);
        }
        int maxStolenNotPick = maxValueStolen2D(weight, value, SC + 1, weightSum, valueSum, maxWeight, memo);

        int result = Math.max(maxStolenPick, maxStolenNotPick);
        memo[SC][weightSum] = result;

        return result;
    }


    public static void main(String[] args) {
        System.out.println(knapsack(new int[]{1,2,4,5,6,1,5,2,3,1,4,1,4,14,14},new int[]{5,4,8,6,14,1,4,4,2,4,11,13,12,12,11},17));
    }
}
