package DSA.DynamicProgramming.Basics.Subsequences;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MinMaxDistanceInSubSeqOfArray {

    static final int MINUS_INFINITY = -1000000000;


    public static int constrainedSubsetSum(int[] Arr, int maxDistance) {

        List<List<Integer>> subsequences = new ArrayList<>();
        getArraySubsets(subsequences, Arr, 0, -1, maxDistance, new ArrayList<>());
        System.out.println(subsequences);
        int Res = constrainedSubsetSum(Arr,0,-1,maxDistance);
        return Res==0 ? Arrays.stream(Arr).max().orElseThrow() : Res;

    }

    static int constrainedSubsetSum(int[] Arr, int SC, int lastAddedIndex, int maxDistance) {

        if(SC>=Arr.length){
            return 0;
        }

//        if (SC == Arr.length - 1) {
//            return isValid(SC, lastAddedIndex, maxDistance) ? Arr[SC] : 0;
//        }


        // Check if adding current element maintains the maximum distance condition
        int PickMaxSum = MINUS_INFINITY;
        if (isValid(SC, lastAddedIndex, maxDistance)) {
            PickMaxSum = constrainedSubsetSum(Arr,SC+1,SC,maxDistance) + Arr[SC];
        }

        int NotPickSum = constrainedSubsetSum(Arr,SC+1,lastAddedIndex,maxDistance);

        System.out.println("Pick:"+PickMaxSum+":"+"NotPick"+":"+NotPickSum);

        return Math.max(PickMaxSum,NotPickSum);


    }

    static void getArraySubsets(List<List<Integer>> result, int[] arr, int i, int lastAddedIndex, int maxDistance, List<Integer> ans) {
        if (i >= arr.length) {
            result.add(new ArrayList<>(ans));
            return;
        }

        // Check if adding current element maintains the maximum distance condition
        if (isValid(i, lastAddedIndex, maxDistance)) {
            ans.add(arr[i]);
            getArraySubsets(result, arr, i + 1, i, maxDistance, ans);
            ans.remove(ans.size() - 1);
        }

        // Skip current element
        getArraySubsets(result, arr, i + 1, lastAddedIndex, maxDistance, ans);
    }

    static boolean isValid(int currentIndex, int lastAddedIndex, int maxDistance) {
        if (lastAddedIndex == -1) {
            return true;  // No elements added yet, so valid
        }

//        System.out.println(currentIndex+":"+lastAddedIndex);
        return Math.abs(currentIndex - lastAddedIndex) <= maxDistance;
    }

    public static void main(String[] args) {


        System.out.println(constrainedSubsetSum(new int[]{-1,-2,-3},2));
        // Print the generated subsequences
//        System.out.println(subsequences);
    }
}

