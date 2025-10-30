import java.util.*;

public class Main {
    static int maxSubarray(int[] nums , int k){
        int n = nums.length;
        HashMap<Integer , Integer> map = new HashMap<>();

        // if the current sum is 5 and k = 5 .. rem will be 0.
        // it will try to look for 0 in the map.. it must find it.
        map.put(0,-1);
        int max = 0;
        int sum = 0;
        for(int i = 0 ; i < n; i++){
            sum += nums[i];
            int rem = sum - k;
            if(map.containsKey(rem)){
                int left = map.get(rem);
                max = Math.max(max , i - left);
            }

            //it is a special edge case.
            // what if sum = 5 encounters again ?..
            // if i update the index of 5 in the map .. 
            //    then how would i be able to get the max length.
            if(!map.containsKey(sum)){
                map.put(sum , i);
            }
        }
        return max;
    }
    public static void main(String[] args){
        int[] nums = {10, -10, 20, 30};
        int k = 5;
        System.out.println(maxSubarray(nums , k));
    }
}
