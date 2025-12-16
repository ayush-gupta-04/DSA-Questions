// first DFS : 
// -> For every node , store the information of the time at which alice and bob visit that node.
// -> For every node i will have a clear idea that whether i have to pick all the amt or half or none.
// -> 

// second DFS :
// Traverse and accumulate the ans.


class Solution {
private:
public:

    int calcDist(vector<pair<int,int>>& dist , int node ,const vector<vector<int>>& adj , int time , int bob, int parent){
        // set alice dist.
        dist[node].first = time;
        for(auto &it : adj[node]){
            if(it != parent){
                int ans = calcDist(dist , it , adj, time + 1,bob , node);
                if(ans != -1){
                    dist[node].second = ans + 1;
                }
            }
        }

        if(node == bob){
            dist[node].second = 0;
        }
        return dist[node].second;
    }

    int calcAmt(int node , vector<pair<int,int>>& dist,const vector<vector<int>>& adj, vector<int>& amt ,int parent){
        int alice = dist[node].first;
        int bob = dist[node].second;

        int ma = INT_MIN;
        int level = 0;


        if(bob == -1){
            level = amt[node];
        }else{
            if(alice == bob){
                level = amt[node]/2;
            }else if(bob < alice){
                // bob has visited before.
                level = 0;
            }else{
                level = amt[node];
            }
        }

        for(auto &it : adj[node]){
            if(it != parent){
                ma = max(ma , calcAmt(it , dist , adj , amt , node));
            }
        }
        return level + (ma == INT_MIN ? 0 : ma);
    }
    int mostProfitablePath(vector<vector<int>>& edges, int bob, vector<int>& amt) {
        int n = amt.size();
        vector<vector<int>> adj;
        for(int i = 0;i < n; i++){
            adj.push_back({});
        }

        for(auto &it : edges){
            adj[it[1]].push_back(it[0]);
            adj[it[0]].push_back(it[1]);
        }

        vector<pair<int,int>> dist(n,{-1,-1});
        
        dist[0].first = 0;
        dist[bob].second = 0;
        calcDist(dist , 0,adj,0,bob , -1);

        return calcAmt(0,dist,adj,amt ,-1);
    }
};
