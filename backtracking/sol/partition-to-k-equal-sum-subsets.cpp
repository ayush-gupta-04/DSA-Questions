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





class Solution {
    public void reverse(int[] nums){
        int i = 0;
        int j = nums.length-1;
        while(i < j){
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
            i++;j--;
        }
    }
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for (int num : nums) sum += num;
        if (sum % k != 0) return false;

        int target = sum / k;
        int n = nums.length;

        // Sort ascending, then reverse to descending order
        Arrays.sort(nums);
        reverse(nums);

        if (nums[0] > target) return false;

        boolean[] visited = new boolean[n];
        return backtrack(nums, visited, 0, k, 0, target);
    }

    private boolean backtrack(int[] nums, boolean[] visited, int start, int k, int currentSum, int target) {
        // Base case: k - 1 buckets filled means the last bucket is guaranteed
        if (k == 1) return true;

        // Current bucket complete, transition to the next bucket
        if (currentSum == target) {
            return backtrack(nums, visited, 0, k - 1, 0, target);
        }

        for (int i = start; i < nums.length; i++) {
            if (visited[i] || currentSum + nums[i] > target) continue;

            visited[i] = true;
            if (backtrack(nums, visited, i + 1, k, currentSum + nums[i], target)) {
                return true;
            }
            visited[i] = false;

            // Pruning : Symmetrical empty bucket failure
            if (currentSum == 0) break;
        }

        return false;
    }
}
