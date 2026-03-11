// --------------- METHOD -1 : Sub-Optimal -----------------
// TC = O(N)
// SC = O(1)
// a simple partition algorithm
class Solution {
public:
    void moveZeroes(vector<int>& nums) {
        int i = -1;
        for (int j = 0; j < nums.size(); j++) {
            if (nums[j] != 0) {
                i++;
                swap(nums[i], nums[j]);
            }
        }
    }
};


// ------------- METHOD - 2 : Optimal ---------------------
// TC -> N
// SC -> 1
// start with
// j -> At first zero.
// i -> j + 1.
class Solution {
public:
    // Function to move zeroes to the end
    void moveZeroes(vector<int>& nums) {
        // Pointer to the first zero
        int j = -1;

        // Find the first zero
        for (int i = 0; i < nums.size(); i++) {
            if (nums[i] == 0) {
                j = i;
                break;
            }
        }

        // If no zero found, return
        if (j == -1) return;

        // Start from the next index of first zero
        for (int i = j + 1; i < nums.size(); i++) {
            if (nums[i] != 0) {
                swap(nums[i], nums[j]);
                j++;
            }
        }
    }
};
