// -------------------- Approach : Brute Force ----------------------
// time : N
// space : 1
// if there would be no elements in the arr....ans will be k
// since there are elements .. they will increase our k.

class Solution {
    public int findKthPositive(int[] nums, int k) {
        for(int i = 0; i < nums.length ; i++){
            if(nums[i] > k){
                return k;
            }else{
                k++;
            }
        }
        return k;
    }
}



// ----------------- Approach : Optimal ----------------------
//time : logN
// space : N
// leftMissing = find number of missing numbers till a point.
// if k <= leftMissing .... e = m - 1;  .. move left
// if k > leftMissing ...... s = m + 1; ... move right


class Solution {
    public int findKthPositive(int[] nums, int k) {
        int s = 0;
        int e = nums.length - 1;
        while(s <= e){
            int m = s + (e-s)/2;
            int leftMissing = nums[m] - (m + 1);
            if(leftMissing >= k){
                e = m - 1;
            }else{
                s = m + 1;
            }
        }
        return k + e + 1;
        // k + e + 1;
        // Ae + missing
        // Ae + (k - leftMissing)
        // Ae + (k - (Ae - (e + 1)))
        // Ae + k - Ae + e + 1
        // k + e + 1;
    }
}
