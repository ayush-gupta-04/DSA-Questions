// --------------------------- Optimal Approach -------------------
// time : NlogN + N
// space : 3*K (K is the size of ans array)
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        int i = 0;
        int n = nums.length;
        List<List<Integer>> list = new ArrayList<>();
        while(i <= n-3){
            int s = i + 1;
            int e = n-1;
            while(s < e){
                int sum = nums[i] + nums[s] + nums[e];
                if(sum == 0){
                    list.add(Arrays.asList(nums[i],nums[s],nums[e]));
                    s++;
                    e--;
                    // skip duplicates at s and e.
                    while(s < e && nums[s] == nums[s-1]) s++;
                    while(s < e && nums[e] == nums[e+1]) e--;
                }else if(sum < 0){
                    s++;
                    // skip duplicate at s.
                    while(s < e && nums[s] == nums[s-1]) s++;
                }else{
                    e--;
                    // skip duplicate at e.
                    while(s < e && nums[e] == nums[e+1]) e--;
                }
            }

            i++;
            // skip duplicates at i;
            while(i < n && nums[i] == nums[i-1]) i++;
        }
        return list;
    }
}
