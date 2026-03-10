// -------------------- METHOD-1 : DP------------------
// TC -> N^2.
// SC -> N.
// a big bottleneck


class Solution {
    public int lengthOfLIS(int[] nums, int k) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp , 1);
        for(int i = 0;i < n; i++){
            for(int j = 0 ;j < i;j++){
                if(nums[i] > nums[j] && (nums[i] - nums[j]) <= k){
                    dp[i] = Math.max(dp[i] , dp[j] + 1);
                }
            }
        }

        int max = 1;
        for(int i = 0;i < n; i++){
            max = Math.max(dp[i] ,max);
        }
        return max;
    }
}


// ------------------------ METHOD-2 : Segment Trees ---------------------
// TC -> NlogN
// SC -> N
// Intution for Segment Trees.
// -> If i am at 'j' then 
// -> for ele in the left of j ... i know that 'jth' element can be next element for [jth-k ... jth - 1].
// -> I need to know which element in the range [jth-k ... jth-1] forms max LIS ending there ??
// -> Therefore i processed every elment in the left of j in DP.
// -> But i need to know this in logN time.
// -> This looks range max query and logN time...we think of segment trees.

// Important for segment Trees :
// 1. leaf node = max LIS ending there.
// 2. range max query.
// 3. len = query for jth element.....update jth element with this len + 1.
// 4. if we encounter the same number jth multiple times, we update the leaf node jth to be the maximum of its current
//    value and the new len + 1.


public class SegmentTree{
    int[] seg;
    int n;
    
    public SegmentTree (int n){
        this.seg = new int[4*n];
        this.n = n;
    }
    //build function.
    public void build(int[] nums){
        build(0 , 0 , n - 1, nums);
    }
    private void build(int idx , int low , int high , int[] nums){
        if(low == high){
            seg[idx] = nums[low];
            return;
        }
        int m = low + (high - low) / 2; 
        build(2*idx + 1 , low , m , nums);
        build(2*idx + 2 , m + 1, high , nums);
        seg[idx] = seg[2*idx + 1] + seg[2*idx + 2];
    }
    //point update.
    public void update(int i , int val){
        update(0 , 0, n - 1, i , val);
    }
    private void update(int idx , int low , int high , int i , int val){
        if(low == high){
            seg[idx] = Math.max(val , seg[idx]);
            return;
        }

        int m = low + (high - low) / 2;
        if(i <= m){
            update(2*idx + 1 , low , m , i , val);
        }else{
            update(2*idx + 2 , m + 1 , high , i , val);
        }
        seg[idx] = Math.max(seg[2*idx + 1] , seg[2*idx + 2]);
    }
    //range query.
    public int query(int l , int r){
        return query(0 , 0, n - 1, l , r);
    }

    private int query(int idx, int low, int high, int l, int r) {
        // No overlap: [low, high] is completely outside [l, r]
        if (r < low || l > high) {
            return 0;
        }
        
        // Total overlap: [low, high] is completely inside [l, r]
        if (l <= low && high <= r) {
            return seg[idx];
        }

        // Partial overlap
        int m = low + (high - low) / 2;
        int leftQuery = query(2 * idx + 1, low, m, l, r);
        int rightQuery = query(2 * idx + 2, m + 1, high, l, r);
        return Math.max(leftQuery , rightQuery);
    }
}
class Solution {
    public int lengthOfLIS(int[] nums, int k) {
        int maxIdx = 0;
        for(int a : nums) maxIdx = Math.max(maxIdx , a);
        SegmentTree sgt = new SegmentTree(maxIdx + 1);

        for(int a : nums){
            int lower = (a - k);
            int upper = a-1;
            int len = sgt.query(lower , upper);
            sgt.update(a , len+1);
        }

        return sgt.seg[0];
    }
}
