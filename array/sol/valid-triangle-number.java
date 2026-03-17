import java.util.*;

class Solution {
    public int triangleNumber(int[] nums) {
        // Step 1: Sort the array
        Arrays.sort(nums);
        
        int count = 0;
        int n = nums.length;
        
        // Step 2: Fix the largest side 'c' at index k
        // Iterate backwards from the end
        for (int k = n - 1; k >= 2; k--) {
            int left = 0;
            int right = k - 1;
            
            while (left < right) {
                // Step 3: Check Triangle Inequality (a + b > c)
                if (nums[left] + nums[right] > nums[k]) {
                    // logic: If nums[right] is large enough to make a triangle with nums[left],
                    // then it is ALSO large enough to make a triangle with every number 
                    // AFTER nums[left] (because the array is sorted).
                    // So we add all those possibilities at once.
                    count += (right - left);
                    
                    // Try a smaller number for the second side
                    right--;
                } else {
                    // Sum is too small, we need a larger first side
                    left++;
                }
            }
        }
        
        return count;
    }
}
