package DSA.DynamicProgramming.Basics;

import java.util.Arrays;
import java.util.HashMap;

public class BhattJumps {
    static int Count=0;
    public static boolean canCross(int[] Arr) {
        HashMap<String,Integer> Dp=new HashMap<>();
        HashMap<Integer, Integer> Hm = new HashMap<>();
        for (int i = 0; i < Arr.length; i++) {
            Hm.put(Arr[i], i);
        }

        boolean Res= CanCross(Arr, 1, 1, Hm,Dp) == 1;
        System.out.println(Hm);
        return Res;

    }

    public static int CanCross(int[] Arr, int SC, int K, HashMap<Integer, Integer> Hm,HashMap<String,Integer> Dp) {
        if (SC < 0 || SC >= Arr.length || K == 0) {
            return 0;
        }
        System.out.println(++Count);
        if (SC == Arr.length - 1) {
            return 1;
        }

        String Key=SC+"-"+K;
        if(Dp.get(Key)!=null){
            return Dp.get(Key);
        }
        int CanJump = 0;

        for (int i = -1; i <= 1; i++) {
            if (Hm.containsKey(Arr[SC] + K + i)) {
                int Index = Hm.get(Arr[SC] + K + i);
                CanJump = CanCross(Arr, Index, K + i, Hm,Dp);
                if (CanJump == 1) {
                    Dp.put(Key,1);
                    return 1;
                }
            }
        }
        Dp.put(Key,0);
        return 0;
    }

    public static void main(String[] args) {
        int[] Arr = {0, 1, 3, 5, 6, 8, 12, 17};
//        int[] Arr={0,1,2,3,4,8,9,11};
        System.out.println(canCross(Arr));
    }
}
