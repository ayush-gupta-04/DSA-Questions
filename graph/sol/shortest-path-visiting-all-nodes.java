// Normal BFS : 
// - we never visit a node twice.
// - we have a visited array ... visited[node]
// - only thing that metters is where you are ... 
// - Your state is just (currentNode).

// State-Space BFS
// - What matters is (where you are) + (what you have accomplished so far).
// - We cannot visit a node twice with the same accomplishment.
// - Your state becomes (currentNode, stateOfVisitedNodes or stateOfAccomplishment).

// Classic Example to understand this State-Space : 
// - If you walk into Room 5 and you have 0 keys, that is one state.
// - If you walk into Room 5 later and you have the Red and Blue keys, that is a completely different state!
// Even though you are physically in the exact same room, your progress is different
// The algorithm allows you to re-enter Room 5 as long as you are carrying a different combination of keys than the last time you were there. 🗝️


// How to recognise it : 
// - Element Deciding your extra state will be very small .. here N ~ 12
//    - number of nodes.
//    - the thing you are collecting while travelling.
// - You need to find a shortest path that involves collecting, visiting, or activating multiple things.


// OBSERVATION for this Question : 
// - n ~ 12 ... very small n
// - i can visit a node multiple times.
// - The state is not simply the nodes. .. since we can visit multiple times.
// - The state for a node is .... which combination of nodes i have visited so far.



// Thought Process : 
// Since we can start from anywhere .. we will puch every node to the queue at the start.
// we will push [node , mask , dist] in the queue.
//   - node -> to know which node i am at.
//   - mask -> to know the current mask for this node.
//   - dist -> to know the dist.
// we can have multiple nodes with same node val but different mask and dist.
// understanding the Target : 
//  - target is not to reach a node.
//  - target is to visit all nodes.
//  - that the mask becomes 1111...111
// The first time my mask becomes 1111...111  , I know that this is the shortest path.


class Solution {
    public int shortestPathLength(int[][] graph) {
        int n = graph.length;
        if(n == 1) return 0;

        boolean[][] vis = new boolean[n][1 << n];
        Deque<int[]> q = new ArrayDeque<>();

        for(int i = 0;i < n;i++){
            int mask = 1 << i;
            q.offerLast(new int[]{i , mask , 0});
            vis[i][mask] = true;
        }

        int targetMask = (1 << n) - 1;   // 1111....11111

        while(!q.isEmpty()){
            int node = q.peekFirst()[0];
            int mask = q.peekFirst()[1];
            int dist = q.peekFirst()[2];
            q.pollFirst();

            if(mask == targetMask) return dist;   // all nodes visited.

            for(int neigh : graph[node]){
                int newMask = mask | (1 << neigh);

                if(vis[neigh][newMask]) continue;
                q.offerLast(new int[]{neigh , newMask , dist+1});
                vis[neigh][newMask] = true;
            }

        }

        return -1;
    }
}
