package DSA.DynamicProgramming.Basics.Strings;

public class EditDistance {

    // Memoization
    public static int minDistanceMemo(String Str1, String Str2) {
        int m = Str1.length();
        int n = Str2.length();

        Integer[][] memo = new Integer[m + 1][n + 1];
        return minOperationsMemo(Str1, Str2, 0, 0, memo);
    }

    private static int minOperationsMemo(String Str1, String Str2, int i1, int i2, Integer[][] memo) {
        if (i2 >= Str2.length()) {
            return Str1.length() - i1;
        }

        if (i1 >= Str1.length()) {
            return Str2.length() - i2;
        }

        if (memo[i1][i2] != null) {
            return memo[i1][i2];
        }

        if (Str1.charAt(i1) == Str2.charAt(i2)) {
            memo[i1][i2] = minOperationsMemo(Str1, Str2, i1 + 1, i2 + 1, memo);
        } else {
            int replaceCurrentFromStr1 = 1 + minOperationsMemo(Str1, Str2, i1 + 1, i2 + 1, memo);
            int insertJustBeforeStr1 = 1 + minOperationsMemo(Str1, Str2, i1, i2 + 1, memo);
            int deleteCurrentFromStr1 = 1 + minOperationsMemo(Str1, Str2, i1 + 1, i2, memo);

            memo[i1][i2] = Math.min(replaceCurrentFromStr1, Math.min(insertJustBeforeStr1, deleteCurrentFromStr1));
        }

        return memo[i1][i2];
    }

    // Tabulation
    public static int minDistanceTab(String Str1, String Str2) {
        int m = Str1.length();
        int n = Str2.length();

        int[][] dp = new int[m + 1][n + 1];

        // Initialize the last row
        for (int j = 0; j <= n; j++) {
            dp[Str1.length()][j] = Str2.length() - j;
        }

        // Initialize the last column
        for (int i = 0; i <= m; i++) {
            dp[i][Str2.length()] = Str1.length() - i;
        }

        // Calculate in backward direction
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (Str1.charAt(i) == Str2.charAt(j)) {
                    dp[i][j] = dp[i + 1][j + 1];
                } else {
                    dp[i][j] = Math.min(1+dp[i + 1][j + 1], Math.min(1+dp[i][j + 1],1+ dp[i + 1][j]));
                }
            }
        }

        return dp[0][0];
    }

    // Combined Function
    public static void main(String[] args) {
        String Str1 = "horse";
        String Str2 = "ros";

        // Memoization
        int resultMemo = minDistanceMemo(Str1, Str2);
        System.out.println("Memoization Result: " + resultMemo);

        // Tabulation
        int resultTab = minDistanceTab(Str1, Str2);
        System.out.println("Tabulation Result: " + resultTab);
    }
}
