// It is just like we have to find a path with min cost.

// ------ B ---------- C
//   e      4           10 
// if i am at B .. i should know how much effort it took to reach here from the start.
// i will store {total effort , {row,col}} in the PQ.
// - Effort to reach C = max(e , |4 - 10|)


class Solution {
    boolean valid(int r , int c , int n , int m){
        return (r >= 0 && r < n && c >= 0 && c < m);
    }
    public int minimumEffortPath(int[][] heights) {
        int n = heights.length;
        int m = heights[0].length;
        int[][] eff = new int[n][m];
        PriorityQueue<int[]> Q = new PriorityQueue<int[]>((x , y) -> x[2] - y[2]);


        for(int[] a : eff){
            Arrays.fill(a , (int)1e9);
        }
        Q.offer(new int[]{0,0,0});
        eff[0][0] = 0;

        while(!Q.isEmpty()){
            int[] p = Q.poll();
            int r = p[0];
            int c = p[1];
            int e = p[2];

            int[] dr = {0,0,1,-1};
            int[] dc = {-1,1,0,0};
            for(int i = 0 ; i < 4 ; i++){
                int nr = r + dr[i];
                int nc = c + dc[i];             
                if(valid(nr ,nc , n ,m)){
                    int effort = Math.max(e , Math.abs(heights[r][c] - heights[nr][nc]));
                    if(effort < eff[nr][nc]){
                        eff[nr][nc] = effort;
                        Q.offer(new int[]{nr , nc , eff[nr][nc]});
                    }
                }
            }
        }

        for(int[] a : eff){
            System.out.println(Arrays.toString(a));
        }

        return eff[n - 1][m - 1];
    }
}
