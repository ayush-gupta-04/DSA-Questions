// time : N * logM
// space : 1


class Solution {
    public int maxIndex(int[][] nums , int col){
        int n = nums.length;
        int max = Integer.MIN_VALUE;
        int idx = -1;
        for(int i = 0;i < n;i++){
            if(nums[i][col] > max){
                max = nums[i][col];
                idx = i;
            }
        }
        return idx; 
    }
    public int[] findPeakGrid(int[][] nums) {
        int n = nums.length;
        int m = nums[0].length;

        int s = 0;
        int e = m-1;

        while(s <= e){
            int mid = s + (e - s)/2;
            int row = maxIndex(nums , mid);

            int val = nums[row][mid];
            int left = (mid-1 >= 0 ?  nums[row][mid-1] : Integer.MIN_VALUE);
            int right = (mid+1 < m ?  nums[row][mid+1] : Integer.MIN_VALUE);

            if(val > left && val > right) return new int[]{row , mid};
            else if(left > val) e = mid - 1;
            else s = mid +1 ;
        }
        return new int[]{-1, -1};
    }
}
