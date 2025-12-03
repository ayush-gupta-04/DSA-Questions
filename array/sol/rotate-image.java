// take transpose.
// then reverse every row;
class Solution {
    void swap(int[][] nums , int i , int j){
        int temp = nums[i][j];
        nums[i][j] = nums[j][i];
        nums[j][i] = temp;
    }
    public void rotate(int[][] nums) {
        int n = nums.length;
        int m = nums[0].length;
        for(int i = 0 ;i < n ; i++){
            for(int j = i ; j < m ; j++){
                swap(nums , i , j);
            }
        }

        for(int i = 0 ; i < n ; i++){
            int s = 0 ; 
            int e = m - 1;
            while(s < e){
                int temp = nums[i][e];
                nums[i][e] = nums[i][s];
                nums[i][s] = temp;
                s++;
                e--;
            }
        }
    }
}
