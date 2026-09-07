 
class Solution {
    public int findMinArrowShots(int[][] points) {
        int n = points.length;
        int min = -1;
        int max = -1;
        int cnt = 0;
        Arrays.sort(points , (x , y) -> Integer.compare(x[0],y[0]));
        for(int i = 0; i < n ; i++){
            if(min == -1 && max == -1){
                min = points[i][0];
                max = points[i][1];
                continue;
            }
            if(points[i][0] > max){
                cnt++;
                min = points[i][0];
                max = points[i][1];
            }else{
                min = Math.max(min , points[i][0]);
                max = Math.min(max , points[i][1]);
            }

        }
        return cnt + 1;
    }
}
