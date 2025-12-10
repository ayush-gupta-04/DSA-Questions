class Solution {
public:
    vector<vector<int>> result;
    void fun(vector<int>& nums , int i , vector<int>& list){
        if(i == nums.size()){
            result.push_back(list);
            return;
        }
        list.push_back(nums[i]);
        fun(nums , i + 1 , list);
        list.pop_back();
        fun(nums , i + 1 , list);
        return;
    }
    vector<vector<int>> subsets(vector<int>& nums) {
        vector<int> vec;
        fun(nums , 0 , vec);
        return result;
    }
};
