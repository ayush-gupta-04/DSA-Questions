// ---------------------- Approach 1 : 2 pass binary search ------------------

// time : 2*LogN
// space : 1
// - find the pivot 
// - find target in watever half it is present.

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





// --------------------------- Approach 2 : 1 pass binary search solution -------------------
// time : logN
// space : 1

class Solution {
    public int search(int[] nums, int target) {
        int s = 0;
        int e = nums.length - 1;
        
        while (s <= e) {
            int m = s + (e - s) / 2;
            
            // Jackpot! We found the target.
            if (nums[m] == target) {
                return m;
            }
            
            // STEP 1: Check if the LEFT half is sorted
            if (nums[s] <= nums[m]) {
                // STEP 2: Is the target hiding inside this perfectly sorted left half?
                if (target >= nums[s] && target < nums[m]) {
                    e = m - 1; // Yes! Discard the right half.
                } else {
                    s = m + 1; // No! It must be in the right half.
                }
            } 
            // STEP 1 (Alternate): If the left half isn't sorted, the RIGHT half MUST be sorted!
            else {
                // STEP 2: Is the target hiding inside this perfectly sorted right half?
                if (target > nums[m] && target <= nums[e]) {
                    s = m + 1; // Yes! Discard the left half.
                } else {
                    e = m - 1; // No! It must be in the left half.
                }
            }
        }
        
        // Target was not found
        return -1;
    }
}
