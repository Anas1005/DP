package DSA.DynamicProgramming.Basics.Strings;

import java.util.Arrays;

public class LongestCommonSubString {
        // Function that calculates longest common substring.

    private static int[][][] memo;

    public static int longestCommonSubString(String str1, String str2) {
        memo = new int[str1.length() + 1][str2.length() + 1][Math.max(str1.length(), str2.length()) + 1];;
        for (int[][] matrix : memo) {
            for (int[] row : matrix) {
                Arrays.fill(row, -1);
            }
        }

        return lcsHelper(str1, str2, 0, 0, 0);
    }

    private static int lcsHelper(String str1, String str2, int i1, int i2, int currentLength) {
        if (i1 >= str1.length() || i2 >= str2.length()) {
            return currentLength;
        }

//        if (memo[i1][i2][currentLength] != -1) {
//            return memo[i1][i2][currentLength];
//        }

        if (str1.charAt(i1) == str2.charAt(i2)) {
            currentLength = lcsHelper(str1, str2, i1 + 1, i2 + 1, currentLength + 1);
//            memo[i1][i2][currentLength] = currentLength;

        }
            currentLength= Math.max(
                    currentLength,
                    Math.max(
                            lcsHelper(str1, str2, i1 + 1, i2, 0),
                            lcsHelper(str1, str2, i1, i2 + 1, 0)
                    )
            );

            return currentLength;

    }

    public static void main(String[] args) {
        String text1 = "abfcd";
        String text2 = "bbchd";

        System.out.println(longestCommonSubString(text1, text2));
    }
}

