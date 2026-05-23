// ------------------------ Approach 1 : Linear Search ------------------------
// time : N
// space : 1

class Solution {
    public int singleNonDuplicate(int[] nums) {
        int n = nums.length;
        if(n==1) return nums[0];
        if(nums[0] != nums[1]) return nums[0];
        if(nums[n-1] != nums[n-2]) return nums[n-1];

        for(int i=1 ;i <= n-2 ;i++){
            if(nums[i] != nums[i-1] && nums[i] != nums[i+1]) return nums[i];
        }
        return -1;
    }
}


// -------------------------- Approach 2 : Xor method -----------------------
// time : N
// space : 1
class Solution {
    public int singleNonDuplicate(int[] nums) {
        int n = nums.length;
        int xor = 0;
        for(int a : nums){
            xor = xor^a;
        }
        return xor;
    }
}




// ------------------------ Approach 3 : Binary Search ----------------------
// time : logN
// space : 1

class Solution {
    public int singleNonDuplicate(int[] nums) {
        int n = nums.length;
        if(n == 1){
            return nums[0];
        }

        // we resolved the 0th and n-1th part before.
        if(nums[0] != nums[1]){
            return nums[0];
        }
        if(nums[n-1] != nums[n - 2]){
            return nums[n-1];
        }

        // since i have checked for 0th and n-1th ... my range would be this.
        int s = 1;
        int e = n - 2;
        while(s <= e){
            int m = s + (e - s)/2;
            if(nums[m] != nums[m - 1] && nums[m] != nums[m + 1]){
                return nums[m];
            }
            //i am on the left half;
            else if((m%2 == 0 && nums[m] == nums[m + 1] ) || (m%2 == 1 && nums[m] == nums[m - 1])){
                s = m + 1;
            }else if((m%2 == 1 && nums[m] == nums[m + 1]) || (m%2 == 0 && nums[m] == nums[m - 1])){
                e = m - 1;
            }
        }

        return -1;
    }
}
