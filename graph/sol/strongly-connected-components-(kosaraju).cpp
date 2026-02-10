#include<bits/stdc++.h>
using namespace std;

void dfs1(int node, stack<int>& st,vector<int>& vis , vector<vector<int>>& adj){
    vis[node] = 1;

    for(auto& neigh : adj[node]){
        if(!vis[neigh]){
            dfs1(neigh,st,vis,adj);
        }
    }

    st.push(node);
    return;
}
void dfs2(int node,vector<int>& vis , vector<vector<int>>& adj){
    vis[node] = 1;

    for(auto& neigh : adj[node]){
        if(!vis[neigh]){
            dfs2(neigh,vis,adj);
        }
    }
    return;
}
int kosaraju(vector<vector<int>>& adj , int N){
    stack<int> st;
    vector<int> vis(N,0);

    for(int i = 0;i < N ; i++){
        if(!vis[i]) dfs1(i,st,vis,adj);
    }

    // reverse the edges.
    // reset the vis.
    vector<vector<int>> revAdj(N);
    fill(vis.begin(),vis.end(),0);

    for(int u = 0; u < N ; u++){
        for(auto& v : adj[u]){
            revAdj[v].push_back(u);
        }
    }


    int cnt = 0;
    while(!st.empty()){
        int node = st.top();
        st.pop();
        cout << "node : " << node << endl;

        if(!vis[node]){
            dfs2(node,vis,revAdj);
            
            cnt++;
        }
    }
    return cnt;
}

int main() {
    int N = 8;
    vector<vector<int>> adj = {
        {1},
        {2},
        {0,3},
        {4},
        {7},
        {4,6},
        {4,7},
        {}
    };

    int ans = kosaraju(adj,N);
    cout << ans;
}
