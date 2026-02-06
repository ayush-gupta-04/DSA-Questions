class Solution {
public:
    vector<int> maxSlidingWindow(vector<int>& nums, int k) {
        deque<int> d;
        vector<int> ans;
        for(int i = 0;i < nums.size(); i++){
            // pushing into deque.
            while(!d.empty() && nums[i] >= nums[d.back()]){
                d.pop_back();
            }
            d.push_back(i);


            // checking if the window is valid or not...if not then remove one extra element.
            if(d.back() - d.front() + 1 > k){
                d.pop_front();
            }


            // When the first window is made .. now we can ans the query.
            if(i >=  k-1){
                ans.push_back(nums[d.front()]);
            }
        }
        return ans;
    }
};
