// time : N + logM
// space : 1
// first we will find the row in which the target could possible exits.
// binary search on that row.

class Solution {
    public boolean searchMatrix(int[][] nums, int target){
        // no of rows
        int m = nums.length;
        // no of col
        int n = nums[0].length;
        int start = 0;
        int end = m-1;
        int col = -1;
        while(start<=end){
            int mid = start + (end-start)/2;
            if(nums[mid][0] <= target && nums[mid][n-1] >= target){
                col = mid;
                break;
            }else if(nums[mid][0] > target){
                end = mid-1;
            }else{
                start = mid + 1;
            }
        }
        if(col == -1){
            return false;
        }else{
            //search in subarray at mid
            int s = 0;
            int e = n-1;
            while(s<=e){
                int mid1 = s + (e-s)/2;
                if(target == nums[col][mid1]){
                    return true;
                }else if(target > nums[col][mid1]){
                    s = mid1 + 1;
                }else{
                    e = mid1-1;
                }
            }
        }
        return false;
    }
}
