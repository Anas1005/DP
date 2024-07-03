package DSA.DynamicProgramming.Basics.Strings;

public class RegularExpressionMatching {
    public static boolean isMatch(String Original, String Special) {

        return isMatch(Original,Special,0,0);



    }

    public static boolean isMatch(String Original, String Special,int i1, int i2) {
        if (i1 >= Original.length() && i2 >= Special.length()) {    // Both Strings have Exhausted....
            return true;
        }
        if (i2 >= Special.length()) {          // If Special String has been Exhausted but Original has not been Exhausted Yet....
            return false;
        }

        if(i1>=Original.length()){
            return (i2 == Special.length() - 1 ) && Special.charAt(i2) == '*';
        }


        if (Original.charAt(i1) == Special.charAt(i2) || Special.charAt(i2) == '.') {
            return isMatch(Original,Special,i1+1,i2+1);
        }

        if (Special.charAt(i2) == '*') {
            if(Special.charAt(i2-1)==Original.charAt(i1) || Special.charAt(i2-1)=='.'){
                for (int i = 0; i <= Original.length() - i1; i++) {
                    if(isMatch(Original,Special,i1+i,i2+1)){
                        return true;
                    }
                }
                return false;
            }
            else {
                return isMatch(Original,Special,i1,i2+1);
            }
        }
        return false;
    }




    public static void main(String[] args) {
        System.out.println(isMatch("mississippi","mis*is*p*"));
    }
}
