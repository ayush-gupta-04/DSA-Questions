// ------------- Brute Force ------------------
// time : N
// space : N

// - store the freq of all elements.
// - return the number with freq > n/2


// ---------------- Optimal Approach : Moore's Voting Algorithm  --------------------
// time : N
// space : 1
class Solution {
    public int majorityElement(int[] nums) {
        int cnt = 0;
        int maj = nums[0];
        for(int i = 0;i < nums.length ; i++){
            if(cnt == 0){
                maj = nums[i];
            }
            

            if(nums[i] == maj) cnt++;
            else cnt--;
        }


        //confirming that the maj is really the majority element.
        cnt = 0;
        for(int a : nums) if(a == maj) cnt++;

        if(cnt > nums.length/2) return maj;
        return -1;
    }
}
