package DSA.DynamicProgramming.Basics.Subsequences;

public class DivideIntoKSubsets {
        public static boolean canPartitionKSubsets(int[] Arr, int K) {
            int TotalSum=0;
            for(int Elem : Arr){
                TotalSum+= Elem;
            }
            if(TotalSum%K!=0 || Arr.length<K){
                return false;
            }

            return canDivideIntoKSubsets(Arr,0,new int[K],K,TotalSum/K);
        }


        public  static boolean canDivideIntoKSubsets(int[] arr, int currentIndex, int[] subsetSums, int K, int targetSum) {
            if (currentIndex == arr.length) {
                for (int sum : subsetSums) {
                    if (sum != targetSum) {
                        return false;
                    }
                }
                return true;
            }


            for (int i = 0; i < K; i++) {
                if ((subsetSums[i] + arr[currentIndex] <= targetSum)&&(!(i>0 && subsetSums[i] == subsetSums[i-1]))){
                    subsetSums[i] += arr[currentIndex];
                    if (canDivideIntoKSubsets(arr, currentIndex + 1, subsetSums, K, targetSum)) {
                        return true;
                    }
                    subsetSums[i] -= arr[currentIndex];  // Backtrack
                }
            }

            return false;
        }

    public static void main(String[] args) {

        System.out.println(canPartitionKSubsets(new int[]{1,1,1,1,2,2,2,2},4));

    }

}
