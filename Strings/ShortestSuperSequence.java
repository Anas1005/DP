package DSA.DynamicProgramming.Basics.Strings;

import java.util.*;

public class ShortestSuperSequence {

    public static String shortestCommonSupersequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();

        int[][] dp = new int[m + 1][n + 1];

        // Initialize the dp table
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                dp[i][j] = -1;
            }
        }

        // Find the length of the LCS
        int lcsLength = longestCommonSubsequence(text1, text2, 0, 0, dp);

        // Construct the shortest supersequence
        StringBuilder supersequence = new StringBuilder();
        int i = 0, j = 0;

        while (i < m && j < n) {
            if (text1.charAt(i) == text2.charAt(j)) {
                // Common character, add only once to the supersequence
                supersequence.append(text1.charAt(i));
                i++;
                j++;
            } else {
                // Add characters that are not part of the LCS
                if (dp[i + 1][j] >= dp[i][j + 1]) {
                    supersequence.append(text1.charAt(i));
                    i++;
                } else {
                    supersequence.append(text2.charAt(j));
                    j++;
                }
            }
        }
        while (i < m) {
            supersequence.append(text1.charAt(i));
            i++;

        }
        while (j < n) {
            supersequence.append(text2.charAt(j));
            j++;
        }

        return supersequence.toString();
    }

    private static int longestCommonSubsequence(String str1, String str2, int i1, int i2, int[][] dp) {
        if (i1 >= str1.length() || i2 >= str2.length()) {
            return 0;
        }

        if (dp[i1][i2] != -1) {
            return dp[i1][i2];
        }

        if (str1.charAt(i1) == str2.charAt(i2)) {
            dp[i1][i2] = longestCommonSubsequence(str1, str2, i1 + 1, i2 + 1, dp) + 1;
        } else {
            dp[i1][i2] = Math.max(longestCommonSubsequence(str1, str2, i1 + 1, i2, dp),
                    longestCommonSubsequence(str1, str2, i1, i2 + 1, dp));
        }

        return dp[i1][i2];
    }

    public static void main(String[] args) {
        System.out.println(shortestCommonSupersequence("brute", "groot"));
    }
}
