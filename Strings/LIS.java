package DSA.DynamicProgramming.Basics.Strings;
import java.util.*;

public class LIS {
    public int lengthOfLIS(int[] nums) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int Res = 0;  // Initialize the result variable
        for(int num : nums){
            Integer greater = map.ceilingKey(num);
            if(greater != null){
                map.remove(greater);
            }
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        // Sum the values in the map to get the result
        for(int val : map.values()){
            System.out.println(val);
            Res += val;
        }

        return Res;
    }
}
