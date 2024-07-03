package DSA.DynamicProgramming.Basics;

import java.util.Arrays;

public class FrogWithKJumps {

    public static int frogJump(int n, int[] Heights,int K) {
        System.out.println(n);
        System.out.println(Arrays.toString(Heights));
//        return minEnergyByRecursion(n,Heights,K);
        return minEnergyByMemoization(n,Heights,K,new int[n]);
//           return minEnergyByTabulation(n,Heights,K);
//        return  minEnergyByMostOptimised(n,Heights);
    }

    static int minEnergyByRecursion(int n, int[] Heights,int K){
        if(n==1){
            return 0;
        }
        int MinEnergy=Integer.MAX_VALUE;
        for(int i=1;i<=K;i++){
            if(n>=(i+1)) {
                MinEnergy = Math.min(MinEnergy, minEnergyByRecursion(n - i, Heights, K) + Math.abs(Heights[n - 1] - Heights[n - i - 1]));
            }
        }
        return MinEnergy;
    }
    static int minEnergyByMemoization(int n, int[] Heights,int K,int[] Dp){
        if(n==1){
            Dp[0]=0;
            return 0;
        }
        if(Dp[n-1]!=0){
            return Dp[n-1];
        }
        int MinEnergy=Integer.MAX_VALUE;
        for(int i=1;i<=K;i++){
            if(n>=(i+1)) {
                MinEnergy = Math.min(MinEnergy, minEnergyByMemoization(n - i, Heights, K,Dp) + Math.abs(Heights[n - 1] - Heights[n - i - 1]));
            }
        }
        Dp[n-1]=MinEnergy;
        return MinEnergy;
    }

    static int minEnergyByTabulation(int n,int[] Heights,int K) {
        int[] Dp = new int[n];
        Dp[0] = 0;


        for (int i = 1; i < Dp.length; i++) {
            int MinEnergy = Integer.MAX_VALUE;
            for (int j = 1; j <= K; j++) {
                if (i - j >= 0) {
                    MinEnergy = Math.min(MinEnergy, Dp[i - j] + Math.abs(Heights[i] - Heights[i - j]));
                }
            }
            Dp[i] = MinEnergy;
        }
        return Dp[n-1];
    }
    public static void main(String[] args) {
        int[] Heights ={2,1,4,2,4,2,5,2,4,12,4,1,2,1,4,2,4,1,2,3,1,2,5,3,1,4,1,4,1,3,1,4,1,34,1,4,6,4,2,3};
        System.out.println(frogJump(Heights.length,Heights,4));
    }
}
