// If a number 'x' is swapped with 'y' then it must not be again swapped with 'y'(duplicate).
// we used hashset for it.
// We can't skip duplicates because we are changing the order everytime.

class Solution {
    void swap(List<Integer> nums , int s ,int e){
        int temp = nums.get(s);
        nums.set(s , nums.get(e));
        nums.set(e , temp);
    }

    List<List<Integer>> fun(List<Integer> nums , int s){
        List<List<Integer>> overall = new ArrayList<>();
        if(s == nums.size()){
            overall.add(new ArrayList<>(nums));
            return overall;
        }

        HashSet<Integer> set = new HashSet<>();

        for(int i = s ; i < nums.size() ; i++){
            if(set.contains(nums.get(i))) continue;
            
            swap(nums,s,i);
            overall.addAll(fun(nums , s + 1));
            swap(nums,s,i);
            set.add(nums.get(i));
        }
        return overall;
    }
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<Integer> arr = new ArrayList<>();
        for(int a : nums) arr.add(a);

        return fun(arr , 0);
    }
}
