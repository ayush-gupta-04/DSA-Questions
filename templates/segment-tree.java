// Segment Tree Template for Range Sum Query.
package pack;

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
        int m = low + (high - low) / 2; // Overflow-safe midpoint
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
            seg[idx] = val;
            return;
        }

        int m = low + (high - low) / 2; // Overflow-safe midpoint
        if(i <= m){
            update(2*idx + 1 , low , m , i , val);
        }else{
            update(2*idx + 2 , m + 1 , high , i , val);
        }
        seg[idx] = seg[2*idx + 1] + seg[2*idx + 2];
    }
    //range query.
    public int query(int l , int r){
        return query(0 , 0, n - 1, l , r);
    }

    private int query(int idx, int low, int high, int l, int r) {
        // No overlap: [low, high] is completely outside [l, r]
        if (r < low || l > high) {
            return 0; // Return 0 for sum query (identity element)
        }
        
        // Total overlap: [low, high] is completely inside [l, r]
        if (l <= low && high <= r) {
            return seg[idx];
        }

        // Partial overlap
        int m = low + (high - low) / 2; // Overflow-safe midpoint
        int leftQuery = query(2 * idx + 1, low, m, l, r);
        int rightQuery = query(2 * idx + 2, m + 1, high, l, r);
        return leftQuery + rightQuery;
    }
}
