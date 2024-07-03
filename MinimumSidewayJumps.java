package DSA.DynamicProgramming.Basics;

import java.util.Arrays;

public class MinimumSidewayJumps {
    final static int INFINITY = 1000000000;

    public static int minSideJumps(int[] Arr) {
        int[][] Dp=new int[Arr.length][3];
        for(int[] Elem:Dp) {
            Arrays.fill(Elem, -1);
        }
        int Res= MinSideJumps(Arr,0,2,Dp,"");
        for(int[] Elem:Dp){
            System.out.println(Arrays.toString(Elem));
        }
        return Res;
    }

    private static int MinSideJumps(int[] Arr, int Point, int Lane, int[][] Dp, String Ans) {
        if(Arr[Point]==Lane){
            return INFINITY;
        }
        if(Point==Arr.length-1){
            System.out.println(Ans+Lane+"-"+Point);
            return Dp[Point][Lane-1]=0;
        }

        if(Dp[Point][Lane-1]!=-1){
            return Dp[Point][Lane-1];
        }

        int Min1=MinSideJumps(Arr,Point+1,Lane, Dp, Ans+Lane+"-"+Point+"-->");

        if(Min1!=INFINITY){
            return Min1;
        }

        int Min2=INFINITY;
        for(int i=1;i<=3;i++){
            if(i!=Lane) {
                Min2 = Math.min(Min2, MinSideJumps(Arr, Point, i, Dp, Ans + Lane + "-" + Point + "-->"));
            }
        }

        return Dp[Point][Lane-1]= Min2+1;
    }

    public static void main(String[] args) {
//         int[] Arr={0,1,2,3,0};
        int[] Arr={0,1,2,3,0};
        System.out.println(Arr.length);
        System.out.println(minSideJumps(Arr));
    }
}
