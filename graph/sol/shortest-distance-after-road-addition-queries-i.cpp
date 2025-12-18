// make a Adj list .. a straight foward thing.
// assign the initial distance .. also a straight forward thing.

// when i have a query .. [u , v].
// OBV : -> Dist of U will not change.
//       -> Only dist of V will change.
//       -> if new Dist of V < old dist of V .. i know i need to add this V with new Dist to the queue.
//       -> if yes .. then add this {new Dist of V (Old dis of U + 1) , V } to the queue.


// It is a BFS with unit wight  but i can visit a node multiple times , if i have a less distance then old one.

class Solution {
public:
    vector<int> shortestDistanceAfterQueries(int n, vector<vector<int>>& queries) {
        vector<vector<int>> adj(n , vector<int>{});
        vector<int> dist(n , 0);
        queue<pair<int,int>> Q;
        vector<int> ans;


        // Make Edges and Assign the initial Distance;
        for(int i = 0;i < n-1; i++){
            // make an edge bet i -> i+1.
            adj[i].push_back(i + 1);
            dist[i] = i;
        }
        dist[n-1] = n-1;



        for(auto q : queries){
            int u = q[0];
            int v = q[1];
            adj[u].push_back(v);

            // Checking is the new Edge reduced the dist of V or not.
            if(dist[u] + 1 < dist[v]){
                dist[v] = dist[u] + 1;
                Q.push({dist[v], v});
            }

            while(!Q.empty()){
                int steps = Q.front().first;
                int node = Q.front().second;
                Q.pop();

                for(int v : adj[node]){
                    if(steps + 1 < dist[v]){
                        dist[v] = steps + 1;
                        Q.push({dist[v] , v});
                    }
                }
            }

            ans.push_back(dist[n-1]);
        }
        return ans;
    }
};

