// Make a Guess: Pick the middle value (mid) of this range.
// Count how many numbers in the entire matrix are less than or equal to mid. 
// Since every row is sorted, we can use an efficient Binary Search (upper_bound) on each individual row to find this count.
// If the count is less than or equal to half the total elements, our mid is too small. We must guess higher.
// If the count is strictly greater than half, our mid might be the median, or the median is smaller. We guess lower.

class Solution {
    public int median(int[][] matrix) {
        int R = matrix.length;
        int C = matrix[0].length;
        
        // Step 1: Find the minimum and maximum values in the matrix
        int minVal = Integer.MAX_VALUE;
        int maxVal = Integer.MIN_VALUE;
        
        for (int i = 0; i < R; i++) {
            minVal = Math.min(minVal, matrix[i][0]);
            maxVal = Math.max(maxVal, matrix[i][C - 1]);
        }
        
        // The number of elements that should be smaller than the median
        int desired_count = (R * C) / 2;
        
        // Step 2: Binary Search on the value range
        while (minVal <= maxVal) {
            int mid = minVal + (maxVal - minVal) / 2;
            int count = 0;
            
            // Step 3: Count elements <= mid for each row
            for (int i = 0; i < R; i++) {
                count += upperBound(matrix[i], mid);
            }
            
            // Step 4: Adjust the search space
            if (count <= desired_count) {
                minVal = mid + 1; 
            } else {
                maxVal = mid - 1; 
            }
        }
        
        return minVal; 
    }
    
    // Helper function that replicates C++ upper_bound functionality
    // It returns the count of elements in the row that are less than or equal to the target
    private int upperBound(int[] row, int target) {
        int low = 0;
        int high = row.length;
        
        while (low < high) {
            int mid = low + (high - low) / 2;
            
            if (row[mid] <= target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        
        return low; 
    }
}
