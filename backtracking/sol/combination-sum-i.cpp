class Solution {
public:
    vector<vector<int>> result;
    void fun(vector<int>& nums , int i ,int k ,vector<int>& list){
        // whenver k == 0 .. i don't need to go to the end... just return the ans from here.
        if(k == 0){
            result.push_back(list);
            return;
        }

        // if i found no ans during the middle .. and reached the end .. just return.
        if(i == nums.size()){
            return;
        }

        // not take case;
        fun(nums , i + 1, k , list);

        // take only if i can.
        if(k >= nums[i]){
            // since hum iss call m jaa rhe h .. taking the element ith.
            // i should remove the same element.
            list.push_back(nums[i]);
            fun(nums, i , k - nums[i] , list);
            list.pop_back();
        }
        return;
    }
    vector<vector<int>> combinationSum(vector<int>& nums, int k) {
        vector<int> vec;
        fun(nums ,0, k , vec);
        return result;
    }
};
