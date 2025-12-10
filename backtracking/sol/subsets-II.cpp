class Solution {
public:
    vector<vector<int>> result;
    void fun(vector<int>& nums , int i, vector<int> list){
        if(i == nums.size()){
            result.push_back(list);
            return;
        }
        // let nums[i] = a;
        // take 'a'.
        list.push_back(nums[i]);
        fun(nums , i + 1 , list);
        list.pop_back();
        // not take 'a' . . . skip duplicates.
        i++;
        while(i < nums.size() && nums[i-1] == nums[i]){
            i++;
        }
        fun(nums , i , list);
        return;
    }
    vector<vector<int>> subsetsWithDup(vector<int>& nums) {
        sort(nums.begin() , nums.end());
        vector<int> vec;
        fun(nums , 0 , vec);
        return result;
    }
};
