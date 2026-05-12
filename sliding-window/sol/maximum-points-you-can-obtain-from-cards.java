// ----------------------- Brute Force : Trying all combos -----------------
// TC -> K*K -> outer loop K times ... inner 2 loops combined K times.
// SC -> 1

class Solution {
    public int maxScore(int[] cardPoints, int k) {
        int n = cardPoints.length;

        int maxSum = 0;

        // Try all combinations: i from front, k-i from back
        for (int i = 0; i <= k; i++) {

            int tempSum = 0;
            for (int j = 0; j < i; j++) {  // from front
                tempSum += cardPoints[j];
            }
            for (int j = 0; j < k - i; j++) {  // from back
                tempSum += cardPoints[n - 1 - j];
            }

            maxSum = Math.max(maxSum, tempSum);
        }

        return maxSum;
    }
}



// ------------------ Better Approach : Precalculating the sum -------------
// TC -> N + N + K  -> prefix : N ... suffix : N ... main loop : K
// SC -> 2*N -> for prefix and suffix array.

class Solution {
    public int maxScore(int[] nums, int k) {
        int n = nums.length;
        int[] pre = new int[n];
        int[] suff = new int[n];

        pre[0] = nums[0];
        for(int i = 1; i < n ;i++){
            pre[i] = pre[i-1] + nums[i];
        }
        
        suff[n-1] = nums[n-1];
        for(int i = n-2 ; i>=0 ; i--){
            suff[i] = suff[i+1] + nums[i];
        }

        int max = 0;

        for(int i = 0; i <= k ; i++){
            int numLeft = i;
            int numRight = k - i;
            int sumLeft = numLeft == 0 ? 0 : pre[numLeft - 1];
            int sumRight = numRight == 0 ? 0 : suff[n - numRight];
            max = Math.max(max , sumLeft + sumRight);
        }

        return max;
    }
}



// -------------------- Optimal Approach : Slide a window of length K --------------------
// Have a window on the front first.
// remove an element from the last of the window....add an element from the back of the array.
// means slide the window to the left.

// TC -> K + K -> initial window : K .... sliding of the window : K
// SC -> 1

class Solution {
    public int maxScore(int[] nums, int k) {
        int n = nums.length;
        int sum = 0;
        for(int i = 0;i < k  ; i++){
            sum += nums[i];
        }
        int max = sum;
        
        int r = k-1; // last of the window.
        int l = n-1; // last of the array.
        while(r >= 0){
            sum -= nums[r];
            sum += nums[l];
            max = Math.max(max , sum);
            r--;
            l--;
        }
        return max;
    }
}
