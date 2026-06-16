// time : N*N
// space : 1

class Solution {
    boolean valid(int[][] image,int r, int c){
        return (r >= 0 && r < image.length && c >= 0 && c < image[0].length);
    }
    int[] dr = {0,0,1,-1};
    int[] dc = {1,-1,0,0};
    void dfs(int[][] image, int r, int c,int color , int startColor){

        image[r][c] = color;

        for(int i = 0 ; i < 4 ; i++){
            int row = r + dr[i];
            int col = c + dc[i];
            if(valid(image,row,col) && image[row][col] == startColor){
                dfs(image,row,col,color,startColor);
            }
        }
    }
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int startColor = image[sr][sc];
        if(startColor == color){
            return image;
        }
        dfs(image,sr,sc,color,startColor);
        return image;
    }
}
