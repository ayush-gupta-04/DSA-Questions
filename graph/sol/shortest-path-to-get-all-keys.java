class Solution {
    int[] dr = {0,0,-1,1};
    int[] dc = {1,-1,0,0};

    boolean valid(int r, int c, int n, int m){
        return (r >= 0 && r < n && c >=0 && c < m);
    }
    public int shortestPathAllKeys(String[] grid) {
        int n = grid.length;
        int m = grid[0].length();
        int sr = -1;
        int sc = -1;
        int target = 0;

        // findin the src and the target.
        for(int i = 0 ;i <  n;i++){
            for(int j = 0; j < m;j++){
                char ch = grid[i].charAt(j) ;
                if(ch == '@'){
                    sr = i;
                    sc = j;
                }

                if(ch >= 'a' && ch <= 'f'){
                    target = (target | (1 << (ch-'a')));
                }
            }
        }

        Deque<int[]> q = new ArrayDeque<>();
        boolean[][][] vis = new boolean[n][m][(1 << 6)];   // mask of keys.

        q.offerLast(new int[]{sr , sc , 0 ,0});   // row , col , mask , dist.
        vis[sr][sc][0] = true;

        while(!q.isEmpty()){
            int r = q.peekFirst()[0];
            int c = q.peekFirst()[1];
            int mask = q.peekFirst()[2];
            int dist = q.peekFirst()[3];
            q.pollFirst();


            if(mask == target) return dist;

            for(int i =0 ;i < 4 ;i++){
                int nr = dr[i] + r;
                int nc = dc[i] + c;

                if(!valid(nr,nc,n,m)) continue;

                char ch = grid[nr].charAt(nc);
                if(ch == '#') continue;   // wall
                if(ch >= 'A' && ch <= 'F' && (((mask)>>(ch-'A'))&1) == 0) continue; // locked .. don't have key.

                int newMask = mask;
                if(ch >= 'a' && ch <= 'f'){       // its a key.
                    newMask = (newMask | (1 << (ch-'a')));
                }

                if(vis[nr][nc][newMask]) continue;   // if i have visited with the same key configuration.
                q.offerLast(new int[]{nr,nc,newMask,dist + 1});
                vis[nr][nc][newMask] = true;
            }
        }
        return -1;
    }
}
