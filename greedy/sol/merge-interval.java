class Solution {
    public int[][] merge(int[][] nums) {
        Arrays.sort(nums , (x,y) -> Integer.compare(x[0], y[0]));
        List<int[]> merged = new ArrayList<>();

        for(int[] se : nums){
            if(merged.isEmpty() || se[0] > merged.getLast()[1]){
                merged.add(se);
            }else{
                merged.getLast()[0] = Math.min(merged.getLast()[0], se[0]);
                merged.getLast()[1] = Math.max(merged.getLast()[1], se[1]);
            }
        }


        // converting List<int[]> to int[][];
        int[][] ans = new int[merged.size()][2];
        int k = 0;
        for(int[] m : merged){
            ans[k++] = m;
        }
        return ans;
    }
}
