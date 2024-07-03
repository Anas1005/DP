package DSA.DynamicProgramming.Basics;

import java.util.Arrays;

public class FrogJumps {
    public static int frogJump(int n, int[] Heights) {
        System.out.println(n);
        System.out.println(Arrays.toString(Heights));
//           return minEnergyByRecursion(n,Heights);
        return minEnergyByMemoization(n,Heights,new int[n]);
//        return minEnergyByTabulation(n,Heights);
//        return  minEnergyByMostOptimised(n,Heights);
    }

    static int minEnergyByRecursion(int n, int[] Heights){
        if(n==1){
            return 0;
        }
        int LeftMin=Integer.MAX_VALUE;
        int RightMin=Integer.MAX_VALUE;

        if(n>=2)
        LeftMin=minEnergyByRecursion(n-1,Heights)+Math.abs(Heights[n-1]-Heights[n-2]);

        if(n>=3)
        RightMin=minEnergyByRecursion(n-2,Heights)+Math.abs(Heights[n-1]-Heights[n-3]);

        int Ans=Math.min(LeftMin,RightMin);
        return Ans;
    }

    static int minEnergyByMemoization(int n, int[] Heights, int[] MinEnergy){
        if(n==1){
            MinEnergy[0]=0;
            return 0;
        }
        if(n==2){
            MinEnergy[1]=Math.abs(Heights[1]-Heights[0]);
            return Math.abs(Heights[1]-Heights[0]);
        }
        if(MinEnergy[n-1]!=0){
            return MinEnergy[n-1];
        }
        int LeftMin=minEnergyByMemoization(n-1,Heights,MinEnergy)+Math.abs(Heights[n-1]-Heights[n-2]);
        int RightMin=minEnergyByMemoization(n-2,Heights,MinEnergy)+Math.abs(Heights[n-1]-Heights[n-3]);
        int Ans=Math.min(LeftMin,RightMin);
        MinEnergy[n-1]=Ans;
        return Ans;
    }
    static int minEnergyByTabulation(int n,int[] Heights){
        int[] Dp=new int[n];
        Dp[0]=0;

        int OneStep=Integer.MAX_VALUE;
        int TwoStep=Integer.MAX_VALUE;
        for (int i=1;i<Dp.length;i++){
            if(i>=1)
             OneStep=Dp[i-1]+Math.abs(Heights[i]-Heights[i-1]);

             if(i>=2)
                 TwoStep=Dp[i-2]+Math.abs(Heights[i]-Heights[i-2]);

            Dp[i]=Math.min(OneStep,TwoStep);
        }
        System.out.println(Arrays.toString(Dp));
        return Dp[n-1];
    }
    static int minEnergyByMostOptimised(int n,int[] Heights) {
        int PrevPrev=0;
        int Prev = Math.abs(Heights[1] - Heights[0]);
        int Curr= Prev;

        for (int i = 2; i < n; i++) {
            Curr = Math.min(Prev + Math.abs(Heights[i] - Heights[i - 1]), PrevPrev + Math.abs(Heights[i] - Heights[i - 2]));
            PrevPrev=Prev;
            Prev=Curr;
        }
        return Curr;
    }


        public static void main(String[] args) {
//        int[] Heights ={2,1,4,2,4,2,5,2,4,12,4,2,34,2,4,2,4,2,4,2,4,7,4,4,2,6,6,3,4,2,13,1,3,4,1,2,3,1,2,3,1,4,1,3,1,4,1,4,1,3,12,31,12,3,13,13,11,1,2};
//            int[] Heights ={2,1,4,2,4,2,5,2,4,12,4,2,34,2,4,2,4,2,4,2,4,7,4,13,11,1,2};
            int[] Heights ={2,1,4,2,4,2,5,2,4,12,4,1,2};
            System.out.println(frogJump(Heights.length,Heights));
    }
}
