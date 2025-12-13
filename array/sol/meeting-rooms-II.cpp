// we just need to find the maximum number of active rooms at a given time.
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
