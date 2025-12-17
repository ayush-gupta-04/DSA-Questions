// in DFS.
// make(in case we don't have) or reuse a newNode(dup) from the node(org).
// do dfs traversal in node->neighbors .. and also make new nodes from them .. and mapp them.
// after the traversal .. while returning.
//  -> we know the neigh of node(org).
//  -> we know that the neigh of newNode will be same.
//  -> i am also sure that nodes[] will have all the new nodes in it.
// make a new neighborNodes[] and attach to newNode.
// return newNode

class Solution {
public:
    static Node* dfs(Node* node, unordered_set<int>& vis , unordered_map<int,Node*>& nodes){
        vis.insert(node->val);
        Node* newNode = nullptr;
        if(nodes.find(node->val) == nodes.end()){
            newNode = new Node(node->val);
            nodes[node->val] = newNode;
        }else{
            newNode = nodes[node->val];
        }


        for(auto it : node->neighbors){
            if(nodes.find(it->val) == nodes.end()){
                nodes[it->val] = new Node(it->val);
            }
            if(vis.count(it->val) == 0){
                dfs(it , vis , nodes);
            }
        }


        vector<Node*> neighNodes;
        for(auto it : node->neighbors){
            neighNodes.push_back(nodes[it->val]);
        }
        newNode->neighbors = neighNodes;
        return newNode;
    }
    Node* cloneGraph(Node* node) {
        if(node == nullptr) return nullptr;
        if(node->neighbors.size()==0) 
        {
            Node* clone= new Node(node->val);
            return clone; 
        }
        unordered_set<int> vis;
        unordered_map<int,Node*> nodes;
        return dfs(node , vis,nodes);
    }
};



// --------------------------------------------------------------------------------------------------------------------



// in DFS
// do dfs and store the graph as a adj matrix in the form of map (int , vector<int>).
// create nodes[] which stores copy Nodes.
// i have the adj info with me.
// -> for every node pick the specific nodes from nodes[].
// -> collect them in a vector<Node*>.
// -> attach them to the node.
// finally return nodes[1];

class Solution {
public:
    static void fillAdj(Node* node , unordered_map<int, vector<int>>& adj , unordered_set<int>& vis){
        vis.insert(node->val);
        vector<Node*> neigh = node->neighbors;
        for(auto it : neigh){
            if(vis.count(it->val) == 0){
                fillAdj(it , adj, vis);
            }
        }

        vector<int> neighVal;
        for(auto it : neigh){
            neighVal.push_back(it->val);
        }
        adj[node->val] = neighVal;
        return;
    }
    Node* cloneGraph(Node* node) {
        if(node == nullptr) return nullptr;

        unordered_set<int> vis;
        unordered_map<int, vector<int>> adj;
        fillAdj(node, adj , vis);
        

        // creating new nodes.
        vector<Node*> nodes;
        for(int i = 0; i <= adj.size() ; i++){
            Node* n = new Node(i);
            nodes.push_back(n);
        }




        // picking the neigh Nodes from nodes[] for every node.
        // by looking in the adj map.
        for(int i = 1; i < nodes.size() ;i++){
            Node* n = nodes[i];
            int val = n->val;
            vector<int> neighVal = adj[val];
            vector<Node*> neighNodes;
            for(int it : neighVal){
                neighNodes.push_back(nodes[it]);
            }
            n->neighbors = neighNodes;
        }



        return nodes[1];
    }
};
