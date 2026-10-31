// store : For every node .. the frequency of every color till that node (if we proceed in topo order).
// dp[node][i] : The frequency of ith color at node (if we moved in topo order).

class Solution {
    public int largestPathValue(String c, int[][] edges) {
        int n = c.length();
        int[] indeg = new int[n];
        List<List<Integer>> adj = new ArrayList<>();

        for(int i = 0;i < n; i++){
            adj.add(new ArrayList<>());
        }

        for(int[] e : edges){
            int u = e[0];
            int v = e[1];
            adj.get(u).add(v);
            indeg[v]++;
        }

        int[][] dp = new int[n][26];

        Deque<Integer> q = new ArrayDeque<>();
        for(int i = 0;i < n;i++){
            if(indeg[i]==0){
                q.offerLast(i);
                dp[i][c.charAt(i)-'a']++;
            }
        }

        int max = 0;
        int cnt = 0;
        while(!q.isEmpty()){
            int u = q.pollFirst();
            cnt++;

            for(int v : adj.get(u)){
                // update the frequency of every neigh.
                for(int i = 0;i < 26; i++){
                    dp[v][i] = Math.max(dp[v][i], dp[u][i]);
                }

                indeg[v]--;
                if(indeg[v]==0){
                    q.offerLast(v);
                    dp[v][c.charAt(v)-'a']++;
                }
            }

            for(int i = 0; i < 26; i++){
                max = Math.max(max, dp[u][i]);
            }
        }

        return cnt==n ? max : -1;
    }
}
