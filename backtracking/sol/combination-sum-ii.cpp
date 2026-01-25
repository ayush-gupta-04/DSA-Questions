class Solution {
public:
    vector<vector<int>> res;
    void fun(vector<int>& nums ,int target , int i,vector<int>& list){
        if(target == 0){
            res.push_back(list);
            return;
        }
        if(i == nums.size()){
            return;
        }
        // take;
        if(target >= nums[i]){
            list.push_back(nums[i]);
            fun(nums, target - nums[i],i + 1 , list);
            list.pop_back();
        }

        // not - take ... then don't any instance of it.

        int j = i;
        j++;
        while(j < nums.size() && nums[j] == nums[j-1]){
            j++;
        }

        fun(nums , target , j, list);
        
    }
    vector<vector<int>> combinationSum2(vector<int>& nums, int target) {
        vector<int> list;
        sort(nums.begin() , nums.end());
        fun(nums, target , 0 , list);
        return res;
    }
};
