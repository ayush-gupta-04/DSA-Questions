class Solution {
    public boolean canFinish(int N, int[][] prerequisites) {
        List<List<Integer>> adj = new ArrayList<>();
        int[] indegree = new int[N];
        for(int i = 0 ;i < N ; i++){
            adj.add(new ArrayList<>());
        }
        
        // make graph.
        // add indegree.
        for(int[] edge : prerequisites){
            int u = edge[1];
            int v = edge[0];
            indegree[v]++;
            adj.get(u).add(v);
        }

        Deque<Integer> Q = new ArrayDeque<>();
        for(int i = 0 ; i < N ; i++){
            if(indegree[i] == 0){
                Q.offerLast(i);
            }
        }

        int cnt = 0;

        while(!Q.isEmpty()){
            int node = Q.pollFirst();
            cnt++;

            for(int neigh : adj.get(node)){
                indegree[neigh]--;
                if(indegree[neigh] == 0){
                    Q.offerLast(neigh);
                }
            }
        }

        return cnt == N;
    }
}
