class Solution {
public:
    bool fun(vector<vector<int>>& adj , int node , int c ,vector<int>& colors){
        colors[node] = c;
        for(int v : adj[node]){
            if(colors[v] == -1){
                if(!fun(adj , v , (c^1) , colors)) return false;
            }else{
                if(colors[v] == c) return false;
            }
        }
        return true;
    }
    bool isBipartite(vector<vector<int>>& adj) {
        int N = adj.size();
        vector<int> colors(N,-1);
        for(int i = 0;i < N; i++){
            if(colors[i] == -1){
                if(!fun(adj , i , 0,colors)) return false; ;
            }
        }
        return true;
    }
};
