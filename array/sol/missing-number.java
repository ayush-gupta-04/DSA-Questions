// ----------------------- Better Approch : Cycle Sort -------------------------
// time : 2*N
// space : 1

// sort everyone in their correct place.
// travel and where the culprit exist .. that is our place for the missing number.

class Solution {
    void swap(int[] nums,int i , int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    public int missingNumber(int[] nums) {
        int i = 0;
        int n = nums.length;
        while(i < n){
            // avoid invalid;
            if(nums[i] >= n) {
                i++;
                continue;
            }

            int cor = nums[i];   // correct index will be my number itself
            if(nums[i] != nums[cor]){
                swap(nums,i,cor);
            }else{
                i++;
            }
        }

        for(i = 0;i < n;i++){
            if(nums[i] != i){
                return i;
            }
        }
        return n;
    }
}



// ------------------- Optimal Approach : Using Xor -----------------
// time : N
// space : 1

// xor = xor from 0 to N
// xor = xor of all A[i].
// xor will have my ans.

class Solution {
    public int missingNumber(int[] nums) {
        int n = nums.length;
        int xor = 0;
        for(int i = 0;i <= n;i++){
            xor = xor^i;
        }
        for(int a : nums) xor = xor^a;
        return xor;
    }
}



// ------------------- Optimal Approach : Using Math ------------------
// time : N
// space : 1
class Solution {
    public int missingNumber(int[] nums) {
        int n = nums.length;

        int expectedSum = n * (n + 1) / 2;
        int actualSum = 0;

        for (int num : nums) {
            actualSum += num;
        }

        return expectedSum - actualSum;
    }
}
