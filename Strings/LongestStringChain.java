package DSA.DynamicProgramming.Basics.Strings;

import java.util.Arrays;
import java.util.Comparator;

public class LongestStringChain {

    public static int longestStrChain(String[] Arr) {
        Arrays.sort(Arr, Comparator.comparingInt(String::length));
        int[] Dp = new int[Arr.length];
        Arrays.fill(Dp, 1);

        int MaxLSC = 1;
        for (int current = 0; current <= Arr.length - 1; current++) {
            for (int prev = 0; prev <= current - 1; prev++) {
                if (isComparable(Arr[current], Arr[prev])) {
                    Dp[current] = Math.max(Dp[current], 1 + Dp[prev]);
                }
            }
            MaxLSC = Math.max(MaxLSC, Dp[current]);
        }

        return MaxLSC;
    }

    public static boolean isComparable(String Str1, String Str2) {
        if (Str1.length() != Str2.length() + 1) {
            return false;
        }
        int First = 0;
        int Second = 0;
        while (First < Str1.length()) {
            if (Second < Str2.length() && Str1.charAt(First) == Str2.charAt(Second)) {
                First++;
                Second++;
            } else {
                First++;
            }
        }

        return First == Str1.length() && Second == Str2.length();
    }
}