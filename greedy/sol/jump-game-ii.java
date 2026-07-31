// -------------------- Better-approach -------------------
// apply bfs.
// find the first level which reaches n-1 index.


class Solution {
    public int jump(int[] nums) {
        int n = nums.length;
        boolean[] vis = new boolean[n];
        Deque<Integer> q = new ArrayDeque<>();

        q.offerLast(0);
        vis[0] = true;
        int level = 0;

        while(!q.isEmpty()){
            int size = q.size();
            for(int k = 0;k < size ;k++){
                int node = q.pollFirst();

                if(node == n-1) return level;

                int lb = node; 
                int ub = Math.min(node + nums[node] , n-1);
                for(int i = lb ; i <= ub; i++){
                    if(vis[i]) continue;
                    vis[i] = true;
                    q.offerLast(i);
                }
            }
            level++;
        }
        return level;
    }
}





// ------------------ Optimal ---------------------

class Solution {
    public int jump(int[] nums) {
        int n = nums.length;
        int cnt = 0;

        int end = 0;   // currently where i can go.
        int max = 0;   // the max idx where i can jump.

        for(int i = 0;i <= n-2; i++){
            max = Math.max(max, i + nums[i]);

            if(i == end){    // if i reached the end ... i will go to the next max index.
                cnt++;
                end = max;
            }
        }
        return cnt;
    }
}
