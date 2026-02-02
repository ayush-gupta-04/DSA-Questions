// If we Observer.
// If we remove 1-D .. then it becomes find LIS.
// Here both-D should be increasing.

// Sorting acc to 'w' will do the first job .. we can apply LIS on 'h'.
// But how to make sure that i don't take multiple heights if the 'w' are the same.
// 1 , 2
// 1 , 3
// 1 , 4

// sol : Sort 'w' -> ASC.
//     : Sort 'h' -> DESC. // it will make sure that we take the height only once if the 'w' are the same.


class Solution {
public:
    int maxEnvelopes(vector<vector<int>>& nums) {
        sort(nums.begin(), nums.end() , [](auto& a, auto& b){
            if(a[0] != b[0]){
                return a[0] < b[0];
            }
            return a[1] > b[1];
        });
        vector<int> tails;

        for(auto it : nums){
            int h = it[1];
            if(tails.empty() || h > tails.back()){
                tails.push_back(h);
            }else{
                int lb = lower_bound(tails.begin(), tails.end(),h) - tails.begin();
                tails[lb] = h;
            }
        }
        return tails.size();
    }
};
