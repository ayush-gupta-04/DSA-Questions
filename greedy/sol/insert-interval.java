class Solution {
    public int[][] insert(int[][] nums, int[] se) {
        List<int[]> res = new ArrayList<>();
        int i = 0;
        int n = nums.length;

        // add all intervals before new-interval.
        while (i < n && nums[i][1] < se[0]) {
            res.add(nums[i]);
            i++;
        }
        
        // merge intervals which are overlapping with the new-interval
        while (i < n && nums[i][0] <= se[1]) {
            se[0] = Math.min(se[0], nums[i][0]);
            se[1] = Math.max(se[1], nums[i][1]);
            i++;
        }
        res.add(se);
    
        // insert all intervals after the new-interval.
        while (i < n) {
            res.add(nums[i]);
            i++;
        }
    
        return res.toArray(new int[res.size()][]);
    }
}
