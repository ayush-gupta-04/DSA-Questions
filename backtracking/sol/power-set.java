// ---------------- Approach 1 : recursion -----------------------
// TC -> 2^N
//    -> 2^N : recursion
// SC -> N*(2^N) + N
//    -> N*(2^N) : storing ans
//    -> N : resursive stack space
import java.util.*;

public class Main {
    static List<List<Integer>> res = new ArrayList<>();
    static void fun(int[] nums , int i, List<Integer> list){
        if(i == nums.length){
            res.add(new ArrayList<>(list));
            return;
        }

        list.add(nums[i]);
        fun(nums, i + 1 , list);
        list.removeLast();

        fun(nums , i + 1 , list);
    }
    public static void main(String[] args) {
       int[] nums = {1,2,3};
       fun(nums,  0  , new ArrayList<>());
       System.out.println(res);
    }
}



// ------------------- Approach 2 : Bit-Manipulation --------------
// TC -> 2^N
//    -> 2^N : recursion
// SC -> N*(2^N) 
//    -> N*(2^N) : storing ans

import java.util.*;

public class Main {
    static List<List<Integer>> powerSet(int[] nums){
        List<List<Integer>> res = new ArrayList<>();
        int n = nums.length;
        for(int mask = 0 ; mask < (1<<n) ; mask++){
            List<Integer> list = new ArrayList<>();
            for(int i = 0; i < n;i++){
                if(((mask>>i)&1) == 1){
                    list.add(nums[i]);
                }
            }
            res.add(list);
        }
        return res;
    }
    public static void main(String[] args) {
       int[] nums = {1,2,3};
       System.out.println(powerSet(nums));
    }
}
