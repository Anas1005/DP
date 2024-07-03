package DSA.DynamicProgramming.Basics.Strings;

import java.util.HashMap;
import java.util.Map;

public class  DistinctSubsequences {
    public static int numDistinct(String Str1, String Str2) {
        Map<String, Integer> memo = new HashMap<>();
        return numDistinct(Str1, Str2, 0, 0, memo);
    }

    public static int numDistinct(String Str1, String Str2, int i1, int i2, Map<String, Integer> memo) {
        if (i2 >= Str2.length()) {
            return 1;
        }

        if (i1 >= Str1.length()) {
            return 0;
        }

        String memoKey = i1 + "," + i2;
        if (memo.containsKey(memoKey)) {
            return memo.get(memoKey);
        }

        int result;
        if (Str1.charAt(i1) == Str2.charAt(i2)) {
            result = numDistinct(Str1, Str2, i1 + 1, i2 + 1, memo) + numDistinct(Str1, Str2, i1 + 1, i2, memo);
        } else {
            result = numDistinct(Str1, Str2, i1 + 1, i2, memo);
        }

        memo.put(memoKey, result);
        return result;
    }

    public static void main(String[] args) {
        System.out.println(numDistinct("bagbag","bag"));
    }

}
