package DSA.DynamicProgramming.Basics;

public class GridUniquePathsIII {
    public static int uniquePathsIII(int[][] Arr) {
        int Count = 0;
        int SR = 0;
        int SC = 0;
        int DR = 0;
        int DC = 0;
        for (int i = 0; i < Arr.length; i++) {
            for (int j = 0; j < Arr[0].length; j++) {
                if (Arr[i][j] == 1) {
                    SR = i;
                    SC = j;
                }
                if (Arr[i][j] == -1) {
                    Count++;
                }
                if (Arr[i][j] == 2) {
                    DR = i;
                    DC = j;
                }
            }
        }
        int TotalNeedToBeVisited = Arr.length * Arr[0].length - Count;

        return CountFloodFill(Arr, SR, SC, DR, DC, new boolean[Arr.length][Arr[0].length], TotalNeedToBeVisited, "");
    }

    static int CountFloodFill(int[][] Arr, int SR, int SC, int DR, int DC, boolean[][] Visited, int Total, String Ans) {
        if (SR < 0 || SR >= Arr.length || SC < 0 || SC >= Arr[0].length || Arr[SR][SC] == -1 || Visited[SR][SC]) {
            return 0;
        }
        if (SR == DR && SC == DC) {
            if (Ans.length()+1 == Total) {
                System.out.println(Ans);
                return 1;
            } else {
                return 0;
            }
        }

        Visited[SR][SC] = true;
        int Top =    CountFloodFill(Arr, SR - 1, SC, DR, DC, Visited, Total, Ans + "T");
        int Right =  CountFloodFill(Arr, SR, SC + 1, DR, DC, Visited, Total, Ans + "R");
        int Bottom = CountFloodFill(Arr, SR + 1, SC, DR, DC, Visited, Total, Ans + "B");
        int Left =   CountFloodFill(Arr, SR, SC - 1, DR, DC, Visited, Total, Ans + "L");
        Visited[SR][SC] = false;

        return Top + Left + Bottom + Right;

    }

    public static void main(String[] args) {
        int[][] Arr = {{1, 0, 0, 0},
                       {0, -1, 0, 0},
                       {0, 0,2,-1}};
        boolean[][] Visited = new boolean[4][4];
        System.out.println(uniquePathsIII(Arr));
    }
}

