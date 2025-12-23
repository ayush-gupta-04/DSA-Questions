// Sort the Events by Start time.

// Thought Process : 
// 1. For any event 'e' , i want to know how many events have ended before this event 'e'.
//    so i can calc the max value of them all.
// METHOD - 1
// 1. I can Do this Logic by LineSweep.
// 2. i have a var 'last' -> max value of the last events ended before any event (this val is prefix max).
// 3. When an event start .. if last != -1 .. calc the max value.
// 4. when an event ends .. update last.


// METHOD - 2;
// 1. For an event 'e' , i can only attend the past events whose end time < start time of 'e'.
// 2. I can manually iterate .. or i can use a min-heap (sorted with endtime).
// 3. lastMax -> The max val among the events that ended before any event.
// 4. pq -> {end , val} : sorted accoring to the end.




bool cmp(vector<int> & a , vector<int>& b){
    if(a[0] != b[0]){
        return a[0] < b[0];
    }
    return a[1] > b[1];
}

int byLineSweep(vector<vector<int>>& eve) {
    int ma = 0;
    vector<vector<int>> nums;
    for(auto it : eve){
        int s = it[0];
        int e = it[1];
        int v = it[2];
        nums.push_back({s , 1 , v});
        nums.push_back({e , -1 , v});
        ma = max(ma , v);
    }
    sort(nums.begin() , nums.end() , cmp);

    int lastVal = -1;
    for(int i = 0; i < nums.size() ; i++){
        if(nums[i][1] == 1){
            // start time.
            if(lastVal != -1){
                ma = max(ma , lastVal + nums[i][2]);
            }
        }else if (nums[i][1] == -1){
            lastVal = max(lastVal , nums[i][2]);
        }
    }
    return ma;
}




int byPQ(vector<vector<int>>& events) {
        int ma = 0;
        priority_queue<pair<int,int> , vector<pair<int,int>> , greater<pair<int,int>>> pq;
        
        int lastMax = 0;
        int Max = 0;

        sort(events.begin() , events.end());

        for(auto event : events){
            int s = event[0];
            int e = event[1];
            int val = event[2];
            while(!pq.empty() && pq.top().first < s){
                lastMax = max(lastMax , pq.top().second);
                pq.pop();
            }
            Max = max(Max , lastMax + val);
            Max = max(Max , val);
            pq.push({e,val});
        }
        return Max;
    }


class Solution {
public:
    int maxTwoEvents(vector<vector<int>>& events) {
        // return byPQ(events);
        return byLineSweep(events);
    }
};
