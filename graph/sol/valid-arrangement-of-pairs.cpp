// It looks like we can visit a node multiple times but we are only using an edge once.
// It is Euler circuit problem .. Hierholzer's Algorithm
// Algo : 
// -> Make an Adj List , Which will be a map of { int -> queue<int> } .... 
//          queue because we will have to remove the edge once used.
// -> A directed graph has an Eulerian path iff:
//    1. Exactly one node has ( outDegree = inDegree + 1 ) ...... start node.
//    2. Exactly one node has ( intDegree = outDegree + 1 ) ...... end node.
//    3. All other nodes have equal in and out degrees
// -> If no such Start or End node exist ... then path is a cycle , We can choose any start node or end node.
// -> Apply DFS from the Start node.
// -> For a node 'u' .. visit it's neigh and pop them ...... because if i have choosen to visit an egde (u -> v)
//          then i will remove it .. because i can only visit an edge once.
//          but since i can visit a node multiple times .. i cannot have a visited array.
// -> For a recursive call of node 'u' .. before coming out of the call .. just push it to the vector<int> result.
//          - We only record u after all its edges are exhausted
//          - We Proceed PostOrder .. 
//          - Why this works (deep intuition) ??
//          - When DFS reaches a node u:
//            - If 'u' still has outgoing edges → we are not done with 'u'.
//            - If 'u' has NO outgoing edges → 'u' must appear at the end of the Eulerian path.

// -> We need to reverse the path to get the original path.





class Solution {
public:
    vector<int> result;
    void dfs(int node , unordered_map<int,queue<int>>& adj){
        queue<int>& neigh = adj[node];
        // pop the neigh and visit the neigh.
        while(!neigh.empty()){
            int v = neigh.front();
            neigh.pop();
            dfs(v , adj);
        }
        // postOrder
        result.push_back(node);
    }
    vector<vector<int>> validArrangement(vector<vector<int>>& pairs) {
        unordered_map<int,int> indegree , outdegree;
        unordered_map<int,queue<int>> adj;

        // Just Deciding the start node.
        int start = -1;
        for(auto &it : pairs){
            int u = it[0];
            int v = it[1];
            adj[u].push(v);
            outdegree[u]++;
            indegree[v]++;
            indegree[u] = indegree[u] + 0;     // to have every node in indegree and outdegree.
            outdegree[v] = outdegree[v] + 0;
        }

        for(auto it : indegree){
            int node = it.first;
            if(outdegree[node] == indegree[node] + 1){
                start = node;
                break;
            }
        }

        if(start == -1) {
            start = pairs[0][0];
        }

        // ------------------------------------------------

        dfs(start , adj);
        vector<vector<int>> ans;
        reverse(result.begin() , result.end());
        for(int i = 1;i < result.size() ; i++){
            ans.push_back({result[i-1] , result[i]});
        }
        return ans;
    }
};
