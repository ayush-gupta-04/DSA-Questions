// time : N
// space : 1

//using two pointers method
class Solution {
    public int[] twoSum(int[] nums, int target) {
        int n = nums.length;
        int s = 0; int e = n-1;
        while(s < e){
            int sum = nums[s] + nums[e];
            if(sum == target){
                return new int[]{s + 1,e + 1};
            }else if(sum < target){
                s++;
            }else{
                e--;
            }
        }
        return new int[]{-1,-1};
    }
}
