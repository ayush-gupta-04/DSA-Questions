class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        int i = 0;
        int n = nums.length;
        List<List<Integer>> list = new ArrayList<>();
        while(i <= n-4){
            int j = i + 1;
            while(j <= n-3){
                int s = j + 1;
                int e = n-1;
                while(s < e){
                    long sum = (long)nums[i] + (long)nums[j] + (long)nums[s] + (long)nums[e];
                    if(sum == target){
                        list.add(Arrays.asList(nums[i], nums[j], nums[s], nums[e]));
                        s++;
                        e--;
                        // skip duplicates at s and e.
                        while(s < e && nums[s] == nums[s-1]) s++;
                        while(s < e && nums[e] == nums[e+1]) e--;
                    }else if(sum < target){
                        s++;
                        // skip duplicate at s.
                        while(s < e && nums[s] == nums[s-1]) s++;
                    }else{
                        e--;
                        // skip duplicate at e.
                        while(s < e && nums[e] == nums[e+1]) e--;
                    }
                }
                j++;
                // skip duplicates at j;
                while(j < n && nums[j] == nums[j-1]) j++;
            }

            i++;
            // skip duplicates at i;
            while(i < n && nums[i] == nums[i-1]) i++;
        }
        return list;
    }
}
