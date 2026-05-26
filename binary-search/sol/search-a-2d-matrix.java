//------------------------- Approach 1 : Brute Force --------------------------------
// time : N + logM
// space : 1
// if it exist in the row .. bs in the row.
class Solution {
    boolean bs(int[] nums , int k){
        int s = 0;
        int e = nums.length;
        while(s <= e){
            int m = s + (e - s)/2;
            if(nums[m] == k) return true;
            else if(k > nums[m]) s = m + 1;
            else e = m - 1;
        }
        return false;
    }
    public boolean searchMatrix(int[][] nums, int target) {
        int n = nums.length;
        int m = nums[0].length;

        for(int i= 0;i < n;i++){
            if(target >= nums[i][0] && target <= nums[i][m-1]){
                if(bs(nums[i] , target)) return true;
                return false;
            }
        }
        return false;
    }
}












// -------------------------- Approach : 2 Optimal -------------------
// time : log(N * M)
// space : 1

// treat it like a flatten 1D array.
// for mid .. row = mid/m
//            col = mid%m

class Solution {

    public boolean searchMatrix(int[][] nums, int target) {
        int n = nums.length;
        int m = nums[0].length;

        int s = 0;
        int e = n*m - 1;

        while(s <= e){
            int mid = s + (e-s)/2;
            int row = mid/m;
            int col = mid%m;

            if(nums[row][col] == target) return true;
            else if(target > nums[row][col]) s = mid + 1;
            else e = mid - 1;
        }

        return false;
    }
}
