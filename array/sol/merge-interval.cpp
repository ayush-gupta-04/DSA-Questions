// ------------------------------------------- OPTIMAL SOLUTION ----------------------------------------
// TC - O(N).
// SC - O(N).


class Solution {
public:
    vector<vector<int>> merge(vector<vector<int>>& nums) {
        int n = nums.size();
        vector<vector<int>> ans;
        sort(nums.begin() , nums.end());
        ans.push_back(nums[0]);

        for(int i = 1;i < n ; i++){
            // can i merge this nums[i] with ans.back().
            if(nums[i][0] <= ans.back()[1]){
                ans.back()[1] = max(ans.back()[1] , nums[i][1]);
                continue;
            }

            // if i cannot merge then it will form a new interval.
            ans.push_back(nums[i]);
        }

        return ans;
    }
};





// line sweep algorithm.
// we are not using map .. we are using vector<pair<int,int>>.
// cnt -> to track the start and end of the interval.
// last -> to mark the start of interval.
// when cnt == 1 && last == -1 ... we know this is start of interval.
// when cnt == 0 && last != -1 .. we know this is end of interval.

bool cmp(const pair<int,int> a , const pair<int,int> b){
    if(a.first != b.first){
        return a.first < b.first;
    }
    return a.second > b.second;
}

class Solution {
public:
    vector<vector<int>> merge(vector<vector<int>>& intervals) {
        vector<pair<int,int>> events;
        for(auto it : intervals){
            events.push_back({it[0] , 1});
            events.push_back({it[1] , -1});
        }
        sort(events.begin() , events.end() , cmp);

        vector<vector<int>> ans;
        int cnt = 0;
        int last = -1;
        for(int i = 0;i < events.size() ; i++){
            if(events[i].second == 1){
                cnt++;
                if(last == -1) last = i;
            }else if(events[i].second == -1){
                // it could be the end.
                cnt--;
                if(cnt > 0) continue;

                vector<int> vec;
                vec.push_back(events[last].first);
                vec.push_back(events[i].first);
                ans.push_back(vec);
                last = -1;
            }
        }
        return ans;
    }
};
