package DSA.DynamicProgramming.Basics.Strings;

import java.util.Arrays;

public class LongestPalindromicSubsequence {

    private static int[][] memo;
    public static int longestPalindromeSubseq(String Str) {
        memo = new int[Str.length()][Str.length()];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }

        return longestPalindromicSubSeq(Str, 0, Str.length() - 1);
    }

    private static int longestPalindromicSubSeq(String Str, int i1, int i2) {
        if (i1 > i2) {
            return 0;
        }

        if (i1 == i2) {
            return 1;
        }

        if (memo[i1][i2] != -1) {
            return memo[i1][i2];
        }

        if (Str.charAt(i1) == Str.charAt(i2)) {
            return memo[i1][i2] = 2 + longestPalindromicSubSeq(Str, i1 + 1, i2 - 1) ;
        } else {
            return memo[i1][i2] = Math.max(longestPalindromicSubSeq(Str, i1 + 1, i2), longestPalindromicSubSeq(Str, i1, i2 - 1));
        }


    }


    public static void main(String[] args) {
        System.out.println(longestPalindromeSubseq("jxvcfeohzyklwtbacnljbopuhlcvawtm"));
    }
    }
