// If a number 'x' is swapped with 'y' then it must not be again swapped with 'y'(duplicate).
// we used hashset for it.
// We can't skip duplicates because we are changing the order everytime.

// If a number 'x' is swapped with 'y' then it must not be again swapped with 'y'(duplicate).
// we used hashset for it.
// We can't skip duplicates because we are changing the order everytime.

class Solution {
    void swap(List<Integer> nums , int s ,int e){
        int temp = nums.get(s);
        nums.set(s , nums.get(e));
        nums.set(e , temp);
    }

    void fun(List<Integer> nums , int s, List<List<Integer>> ans) {
        if(s == nums.size()){
            ans.add(new ArrayList<>(nums));
            return;
        }

        HashSet<Integer> set = new HashSet<>();

        for(int i = s ; i < nums.size() ; i++){
            if(set.contains(nums.get(i))) continue;
            
            swap(nums, s, i);
            fun(nums , s + 1, ans);
            swap(nums, s, i);
            set.add(nums.get(i));
        }
        return;
    }
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<Integer> arr = new ArrayList<>();
        List<List<Integer>> ans = new ArrayList<>();
        for(int a : nums) arr.add(a);

        fun(arr , 0, ans);
        return ans;
    }
}
