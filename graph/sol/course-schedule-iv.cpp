// What i Cannot do is that apply topo sort and find if v is after u....this is totally wrong solution.
// Main AIM : To find if a path exist from u to v.
// Brute Force :
// -> Build Adj list.
// -> for every query ... find if a path exist between u and v or not.

// Optimal :
// 1st approach :
// Main AIM : Let's make something like this ...[ node1 -> {every reachable nodes from this node1},
//                                                node2 -> {every reachable nodes from this node1}...]
// -> I can easily make this relationship using DFS Algorithm.
// -> Now for every node 'u' i can find if i have 'v' in every reachable nodes of 'u'.




// 2nd Approach : 
// Main AIM : Let's make something like this ...[ node1 -> {every nodes That lead to this node1},
//                                                node2 -> {every nodes That lead to this node1}...]
// -> Now for every node 'v' i can find if i have 'u' in every nodes That lead to this 'v'.
// -> This quesition reduces to All Ancestors of a node in DAG.
// -> and then finding pair [u , v].


class Solution {
public:
    vector<bool> checkIfPrerequisite(int n,
                                     vector<vector<int>>& edges,
                                     vector<vector<int>>& Q) {

// -----------------------------------------------------------------------
// first let's find all ancestors of a node.
        vector<unordered_set<int>> allAnces(n , unordered_set<int>{});
        vector<vector<int>> adj(n , vector<int>{});
        vector<int> indegree(n , 0);

        for(auto it : edges){
            int u = it[0];
            int v = it[1];
            adj[u].push_back(v);
            indegree[v]++;
        }

        queue<int> q;
        for(int i = 0;i < n; i++){
            if(indegree[i] == 0){
                q.push(i);
            }
        }

        while(!q.empty()){
            int node = q.front();
            q.pop();

            for(auto it : adj[node]){
                indegree[it]--;
                if(indegree[it] == 0){
                    q.push(it);
                }

                // i know that node -> it.
                // all ancestor of 'it'.
                for(auto x : allAnces[node]){
                    allAnces[it].insert(x);
                }
                allAnces[it].insert(node);
            }
        }
// -------------------------------------------------------------

        // in the above i have just found out .. All ancestors of a node in DAG.
        vector<bool> ans;

        for(auto it : Q){
            int u = it[0];
            int v = it[1];
            // for v search for u.
            int found = false;
            for(auto a : allAnces[v]){
                if(a == u){
                    found = true;
                    break;
                }
            }
            ans.push_back(found);
        }
        return ans;
    }
};
