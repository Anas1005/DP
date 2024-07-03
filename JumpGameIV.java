package DSA.DynamicProgramming.Basics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class JumpGameIV {
    static int PrevIndex=-1;
    public static int minJumps(int[] Arr) {
        HashMap<Integer, ArrayList<Integer>> Hm=new HashMap<>();
        for(int i=0;i<Arr.length;i++) {
            if (Hm.get(Arr[i]) == null) {
                ArrayList<Integer> Indexes = new ArrayList<>();
                Indexes.add(i);
                Hm.put(Arr[i], Indexes);
            } else {
                ArrayList<Integer> Indexes = Hm.get(Arr[i]);
                Indexes.add(i);
                Hm.put(Arr[i], Indexes);
            }
        }
//        System.out.println(Hm);

        int[] Dp=new int[Arr.length];
        Arrays.fill(Dp,-1);
//        int Res= MinJumps(Arr, 0,new boolean[Arr.length],Hm,Dp,"");
        int Res= MinJumps(Arr, 0,new boolean[Arr.length],Hm,Dp,"");
//        System.out.println(Arrays.toString(Dp));
        return Res;
    }

//    private static int MinJumps(int[] Arr, int SC, HashMap<Integer, ArrayList<Integer>> Hm,int[] Dp,String Ans) {
//        if (SC < 0 || SC >= Arr.length||SC==PrevIndex) {
//            return Integer.MAX_VALUE;
//        }
////        System.out.print(SC+"==>");
//        if (SC == Arr.length - 1) {
//            Ans+=Arr[SC];
//            return 0;
//        }
//
//        if(Dp[SC]!=-1){
//            return Dp[SC];
//        }
//
//        System.out.print(PrevIndex+":");
//        int MinJumps = Integer.MAX_VALUE;
////        Visited[SC]=true;
//        int ImmediateRight = MinJumps(Arr, SC + 1,Hm,Dp,Ans+Arr[SC]+"-->");
//        PrevIndex=SC;
//        int ImmediateLeft = MinJumps(Arr, SC - 1,Hm,Dp,Ans+Arr[SC]+"-->");
//        PrevIndex=SC;
//        MinJumps = Math.min(ImmediateRight, ImmediateLeft);
//
//        int ExtraJumps=Integer.MAX_VALUE;
//        for (int index:Hm.get(Arr[SC])) {
//            if(index!=SC){
//                ExtraJumps=Math.min(ExtraJumps,MinJumps(Arr,index,Hm,Dp,Ans+Arr[SC]+"-->"));
//                PrevIndex=SC;
////                MinJumps = Math.min(MinJumps, MinJumps(Arr,index,Visited,Hm,Dp));
//            }
//        }
//        MinJumps=Math.min(MinJumps,ExtraJumps);
////        Visited[SC]=false;
//        int Res=MinJumps==Integer.MAX_VALUE?Integer.MAX_VALUE:MinJumps + 1;
////        System.out.println(Arr[SC]+"--->"+ImmediateRight+":"+ImmediateLeft+":"+ExtraJumps+"--->"+MinJumps);
////        System.out.println(Arr[SC]+"--->"+MinJumps);
//
//        return Res;
//
//    }
private static int MinJumps(int[] Arr, int SC, boolean[] Visited, HashMap<Integer, ArrayList<Integer>> Hm,int[] Dp,String Ans) {
    if (SC < 0 || SC >= Arr.length||Visited[SC]) {
        return Integer.MAX_VALUE;
    }
    System.out.print(Arr[SC]+"==>");
//        System.out.println(++Count);
    if (SC == Arr.length - 1) {
        Ans+=Arr[SC];
//        System.out.println(Ans);
        return 0;
    }

//    if(Dp[SC]!=-1){
//        return Dp[SC];
//    }

    int MinJumps = Integer.MAX_VALUE;
    Visited[SC]=true;
    int ImmediateRight = MinJumps(Arr, SC + 1,Visited,Hm,Dp,Ans+Arr[SC]+"-->");
    int ImmediateLeft = MinJumps(Arr, SC - 1,Visited,Hm,Dp,Ans+Arr[SC]+"-->");
    MinJumps = Math.min(ImmediateRight, ImmediateLeft);

    int ExtraJumps=Integer.MAX_VALUE;
    for (int index:Hm.get(Arr[SC])) {
        if(index!=SC){
            ExtraJumps=Math.min(ExtraJumps,MinJumps(Arr,index,Visited,Hm,Dp,Ans+Arr[SC]+"-->"));
//                MinJumps = Math.min(MinJumps, MinJumps(Arr,index,Visited,Hm,Dp));
        }
    }
    MinJumps=Math.min(MinJumps,ExtraJumps);
    Visited[SC]=false;
    int Res=MinJumps==Integer.MAX_VALUE?Integer.MAX_VALUE:MinJumps + 1;
//        System.out.println(Arr[SC]+"--->"+ImmediateRight+":"+ImmediateLeft+":"+ExtraJumps+"--->"+MinJumps);
//     System.out.println(Arr[SC]+"--->"+Res);

    return Res;

}

    public static void main(String[] args) {
        int[] Arr = {100,11,1,3,100,12,13,3};
//        int[] Arr={7,6,9,6,9,6,9,7};
        System.out.println(minJumps(Arr));
    }
}
