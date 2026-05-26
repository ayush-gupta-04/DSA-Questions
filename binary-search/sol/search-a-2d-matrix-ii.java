// time : N + M
// space : 1

class Solution {
    public boolean searchMatrix(int[][] nums, int target) {
        int n = nums.length;
        int m = nums[0].length;

        int row = n-1;
        int col = 0;

        while(row >= 0 && col < m){
            if(nums[row][col] == target) return true;
            else if(target > nums[row][col]) col++;
            else row--;
        }
        return false;
    }
}
