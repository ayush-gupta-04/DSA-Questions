// brute force : find peak , and expand to left and right to find valley. find max.
// one pass : 
//       -> while travelling find start valley , peak , end valley . find max.         

class Solution {
    boolean isPeak(int[] nums , int i){
        int n = nums.length;
        return (i >= 1 && i <= n-2 && nums[i-1] < nums[i] && nums[i] > nums[i + 1]);
    }
    boolean startV(int[] nums , int i){
        int n = nums.length;
        if(n == 1) return true;
        if(i == 0){
            return nums[1] > nums[0];
        }
        return (i <= n-2 && nums[i-1] >= nums[i] && nums[i] < nums[i+1]);
    }
    boolean endV(int[] nums ,int i ){
        int n = nums.length;
        if(n == 1) return true;
        if(i == n-1){
            return nums[i-1] > nums[i];
        }
        return (i >= 1 && nums[i-1] > nums[i] && nums[i] <= nums[i + 1]);
    }
    public int longestMountain(int[] nums) {
        int n = nums.length;
        int max = 0;
        int s = -1;
        int e = -1;
        int p = 0;
        for(int i = 0; i < n; i++){
            if(p == 0 && startV(nums, i)){    // find start valley only if i didn't find a peak yet.
                s = i;
            }else if(s != -1 && isPeak(nums , i)){    // find peak only if i have found a start valley.
                p = 1;
            }else if(p == 1 && endV(nums , i)){      // only find the end valley if i have a peak behind.
                e = i;
                max = Math.max(max , e - s + 1);
                s = -1;
                p = 0;
                e = -1;
                if(startV(nums , i)){
                    s = i;
                } 
            }
        }
        return max;
    }
}
