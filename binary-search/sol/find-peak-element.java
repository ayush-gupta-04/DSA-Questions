// -------------------- Approach : Binary Search --------------
// time : logN
// space : 1

class Solution {
    public int findPeakElement(int[] nums) {
        int n = nums.length;

        // handle the edge cases here itself
        if(n == 1) return 0;
        if(nums[0] > nums[1]) return 0;
        if(nums[n-1] > nums[n-2]) return n-1;

        int s = 1;
        int e = n-2;
        while(s <= e){
            int m = s + (e - s)/2;
            if(nums[m] > nums[m+1] && nums[m] > nums[m-1]){  // peak
                return m;
            }else if(nums[m-1] < nums[m] && nums[m+1] > nums[m]){  // increasing part
                s = m + 1;
            }else{  // decreasing or minima part.
                e = m-1;
            }
        }   
        return -1;
    }
}
