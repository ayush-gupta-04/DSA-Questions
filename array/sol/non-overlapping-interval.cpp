// ----------------------------------------------------- Sort Start Time --------------------------------------------
// Sort according to the StartTime.
// we have prev and curr.
// if prev and curr do not overlapp .... curr = prev ; continue;
// if they overlapp .. then we will think to remove the one with the greater end time.
//                     because interval with greater end time can overlapp with future intervals.


class Solution {
public:
    int eraseOverlapIntervals(vector<vector<int>>& nums) {
        int n = nums.size();
        sort(nums.begin() , nums.end());

        // the prev interval.
        vector<int> prev = nums[0];
        int cnt = 0;
        for(int i = 1; i < n ; i++){
            vector<int> curr = nums[i];
            // if no overlapp of prev and curr.
            if(curr[0] >= prev[1]) {
                prev = curr;
                continue;
            }

            // overlapp case.
            if(curr[1] >= prev[1]){
                cnt++;
                continue;
            }
            prev = curr;
            cnt++;
        }

        return cnt;
    }
};




// -------------------------------------------- Sort End Time ----------------------------------------
  // Sort according to the EndTime.
// Logic is the same .. if no overlapp .. move.
//                      if overlapp then remove the one with the greater endtime.
// we know that the curr will always have the greater endtime wrt the prev.



class Solution {
public:
    int eraseOverlapIntervals(vector<vector<int>>& nums) {
        int n = nums.size();
        sort(nums.begin() , nums.end() , [](vector<int>& a , vector<int>& b){
            if(a[1] != b[1]){
                return a[1] < b[1];
            }
            return a[0] < b[0];
        });

        // the prev interval.
        vector<int> prev = nums[0];
        int cnt = 0;
        for(int i = 1; i < n ; i++){
            vector<int> curr = nums[i];
            // if no overlapp of prev and curr.
            if(curr[0] >= prev[1]) {
                prev = curr;
                continue;
            }
            // if not then i know to skip the curr. because it will have the greater End Time.
            cnt++;
        }

        return cnt;
    }
};
