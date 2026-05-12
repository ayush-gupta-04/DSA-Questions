// Method 1 :
// for every element of nums .. choose which subset to put it to.

// Method 2 :
// 1. Build 1 subset by choosing elements from nums using backtracking.
// 2. If 1 subset is built then build next one.
// 3. Pruning techniques :
//    -> Feasibility Pruning : 
//         1. If the largestElement > target  .. where target is sum/k .... return false directly.
//         2. While selecting elements for a bucket : 
//            -> if (currSum + nums[i] > target) continue;
//            -> try to put next num into the bucket.
//    -> Greedy Ordering
//       - If i need to build a number by the combination of multiple numbers .. like here I need to build the currSum (of a bucket) from nums.
//       - Then , remember this greedy ordering.
//       -> Choose Larger number first to check if it is feseable or not.
//       -> Example.
//             -> To build 5 we can have multiple smaller element .. [1,1,1,1,1] or [1,2,2] .. 
//             -> failing will take time to check, with big numbers tree willl have smaller branches.
//    -> Symmetry Breaking (The Bucket Exception)
//       - if remaining element for subset 1st is {a,b,c,d}.
//       - and if starting the subset with [a , ...] fails .. then it will fail 100% even if starting with b,c,d.
//       - because if i start wtih [a , ...] i had {b,c,d} as option to put with a .. and it failed.
//       - so if i start with [b , ... ] i will have {a,c,d} as options and it will fail for sure.
//       - because in both the cases the conbinations are exactly the same.





import java.util.*;

class Solution {

    public boolean fun(boolean[] vis, int target, int currSum, int k, int[] nums) {
        if (k == 0) {
            return true;
        }

        if (currSum == target) {
            return fun(vis, target, 0, k - 1, nums);
        }

        for (int i = nums.length-1; i >= 0; i--) {
            if (vis[i]) continue;
            if (currSum + nums[i] > target) continue;

            vis[i] = true;
            if (fun(vis, target, currSum + nums[i], k, nums)) {
                return true;
            }
            vis[i] = false;

            // pruning:
            // if combination with this first-choice(i am starting the subset) fails .. 
            // then i know for sure that combination starting with any other choice later will fail 100%.
            if (currSum == 0) return false;
        }

        return false;
    }

    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        int n = nums.length;
        for (int a : nums) {
            sum += a;
        }
        if (sum % k != 0) return false;
        Arrays.sort(nums);
        if (nums[n-1] > sum / k) return false;
        boolean[] vis = new boolean[n];
        return fun(vis, sum / k, 0, k, nums);
    }
}
