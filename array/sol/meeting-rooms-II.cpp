// we just need to find the maximum number of active rooms at a given time.
// we will sort the interval.
// we will sweep though the time .. and calc the maximum active rooms , and calc the max.
class Solution {
public:
    int minMeetingRooms(vector<int>& start, vector<int>& end) {
        int n = start.size();

        vector<pair<int,int>> events;
        for(int i = 0; i < n; i++){
            events.push_back({start[i], +1}); // meeting starts
            events.push_back({end[i], -1});   // meeting ends
        }

        // sort: if time same -> process end (-1) before start (+1)
        // because end at time t frees a room for a start at time t
        sort(events.begin(), events.end(),
            [](auto &a, auto &b){
                if(a.first != b.first) return a.first < b.first;
                return a.second < b.second; // -1 before +1
            }
        );

        int active = 0;
        int maxRooms = 0;

        for(auto &ev : events){
            active += ev.second;
            maxRooms = max(maxRooms, active);
        }

        return maxRooms;
    }
};



// -------------------------------------------------- 2-Pointers -------------------------------------------------------

// what we are concerned of :
// 1. number of active meetings at a given point of time....i.e max meetings intersection.
// 2. As we are sweeping through time .. we don't care about the pair (startTime , endTime).
//    all we care about is that ... (it must be sorted , we should know is it start or end.).
// 3. We can sort StartTime , EndTime separately .... and we will have 2 pointers.

class Solution {
public:
    int minMeetingRooms(vector<int>& start, vector<int>& end) {
        int n = start.size();
        sort(start.begin() , start.end());
        sort(end.begin() , end.end());
        
        int i = 0;
        int j = 0;
        int cnt = 0;
        int ma = 0;
        while(i < n && j < n){
            if(start[i] < end[j]){
                cnt++;
                i++;
            }else if(start[i] >= end[j]){    // if start == end .. i will process end first.
                cnt--;
                j++;
            }
            ma = max(ma , cnt);
        }
        return ma;
    }
};

