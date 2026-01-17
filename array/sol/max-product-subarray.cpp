class Solution {
public:
    int maxProduct(vector<int>& nums) {
        int ma = INT_MIN;
        int suff = 1;
        int pre = 1;
        for(int i = 0 ; i < nums.size(); i++){
            suff = suff * nums[nums.size()-1-i];
            pre = pre * nums[i];
            ma = max(ma , max(pre , suff));                                                                 
            if(suff == 0) suff = 1;
            if(pre == 0) pre = 1;
        }

        return ma;
    }
};
