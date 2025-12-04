# Shortest Unsorted Continuous Subarray - Simplified Logic

## The Big Picture
Think of the array in three parts:  
`[ Small & Sorted ]` ... `[ THE MESSY MIDDLE ]` ... `[ Large & Sorted ]`

Our goal is to identify the **exact start and end** of the "Messy Middle."

---

## The 3-Step Algorithm (O(N) Time)

### 1. Find the "Obvious" Mess
* **Scan from Left:** Find the first index where the order breaks (value drops). Call this `temp_start`.
* **Scan from Right:** Find the first index where the order breaks (value rises). Call this `temp_end`.
* *Note: If the order never breaks, the array is already sorted. Return 0.*

### 2. Find the Extremes in the Mess
* Look at the subarray between `temp_start` and `temp_end`.
* Find the **Minimum (`min`)** value in this range.
* Find the **Maximum (`max`)** value in this range.
* **Why?** Because a tiny number in the middle (like `2`) might belong way back at index 0, forcing us to expand our subarray to the left.

### 3. Expand the Boundaries
* **Expand Left:** Look before `temp_start`. If any number is **greater than `min`**, it's in the wrong place. Move `start` to the left.
* **Expand Right:** Look after `temp_end`. If any number is **smaller than `max`**, it's in the wrong place. Move `end` to the right.

**Final Answer:** `end - start + 1`

---

## Quick Visual Example
**Input:** `[1, 5, 3, 4, 2, 6]`

1.  **Obvious Mess:** `5 > 3` (Index 1) and `4 > 2` (Index 4). Range is `[5, 3, 4, 2]`.
2.  **Extremes:** In `[5, 3, 4, 2]`, the `min` is **2** and `max` is **5**.
3.  **Expand:**
    * Check Left of range: Is `1 > 2`? **No.** Stop. `Start` is index 1.
    * Check Right of range: Is `6 < 5`? **No.** Stop. `End` is index 4.
4.  **Subarray:** `[5, 3, 4, 2]`. Length **4**.

---

## Mental Template
1.  **Identify** the unsorted core.
2.  **Get Min/Max** of that core.
3.  **Stretch** outwards if neighbors can't handle those Min/Max values.

---

## Solution Code (Java)

```java
class Solution {
    public int findUnsortedSubarray(int[] nums) {
        int n = nums.length;
        if (n < 2) return 0;

        // --- Step 1: Find the "Obvious Mess" (The Core) ---
        
        // Find first index from left where order breaks
        int left = 0;
        while (left < n - 1 && nums[left] <= nums[left + 1]) {
            left++;
        }
        
        // Edge Case: If loop reached the end, array is already sorted
        if (left == n - 1) return 0;

        // Find first index from right where order breaks
        int right = n - 1;
        while (right > 0 && nums[right] >= nums[right - 1]) {
            right--;
        }

        // --- Step 2: Find Min/Max inside the "Mess" ---
        
        int subMin = Integer.MAX_VALUE;
        int subMax = Integer.MIN_VALUE;
        
        // Scan the range we identified to find the true min/max values
        for (int k = left; k <= right; k++) {
            subMin = Math.min(subMin, nums[k]);
            subMax = Math.max(subMax, nums[k]);
        }

        // --- Step 3: Expand Outwards ---
        
        // Stretch Left: If the number on the left is bigger than our subMin, include it
        while (left > 0 && nums[left - 1] > subMin) {
            left--;
        }
        
        // Stretch Right: If the number on the right is smaller than our subMax, include it
        while (right < n - 1 && nums[right + 1] < subMax) {
            right++;
        }

        // Return the length of the window
        return right - left + 1;
    }
}
