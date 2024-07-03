package DSA.DynamicProgramming.Basics.Strings;

import java.util.Arrays;

public class LongestCommonSubSequence {

    public static int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();

        int[][] dp = new int[m + 1][n + 1];

        // Initialize the dp table
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                dp[i][j] = -1;
            }
        }

        String[][] dpp = new String[m + 1][n + 1];

        // Initialize the dp table
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                dpp[i][j] = "";
            }
        }

        int ResLength = longestCommonSubsequence(text1, text2, 0, 0, dp);
        for(int[] arr : dp)
        System.out.println(Arrays.toString(arr));


//        Finding LCS from Directly from DP Table only.........

        StringBuilder LCS = new StringBuilder();
        int i = 0, j = 0;

        while (i < m && j < n) {
            if (text1.charAt(i) == text2.charAt(j)) {
                // Common character, add only once to the LCS
                LCS.append(text1.charAt(i));
                i++;
                j++;
            } else {
                // Add characters that are not part of the LCS
                if (dp[i + 1][j] >= dp[i][j + 1]) {
                    i++;
                } else {
                    j++;
                }
            }
        }

        System.out.println(LCS);

        String ResString = longestCommonSubsequence(text1, text2, 0, 0,dpp);
        System.out.println(ResString);

        return -1;
    }

    private static int longestCommonSubsequence(String str1, String str2, int i1, int i2, int[][] dp) {
        if (i1 >= str1.length() || i2 >= str2.length()) {
            return 0;
        }

        if (dp[i1][i2] != -1) {
            return dp[i1][i2];
        }

        if (str1.charAt(i1) == str2.charAt(i2)) {
           return dp[i1][i2] = 1 + longestCommonSubsequence(str1, str2, i1 + 1, i2 + 1, dp);
        } else {
           return dp[i1][i2] = Math.max(longestCommonSubsequence(str1, str2, i1 + 1, i2, dp),
                    longestCommonSubsequence(str1, str2, i1, i2 + 1, dp));
        }

    }
      private static String longestCommonSubsequence(String str1, String str2, int i1, int i2, String[][] dp) {
        if (i1 >= str1.length() || i2 >= str2.length()) {
            return "";
        }

        if (!dp[i1][i2].equals("")) {
            return dp[i1][i2];
        }

        if (str1.charAt(i1) == str2.charAt(i2)) {
           return dp[i1][i2] = str1.charAt(i1) + longestCommonSubsequence(str1, str2, i1 + 1, i2 + 1, dp);

        } else {
            String pick1NotPick2 = longestCommonSubsequence(str1, str2, i1 + 1, i2, dp);
            String pick2NotPick1 = longestCommonSubsequence(str1, str2, i1, i2 + 1, dp);

           return dp[i1][i2] = pick1NotPick2.length() > pick2NotPick1.length() ? pick1NotPick2 : pick2NotPick1;
        }

    }




    public static void main(String[] args) {
        String text1 = "abcdfg";
        String text2 = "zbkfl";

        int result = longestCommonSubsequence(text1, text2);

    }
}
