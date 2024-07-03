package DSA.DynamicProgramming.Basics.Subsequences;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ExpressionLeadingTargetSum extends CountSubSetsWithTargetSum {

//    S1 - S2 = Target..
//    S1+S2 = TotalSum
//    ==> S1 =(Target+TotalSum)/2;

    // So same as Counting nUmbe rof Subsets having Target Sum = (Target+TotalSum)/2;
        public static int findTargetSumWays(int[] Arr, int Target) {
            int TotalSum = Arrays.stream(Arr).sum();
            if((Target+TotalSum)%2!=0){
                return 0;
            }
            Target = (Target+TotalSum)/2;
            Map<String,Integer> memo = new HashMap<>();

            return countSubsetsWithTargetSumMap(Arr,0,Target,0,memo);

        }

    public static void main(String[] args) {
        System.out.println(findTargetSumWays(new int[]{1,1,1,1,1},3));
    }


    }
