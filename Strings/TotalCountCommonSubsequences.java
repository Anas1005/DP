package DSA.DynamicProgramming.Basics.Strings;

public class TotalCountCommonSubsequences {

    public static int countCommonSubsequences(String str1, String str2, int m, int n) {
        // Base cases
        if (m == str1.length() || n == str2.length()) {
            return 0;
        }

        // If the current characters are the same, consider the common subsequence in both
        if (str1.charAt(m) == str2.charAt(n)) {
            return countCommonSubsequences(str1, str2, m + 1, n + 1) + 1
                    + countCommonSubsequences(str1, str2, m + 1, n)
                    + countCommonSubsequences(str1, str2, m, n + 1)
                    - countCommonSubsequences(str1, str2, m + 1, n + 1);
        } else {
            // If the current characters are different, move forward in one of the strings
            return countCommonSubsequences(str1, str2, m + 1, n) + countCommonSubsequences(str1, str2, m, n + 1)
                    - countCommonSubsequences(str1, str2, m + 1, n + 1);
        }
    }

    public static void main(String[] args) {
        String str1 = "ajblqcpdz";
        String str2 = "aefcnbtdi";
        int result = countCommonSubsequences(str1, str2, 0, 0);
        System.out.println("Number of common subsequences: " + result);
    }
}
