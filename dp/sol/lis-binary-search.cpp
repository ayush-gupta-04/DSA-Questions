class Solution {
    public int lengthOfLIS(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for(int a : nums){
            // if i can put a at the back .. then put.
            // otherwise find lowerBound of a in list and replace it.
            if(list.isEmpty() || a > list.getLast()){
                list.add(a);
            }else{
                int idx = lowerBound(list , a);
                list.set(idx,a);
            }
        }
        return list.size();
    }
    private int lowerBound(List<Integer> nums , int k){
        int s = 0;
        int e = nums.size() - 1;
        while(s <= e){
            int m = s + (e-s)/2;
            if(k > nums.get(m)) s = m + 1;
            else e = m - 1;
        }
        return s;
    }
}
