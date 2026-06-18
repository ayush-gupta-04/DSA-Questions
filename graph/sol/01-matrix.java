// intuition : 
// - It is very un-optimal to go from every 1 to nearest 0;
// - We will add every 0 to the queue then we will move towards 1.

// - It's a simple shortest dist BFS with unit weight.
// - We will visit every node just once .. we don't need vis[] ... we can use dis[] also.
// - The first time we visit a node .. it's with the shortest dist possible.
// - We will store [row,col] only in the queue.
//   - dist of [row,col] can easily be get from dis[] array.

class Solution {
    boolean valid(int r, int c, int n , int m){
        return (r >= 0 && r < n && c >= 0  && c < m);
    }
    public int[][] updateMatrix(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;
        int[] dr = {0,0,-1,1};
        int[] dc = {1,-1,0,0};

        int[][] dis = new int[n][m];
        Deque<int[]> q = new ArrayDeque<>();

        for(int[] a : dis) Arrays.fill(a , -1);
        for(int i =0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(mat[i][j] == 0) {
                    dis[i][j] = 0;
                    q.offerLast(new int[]{i,j});
                }
            }
        }

        while(!q.isEmpty()){
            int r = q.peekFirst()[0];
            int c = q.peekFirst()[1];
            q.pollFirst();

            for(int i = 0;i < 4;i++){
                int nr = r + dr[i];
                int nc = c + dc[i];
                if(valid(nr,nc,n,m) && dis[nr][nc] == -1){
                    dis[nr][nc] = dis[r][c] + 1;
                    q.offerLast(new int[]{nr,nc});
                }
            }
        }

        return dis;
    }
}
