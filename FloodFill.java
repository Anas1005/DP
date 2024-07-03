package DSA.DynamicProgramming.Basics;

import java.awt.event.FocusAdapter;
import java.util.ArrayList;

public class FloodFill {
    static ArrayList<String> GetFloodFill(int[][] Arr, int SR, int SC, boolean[][] Visited) {

        if(SR<0||SR>=Arr.length||SC<0||SC>=Arr[0].length||Arr[SR][SC]==1||Visited[SR][SC]){
            return new ArrayList<>();
        }
        if (SR == Arr.length-1 & SC == Arr[0].length-1) {
            ArrayList<String> Temp = new ArrayList<>();
            Temp.add("");
            return Temp;
        }

        ArrayList<String> Result = new ArrayList<>();
        ArrayList<String> FirstTop = new ArrayList<>();
        ArrayList<String> FirstLeft = new ArrayList<>();
        ArrayList<String> FirstDown = new ArrayList<>();
        ArrayList<String> FirstRight = new ArrayList<>();

            Visited[SR][SC] = true;
            FirstTop = GetFloodFill(Arr, SR - 1, SC, Visited);
            for (String Elem : FirstTop) {
                Result.add("T" + Elem);
            }
            FirstLeft = GetFloodFill(Arr, SR, SC - 1, Visited);
            for (String Elem : FirstLeft) {
                Result.add("L" + Elem);
            }
            FirstDown = GetFloodFill(Arr, SR + 1, SC, Visited);
            for (String Elem : FirstDown) {
                Result.add("B" + Elem);
            }
            FirstRight = GetFloodFill(Arr, SR, SC + 1, Visited);
            for (String Elem : FirstRight) {
                Result.add("R" + Elem);
            }
            Visited[SR][SC]=false;

        return Result;
    }

    static int CountFloodFill(int[][] Arr, int SR, int SC, boolean[][] Visited) {
        if(SR<0||SR>=Arr.length||SC<0||SC>=Arr[0].length||Arr[SR][SC]==1||Visited[SR][SC]){
            return 0;
        }
        if (SR == Arr.length-1 & SC == Arr[0].length-1) {
            return 1;
        }

        Visited[SR][SC] = true;
        int Top=CountFloodFill(Arr, SR - 1, SC, Visited);

        int Left=CountFloodFill(Arr, SR, SC - 1, Visited);

        int Bottom=CountFloodFill(Arr, SR + 1, SC, Visited);

        int Right=CountFloodFill(Arr, SR, SC + 1, Visited);

        Visited[SR][SC]=false;

        return Top+Left+Bottom+Right;

    }
    static void PrintFloodFill(int[][] Arr, int SR, int SC, boolean[][] Visited,String Ans) {

        if(SR<0||SR>=Arr.length||SC<0||SC>=Arr[0].length||Arr[SR][SC]==1||Visited[SR][SC]){
            return;
        }
        if (SR == Arr.length-1 & SC == Arr[0].length-1) {
            System.out.println(Ans);
            return ;
        }

        Visited[SR][SC] = true;
        PrintFloodFill(Arr, SR - 1, SC, Visited,Ans+"T");

        PrintFloodFill(Arr, SR, SC - 1, Visited,Ans+"L");

        PrintFloodFill(Arr, SR + 1, SC, Visited,Ans+"B");

        PrintFloodFill(Arr, SR, SC + 1, Visited,Ans+"R");

        Visited[SR][SC]=false;
    }

    public static void main(String[] args) {
        int[][] Arr ={{0,0,0,0},
                      {0,0,0,0},
                      {0,0,0,0}};
        boolean[][] Visited =new boolean[4][4];
        System.out.println(GetFloodFill(Arr,0,0,Visited));
//        PrintFloodFill(Arr,0,0,Visited,"");
//        System.out.println(CountFloodFill(Arr,0,0,Visited));
    }
}
