// At every cell, i have 4 opts to move.
// weights are 0 or 1 for every node .. depends on the dir.
// We could use PriorityQueue (dijkistra algo).
// But since the i can only have 0,1 as weights.
// if w == 0 .. add to front of the deque.
// if w == 1 .. add to back of the deque.
// It will make sure that we are processing cost in ascending manner.

class Solution {
    public int minCost(int[][] grid) {
        int INF = 1_000_000_00;
        int n = grid.length;
        int m = grid[0].length;
        
        // Match the 1-indexed directions: 1=Right, 2=Left, 3=Down, 4=Up
        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        
        int[][] costs = new int[n][m];
        for (int[] row : costs) Arrays.fill(row, INF);
        
        Deque<int[]> d = new ArrayDeque<>();
        d.offerLast(new int[]{0, 0, 0});  // cost, r, c
        costs[0][0] = 0;
        
        while (!d.isEmpty()) {
            int[] curr = d.pollFirst();
            int cost = curr[0];
            int r = curr[1];
            int c = curr[2];
            
            for (int i = 0; i < 4; i++) {
                int nr = r + dirs[i][0];
                int nc = c + dirs[i][1];

                if(!valid(nr, nc, n, m)) continue;

                int w = ((grid[r][c] == i + 1) ? 0 : 1);

                if(cost + w < costs[nr][nc]){
                    costs[nr][nc] = cost + w;

                    if(w==0) d.offerFirst(new int[]{costs[nr][nc], nr, nc});
                    else d.offerLast(new int[]{costs[nr][nc], nr,nc});
                }
                
            }
        }
        
        return costs[n - 1][m - 1];
    }
    boolean valid(int r, int c, int n ,int m){
        return (r >= 0 && r < n && c >= 0 && c < m);
    }
}
