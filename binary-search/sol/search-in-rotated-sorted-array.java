// time : 2*LogN
// space : 1

class Solution {
    static int findPivot(int[] A){
        int s = 0;
        int e = A.length - 1;
        while(s <= e){
            int m = s + (e - s)/2;
            if(m + 1 < A.length && A[m] > A[m + 1]){
               return m;
            }else if(A[m] < A[s]){
                e = m - 1;
            }else{
                s = m + 1;
            }
        }
        return -1;
    }
    public int binarySearch(int[] nums, int target,int s, int e){
        while(s <= e){
            int m = s + (e-s)/2;
            if(target > nums[m]){
                s = m + 1;
            }else if(target < nums[m]){
                e = m - 1;
            }else{
                return m;
            }
        }
        return -1;
    }
    public int search(int[] nums, int target) {
        int p = findPivot(nums);
        if(p == -1){
            //pivot not found 
            //full array is sorted
            //normal binary search
            return binarySearch(nums,target,0,nums.length-1);
        }
        if(target >= nums[0]){
            //taregt in 1st part
            //binary search in 1st part
            return binarySearch(nums,target,0,p);
        }
        //target in 2nd part
        return binarySearch(nums,target,p+1,nums.length-1); 
    }
}
