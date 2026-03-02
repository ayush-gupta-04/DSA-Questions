// Segment Tree Template for Range Sum Query.
class SegmentTree{
    private int[] seg;
    private int[] lazy;
    private int n;
    
    public SegmentTree (int n){
        this.seg = new int[4*n];
        this.lazy = new int[4*n];
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
    public void pointUpdate(int i , int val){
        pointUpdate(0 , 0, n - 1, i , val);
    }
    private void pointUpdate(int idx , int low , int high , int i , int val){
        if(low == high){
            seg[idx] = val;
            return;
        }

        int m = low + (high - low) / 2;
        if(i <= m){
            pointUpdate(2*idx + 1 , low , m , i , val);
        }else{
            pointUpdate(2*idx + 2 , m + 1 , high , i , val);
        }
        seg[idx] = seg[2*idx + 1] + seg[2*idx + 2];
    }



    //range query.
    public int query(int l , int r){
        return query(0 , 0, n - 1, l , r);
    }

    private int query(int idx, int low, int high, int l, int r) {
        // update prev pending.
        if(lazy[idx] != 0){
            // update node.
            // propagate downwards.
            // reset lazy.
            seg[idx] += (high - low + 1) * lazy[idx];
            if(low != high){
                lazy[2*idx + 1] += lazy[idx];
                lazy[2*idx + 2] += lazy[idx];
            }
            lazy[idx] = 0;
        }



        // No overlap .... (l r low high) or (low high l r)
        if (r < low || l > high) {
            return 0;
        }
        
        // Total overlap .... (l low high r)
        if (l <= low && high <= r) {
            return seg[idx];
        }

        // Partial overlap
        int m = low + (high - low) / 2;
        int leftQuery = query(2 * idx + 1, low, m, l, r);
        int rightQuery = query(2 * idx + 2, m + 1, high, l, r);
        return leftQuery + rightQuery;
    }



    // increment (l , r) with val.
    public void rangeUpdate(int l , int r, int val){
        rangeUpdate(0, 0, n-1, l, r, val);
    }

    private void rangeUpdate(int idx, int low, int high , int l , int r, int val){
        // update prev pending.
        if(lazy[idx] != 0){
            // update node.
            // propagate downwards.
            // reset lazy.
            seg[idx] += (high - low + 1) * lazy[idx];
            if(low != high){
                lazy[2*idx + 1] += lazy[idx];
                lazy[2*idx + 2] += lazy[idx];
            }
            lazy[idx] = 0;
        }


        // no overlap.
        if (r < low || l > high) {
            return;
        }
        // complete overlap.
        if (l <= low && high <= r) {
            // update node.
            // propagate downward.
            // return.
            seg[idx] += (high - low + 1) * val;
            if(low != high){
                lazy[2*idx + 1] += val;
                lazy[2*idx + 2] += val;
            }
            return;
        }

        // partial overlap.
        int m = low + (high - low) / 2;
        rangeUpdate(2*idx + 1 , low , m , l , r , val);
        rangeUpdate(2*idx + 2 , m+1 , high , l , r , val);
        seg[idx] = seg[2*idx + 1] + seg[2*idx + 2];
    }
}
