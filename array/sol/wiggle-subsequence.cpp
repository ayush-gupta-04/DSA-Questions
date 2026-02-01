// 1   17   5   10   10   13   15   10   5   16   8
//    (u)  (d)  (u    e   u    u )  (d   d)  (u)  (d)

// that's y we use down and up.
// if i have u ,u,u,u,u, .. my up will not increment since down will be constant.

class Solution {
    public int wiggleMaxLength(int[] nums) {
        int up = 1;
        int down = 1;
        for(int i = 1; i < nums.length ; i++){
            if(nums[i] > nums[i - 1]){
                up = down + 1;
            }else if (nums[i] < nums[i - 1]){
                down = up + 1;
            }
        }
        return Math.max(up , down);
    }
}
