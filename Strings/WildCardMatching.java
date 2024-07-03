package DSA.DynamicProgramming.Basics.Strings;

public class WildCardMatching {

    private static Integer[][] memo;

    public static boolean isMatch(String Original, String Special) {
        memo = new Integer[Original.length() + 1][Special.length() + 1];
        return isMatch(Original, Special, 0, 0);
    }

    public static boolean isMatch(String Original, String Special, int i1, int i2) {
        if (i1 >= Original.length() && i2 >= Special.length()) {    // Both Strings have Exhausted....
            return true;
        }
        if (i2 >= Special.length()) {          // If Special String has been Exhausted but Original has not been Exhausted Yet....
            return false;
        }
        if (i1 >= Original.length()) {     // If Original String has been Exhausted but Special has not been Exhausted Yet....
                                          // Returning True If All other remaining Characters of Special String are "****" only......
            for (int i = i2; i < Special.length(); i++) {
                if (Special.charAt(i) != '*') {
                    return false;
                }
            }
            return true;
        }


        if (memo[i1][i2] != null) {
            return memo[i1][i2] == 1;
        }


        boolean Res;

        if (Original.charAt(i1) == Special.charAt(i2) || Special.charAt(i2) == '?') {
            Res = isMatch(Original, Special, i1 + 1, i2 + 1);
            memo[i1][i2] = Res ? 1 : 0;
            return Res;

        }
        if (Special.charAt(i2) == '*') {
            for (int i = 0; i < Original.length() - i1; i++) {
                if (isMatch(Original, Special, i1 + i, i2 + 1)) {
                    memo[i1][i2] = 1;
                    return true;
                }
            }
            memo[i1][i2] = 0;
            return false;
        }

        memo[i1][i2] = 0;
        return false;

    }

    public static void main(String[] args) {
        System.out.println(isMatch("dcfedba","dc*ba"));
    }
}
