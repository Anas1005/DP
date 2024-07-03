package DSA.DynamicProgramming.Basics.Strings;

public class LongestPalindromicSubstring {

        public String longestPalindrome(String s) {
            int n = s.length();
            boolean[][] dp = new boolean[n][n];
            String Res = "";
            // Initialize base cases for substrings of length 1
            for (int i = 0; i < n; i++) {
                dp[i][i] = true;
                Res = s.substring(i,i+1);
            }

            // Initialize base cases for substrings of length 2
            for (int i = 0; i < n - 1; i++) {
                if (s.charAt(i) == s.charAt(i + 1)) {
                    dp[i][i + 1] = true;
                    Res = s.substring(i,i+2);
                }
            }

            // Build up dp table for substrings of length 3 or more
            for (int len = 3; len <= n; len++) {
                for (int start = 0; start <= n - len; start++) {
                    int end = start + len - 1;
                    if (s.charAt(start) == s.charAt(end) && dp[start+ 1][end - 1]) {
                        dp[start][end] = true;
                        Res = s.substring(start,end+1);
                    }
                }
            }

            return Res;
        }

}
