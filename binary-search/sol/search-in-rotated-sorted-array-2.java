// --------------------- Approach : Most Optimal ---------------
// time : LogN
// 1 pass solution.
// 

class Solution {
    public boolean search(int[] nums, int target) {
        int s = 0;
        int e = nums.length - 1;
        
        while (s <= e) {
            int m = s + (e - s) / 2;
            
            // Jackpot! We found the target.
            if (nums[m] == target) {
                return true; 
            }
            
            // THE NEW MAGIC LINES
            // If we can't tell which half is sorted because the edges equal the middle,
            // we just shrink the search window safely.
            if (nums[s] == nums[m] && nums[m] == nums[e]) {
                s++;
                e--;
                continue; // Skip the rest of the loop and recalculate mid
            }
            
            // STEP 1: Check if the LEFT half is sorted
            if (nums[s] <= nums[m]) {
                if (target >= nums[s] && target < nums[m]) {
                    e = m - 1; 
                } else {
                    s = m + 1; 
                }
            } 
            // STEP 1 (Alternate): RIGHT half MUST be sorted!
            else {
                if (target > nums[m] && target <= nums[e]) {
                    s = m + 1; 
                } else {
                    e = m - 1; 
                }
            }
        }
        
        return false;
    }
}
