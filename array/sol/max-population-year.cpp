// we are storing 2003 -> +x (x = number of times 2003 as birth).
//                2004 -> -x (x = number of times 2004 as death).

// (y1 -> +2) , (y2 -> +1) , (y3 -> +4) , (y3 -> -1) , (y4 -> -2).
//                                  |
//                                  i
// we know that till 'i' .. we have 2 + 1 + 4 .. people alive.
// and while traversing , we are storing the cnt.
// at max cnt .. we are storing the year.


class Solution {
public:
    int maximumPopulation(vector<vector<int>>& logs) {
        map<int, int> line;
        for(auto& p : logs){
            ++line[p[0]];
            --line[p[1]];
        }
        int max_p = 0;
        int ans_year;
        int cnt = 0;
        for(auto& i : line){
            if(i.second >= 1){
                cnt += i.second;
                if(cnt > max_p){
                    max_p = cnt;
                    ans_year = i.first;
                }
            }else if(i.second <= -1){
                cnt += i.second;
            }
        }
        return ans_year;
    }

};
















class Solution {
public:
    int maximumPopulation(vector<vector<int>>& logs) {
        vector<pair<int,int>> e;
        for(auto it : logs){
            e.push_back({it[0],1});
            e.push_back({it[1] , -1});
        }

        sort(e.begin() , e.end());

        int cnt = 0;
        int year = -1;
        int maxi = 0;
        for(int i = 0; i < e.size();i++){
            if(e[i].second == 1){
                cnt++;
                if(cnt > maxi){
                    maxi = cnt;
                    year = e[i].first;
                }
            }else if(e[i].second == -1){
                cnt--;
            }
        }

        return year;
    }
};
