// --------------- METHOD -1 : Sub-Optimal -----------------
// TC = O(N)
// SC = O(1)
// a simple partition algorithm
class Solution {
    void swap(int[] nums , int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    public void moveZeroes(int[] nums) {
        int n = nums.length;

        int j = -1;
        for(int i = 0; i < n; i++){
            if(nums[i] != 0){
                j++;
                swap(nums,i,j);
            }
        }
    }
}


// ------------- METHOD - 2 : Optimal ---------------------
// TC -> N
// SC -> 1
// start with
// j -> just before the first zero.
// i -> j + 1.
class Solution {
    void swap(int[] nums , int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    public void moveZeroes(int[] nums) {
        int n = nums.length;
        // Pointer to the first zero
        int j = -1;

        // Find the first zero
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                j = i;
                break;
            }
        }

        // If no zero found, return
        if (j == -1) return;

        j--;  // keep the pointer just before the first zero.

        // Start from the next index of first zero.
        for (int i = j+1; i < n; i++) {
            if (nums[i] != 0) {
                j++;
                swap(nums ,i,j);
            }
        }
    }
}
