// I will move in the x-axis. left -> right....line sweep.
// At some points i will pick passengers.
// At some points i will drop passengers.
// When pick -> reduce the cap by people at that point.
// When drop -> inc the capacity by people at that point.

// At any point if my capacity < 0 .. return false;


class Solution {
public:
    bool carPooling(vector<vector<int>>& trips, int capacity) {
        map<int,int> mp;

        // i am making the map.
        for(auto &it : trips){
            auto from = it[1];
            auto to = it[2];
            auto people = it[0];

            mp[from] = mp[from] - people;
            mp[to] = mp[to] + people;
        }

        // line sweep.
        int cap = capacity;
        for(auto &it : mp){
            cap += it.second;
            if(cap < 0) return false;
        }
        return true;
    }
};
