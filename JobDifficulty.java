package DSA.DynamicProgramming.Basics;

import java.util.Arrays;

public class JobDifficulty {
    public int minDifficulty(int[] jobDifficulty, int d) {
        if (jobDifficulty.length < d) {
            return -1;
        }
        int len = jobDifficulty.length;
        int sum = Arrays.stream(jobDifficulty).sum();
        if (sum == 0) {
            return 0;
        }
        int[][] memo = new int[len][d + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        return helper(jobDifficulty, d, 0, memo);
    }

    private int helper(int[] jd, int daysLeft, int idx, int[][] memo) {
        int len = jd.length;
        if (daysLeft == 1) {
            int num = 0;
            for (int i = idx; i < len; i++) {
                num = Math.max(num, jd[i]);
            }
            return num;
        }

        if (memo[idx][daysLeft] != -1) {
            return memo[idx][daysLeft];
        }

        int maxInDay1 = jd[idx];
        int stop = len - idx - daysLeft + 1;

        int Res = Integer.MAX_VALUE;
        for (int i = 1; i <= stop; i++) {
            maxInDay1 = Math.max(maxInDay1, jd[idx + i - 1]);
            Res = Math.min(Res, maxInDay1 + helper(jd, daysLeft - 1, idx + i, memo));
        }

        memo[idx][daysLeft] = Res;
        return Res;
    }
}