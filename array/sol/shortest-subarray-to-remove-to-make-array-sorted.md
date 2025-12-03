# 1574. Shortest Subarray to be Removed to Make Array Sorted

**Difficulty:** Medium  
**Topic:** Arrays, Two Pointers, Binary Search

---

## Problem Understanding

Given an integer array `arr`, we must remove a **subarray** (a contiguous part of the array) such that the remaining elements form a non-decreasing (sorted) sequence. We need to find the **minimum length** of the subarray to remove.

### The Structure of the Result
After removing a middle chunk, the remaining array will look like this:
`[Sorted Prefix] ... (Removed Subarray) ... [Sorted Suffix]`

To be valid:
1. The **Prefix** must be sorted.
2. The **Suffix** must be sorted.
3. The last element of the **Prefix** must be $\le$ the first element of the **Suffix**.

---

## Intuition & Algorithm

We can solve this in **O(N)** time using a Two-Pointer approach.

### Step 1: Find the Longest Sorted Prefix
Start from `index 0` and move forward as long as `arr[i] <= arr[i+1]`.
Let `left` be the index where this sorted sequence ends.
* *Edge Case:* If `left == n - 1`, the array is already sorted. Return 0.

### Step 2: Find the Longest Sorted Suffix
Start from the last index (`n-1`) and move backward as long as `arr[i-1] <= arr[i]`.
Let `right` be the index where this sorted sequence starts.

### Step 3: Calculate Base Cases
Before attempting to merge the two parts, we have two safe options:
1.  **Keep only the Prefix:** Remove everything from `left + 1` to the end.
2.  **Keep only the Suffix:** Remove everything from `0` to `right - 1`.
Initialize `result` as the minimum of these two options.

### Step 4: The Merge (Two Pointers)
We iterate through the **Prefix** using pointer `i` and the **Suffix** using pointer `j`.
* Start `i = 0`, `j = right`.
* **Condition:** If `arr[i] <= arr[j]`, we can successfully connect these two parts.
    * The elements between `i` and `j` are removed.
    * Update `result = min(result, j - i - 1)`.
    * Increment `i` to try and keep more of the prefix.
* **Else:** If `arr[i] > arr[j]`, the prefix element is too large. We need a larger element from the suffix to connect to.
    * Increment `j`.

---

## Java Solution

```java
class Solution {
    public int findLengthOfShortestSubarray(int[] arr) {
        int n = arr.length;
        
        // Step 1: Find the end of the sorted prefix (left)
        int left = 0;
        while (left < n - 1 && arr[left] <= arr[left + 1]) {
            left++;
        }
        
        // If the entire array is sorted, return 0
        if (left == n - 1) return 0;
        
        // Step 2: Find the start of the sorted suffix (right)
        int right = n - 1;
        while (right > 0 && arr[right - 1] <= arr[right]) {
            right--;
        }
        
        // Step 3: Initial Answer (Base Cases)
        // Option A: Remove everything after 'left' (Keep Prefix)
        // Option B: Remove everything before 'right' (Keep Suffix)
        int result = Math.min(n - left - 1, right);
        
        // Step 4: Merge Prefix and Suffix
        int i = 0;
        int j = right;
        
        while (i <= left && j < n) {
            if (arr[i] <= arr[j]) {
                // Valid connection found. 
                // We keep arr[0...i] and arr[j...n-1]
                // The gap between them is (j - i - 1)
                result = Math.min(result, j - i - 1);
                
                // Try to add one more element from the prefix
                i++;
            } else {
                // arr[i] is too big for arr[j], we need a larger number
                // from the suffix. Move j forward.
                j++;
            }
        }
        
        return result;
    }
}
