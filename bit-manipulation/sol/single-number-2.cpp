// ---------------------------------- APPROACH-1 -------------------------------------------
// TC - O(31.N)
// SC - O(1)
class Solution {
public:
    int singleNumber(vector<int>& nums) {
        int ans = 0;
        for(int i = 0;i <= 31 ; i++){
            int cnt = 0;
            for(auto num : nums){
                if(((1 << i)&num) != 0) cnt++;   // it shouldn't be [ (1 << i)&num > 0 ] ... because num could be -ve.
            }
            if(cnt%3 == 1) ans = ans | (1 << i);
        }
        return ans;
    }
};

// ----------------------------------- APPROACH-2-----------------------------------------------
// TC - O(N)
// SC - O(1)

class Solution {
public:
    int singleNumber(vector<int>& nums) {
        sort(nums.begin() , nums.end());
        for(int i = 1;i < nums.size() ;i+=3){
            if(nums[i] != nums[i-1]){
                return nums[i-1];
            }
        }
        return nums[nums.size()-1];
    }
};
