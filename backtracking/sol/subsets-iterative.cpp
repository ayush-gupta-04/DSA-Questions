class Solution {
public:
    vector<vector<int>> subsets(vector<int>& nums) {
        vector<vector<int>> result;
        int n = nums.size();
        for(int mask = 0 ;mask < (1 << n) ; mask++){
            vector<int> list;
            for(int i = 0 ; i < n ; i++){
                if(((mask >> i) & 1) == 1){
                    list.push_back(nums[i]);
                }
            }
            result.push_back(list);
        }
        return result;
    }
};
