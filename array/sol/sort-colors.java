// time : N
// space : 1
class Solution {
    void swap(int[] nums,int s, int e){
        int temp = nums[s];
        nums[s] = nums[e];
        nums[e] = temp;
    }
    public void sortColors(int[] nums) {
        int n = nums.length;
        int s = -1;
        int m = 0;
        int e = n;
        while(m < e){
            if(nums[m] == 0){
                s++;
                swap(nums,s,m);
                m++;
            }else if(nums[m] == 2){
                e--;
                swap(nums,e,m);  // do not move m yet...because after swapping at m there could be 0 , 1 or 2.
            }else{
                m++;
            }
        }
    }
}
