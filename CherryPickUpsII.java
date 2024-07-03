package DSA.DynamicProgramming.Basics;

import java.util.Arrays;

public class CherryPickUpsII {
    static final int MINUS_INFINITY = -1000000000;
    static  Integer[][][] memo;

    public static int cherryPickup(int[][] Arr) {
        memo = new Integer[Arr.length][Arr[0].length][Arr[0].length];
        int ans = cherryPickUp(Arr, 0, 0, Arr[0].length - 1);
        return Math.max(0, ans);
    }

    public static int cherryPickUp(int[][] grid, int i, int j1, int j2) {
        if (j1 >= grid[0].length || j2 >= grid[0].length || j1 < 0 || j2 < 0) {
            return MINUS_INFINITY;
        }

        if (memo[i][j1][j2] != null) {
            return memo[i][j1][j2];
        }

        if (i == grid.length - 1) {
            if (j1 == j2) {
                return grid[i][j1];
            } else {
                return grid[i][j1] + grid[i][j2];
            }
        }

        int cherry = (j1 == j2) ? grid[i][j1] : grid[i][j1] + grid[i][j2];
        int maxCherry = MINUS_INFINITY;

        for (int dir1 = -1; dir1 <= 1; dir1++) {
            for (int dir2 = -1; dir2 <= 1; dir2++) {
                int newJ1 = j1 + dir1;
                int newJ2 = j2 + dir2;
                int nextCherry = cherryPickUp(grid, i + 1, newJ1, newJ2);
                maxCherry = Math.max(maxCherry, nextCherry + cherry);
            }

        }

        memo[i][j1][j2] = maxCherry;


        return maxCherry;
    }


    public static void main(String[] args) {
        int[][] Arr=
                        {{3,1,1},
                        {2,5,1},
                        {1,5,5},
                        {2,1,1}};

        System.out.println(cherryPickup(Arr));
    }
}
