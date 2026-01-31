class Solution {
public:
    void sortColors(vector<int>& nums) {
        int n = nums.size();
        int s = -1;
        int m = 0;
        int e = n;
        while(m < e){
            if(nums[m] == 0){
                s++;
                swap(nums[s],nums[m]);
                m++;
            }else if(nums[m] == 2){
                e--;
                swap(nums[e] , nums[m]);  // do not move m yet...because after swapping at m there could be 0 , 1.
            }else{
                m++;
            }
        }

    }
};
