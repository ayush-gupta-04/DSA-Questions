// Method 1 :
// for every element of nums .. choose which subset to put it to.

// Method 2 :
// 1. Build 1 subset by choosing any elemnet from nums using backtracking.
// 2. If 1 subset is built then build next one.
// 3. Pruning techniques :
//    -> if remaining element for subset 1st is {a,b,c,d}.
//       - if starting the subset with [a , ...] fails .. then it will fail 100% even if starting with b,c,d.
//    -> sort nums in DESC order.
//       - to build 5 we can have multiple smaller element .. [1,1,1,1,1] or [1,2,2] .. 
//       - failing will take time to check.
//       - with big numbers tree willl have smaller branches.
//    -> check if nums[0] > target .. return false;





class Solution {
public:
    bool fun(vector<bool>& vis , int target , int currSum , int k , vector<int>& nums){
        if(k == 0){
            return true;
        }

        if(currSum == target){
            return fun(vis,target,0,k-1,nums);
        }

        for(int i = 0; i < nums.size() ; i++){
            if (vis[i]) continue;
            if (currSum + nums[i] > target) continue;

            vis[i] = true;
            if(fun(vis , target , currSum + nums[i],k , nums)){
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

    bool canPartitionKSubsets(vector<int>& nums, int k) {
        int sum = 0;
        for(auto &it : nums){
            sum += it;
        }

        if(sum % k != 0) return false;
        vector<bool> vis(nums.size() , false);
        sort(nums.rbegin() , nums.rend());
        if (nums[0] > sum/k) return false;
        return fun(vis,sum/k , 0,k,nums);
    }
};
