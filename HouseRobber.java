package DSA.DynamicProgramming.Basics;

import DSA.BinaryTrees.Basics.CustomBinaryTree;
import DSA.BinaryTrees.Basics.DiameterOfBinaryTree;

import java.util.HashMap;

public class HouseRobber extends CustomBinaryTree {
    public static int rob(Node root){

        HashMap<Node,Integer> Dp=new HashMap<>();

        return GetMaxSumOfSubseq(root,Dp);

    }

    private static int GetMaxSumOfSubseq(Node Elem, HashMap<Node,Integer> Dp) {
        if(Elem==null){
            return 0;
        }
        if(Dp.get(Elem)!=null){
            return Dp.get(Elem);
        }

        int MaxFromPick=0;
        int MaxFromNonPick=0;



        if(Elem.left!=null) {
             MaxFromPick+= GetMaxSumOfSubseq(Elem.left.left,Dp)+GetMaxSumOfSubseq(Elem.left.right,Dp);
        }
        if(Elem.right!=null){
            MaxFromPick+= GetMaxSumOfSubseq(Elem.right.left,Dp)+GetMaxSumOfSubseq(Elem.right.right,Dp);
         }
        MaxFromPick+=Elem.value;



        MaxFromNonPick=GetMaxSumOfSubseq(Elem.left,Dp)+GetMaxSumOfSubseq(Elem.right,Dp);

        int Ans= Math.max(MaxFromPick,MaxFromNonPick);
        Dp.put(Elem,Ans);
        return Ans;
    }

    public static void main(String[] args) {
        CustomBinaryTree Bt = new CustomBinaryTree();
        DiameterOfBinaryTree Obj = new DiameterOfBinaryTree();
//        Integer[] Arr = {3,4,1,null,null,3,null,null,5,null,1,null,null};
        Integer[] Arr = {0,0,0,null,null,0,null,null,0,null,0,null,null};
//        Integer[] Arr = {1,2,3,null,null,null,null};
        Bt.add(Arr);
        Bt.prettyDisplay();

        System.out.println(rob(Bt.root));
    }
}
