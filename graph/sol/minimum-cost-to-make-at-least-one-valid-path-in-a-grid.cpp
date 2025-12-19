class Compare{
public : 

    bool operator()(pair<int , pair<int,int>> a , pair<int , pair<int,int>> b){
        return a.first > b.first;
    }
};

class Solution {
public:
    static bool isValid(int r ,int c, int n, int m){
        return (r >= 0 && r < n && c >= 0 && c < m);
    }
    int minCost(vector<vector<int>>& grid) {
        int n = grid.size();
        int m = grid[0].size();
        int INF = (int)1e8;
        vector<int> dr = {0,0,0,1,-1};
        vector<int> dc = {0,1,-1,0,0};


        vector<vector<int>> minCost(n , vector<int>(m , INF));
        priority_queue<pair<int , pair<int,int>> , vector<pair<int , pair<int,int>>> , Compare> pq;


        minCost[0][0] = 0;
        pq.push({0 ,{0,0}});

        while(!pq.empty()){
            int cost = pq.top().first;
            int r = pq.top().second.first;
            int c = pq.top().second.second;
            pq.pop();


            for(int i = 1; i <= 4 ;i++){
                int nr = r + dr[i];
                int nc = c + dc[i];
                if(isValid(nr , nc , n,m)){
                    int newCost = cost + (i == grid[r][c] ? 0 : 1);
                    if(newCost < minCost[nr][nc]){
                        minCost[nr][nc] = newCost;
                        pq.push({minCost[nr][nc] , {nr,nc}});
                    }
                }
            }
        }

        return minCost[n-1][m-1];
    }
};
