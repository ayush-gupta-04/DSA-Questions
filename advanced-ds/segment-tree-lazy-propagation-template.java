// increment the range elements [l , r] by value 'val'.

package pack;


class LazyInfo {
    int val;
    int update;
    public LazyInfo(int val){
        this.val = val;
        this.update = 0;
    }
}

public class SegmentTree{
    LazyInfo[] seg;
    int n;

    public SegmentTree(int n){
        this.seg = new LazyInfo[4 * n];
        this.n = n;
    }

    public void display(){
        for(LazyInfo l : seg){
            System.out.print(l != null ? l.val+ " " : "null ");
        }
        System.out.println();
    }

    
    //build function
    public void build(int[] nums){
        build( 0 , 0 , nums.length - 1 , nums);
    }

    private void build(int ind , int low , int high , int[] nums){
        if(low == high){
            seg[ind] = new LazyInfo(nums[low]);
            return;
        }
        int m = (low + high)/2;
        build(2*ind + 1 , low , m , nums );
        build(2*ind + 2 , m + 1, high , nums);
        seg[ind] = new LazyInfo(seg[2*ind + 1].val + seg[2*ind + 2].val);
    }

    public int rangeUpdate(int l , int r , int val){
        rangeUpdate(0 , 0 , n - 1, l  , r, val);
        return seg[0].val;
    }

    private void rangeUpdate(int ind , int low , int high , int l , int r , int val){
        //update the previous remaining updates.
        // and propagates downwards.
        if(seg[ind].update != 0){
            //an update remains.
            seg[ind].val += (high - low + 1)*seg[ind].update;
            //propagate downwards.
            if(low != high){
                seg[2*ind + 1].update += seg[ind].update;
                seg[2*ind + 2].update += seg[ind].update;
            }
            seg[ind].update = 0;
        }


        if(high < l || r < low){
            //no overlap.
            return;
        }
        else if(l <= low && high <= r){
            //complete overlap.
            //update the node.
            seg[ind].val += (high - low + 1)*val;
            //propagate downwards.
            if(low != high){
                seg[2*ind + 1].update += val;
                seg[2*ind + 2].update += val;
            }
            return;
        }
        else{
            int m = (low + high)/2;
            rangeUpdate(2*ind + 1 , low , m , l , r , val);
            rangeUpdate(2*ind + 2 , m + 1 , high , l , r , val);
            seg[ind].val = seg[2*ind + 1].val + seg[2*ind + 2].val;
        }
    }

    public int rangeQuery(int l, int r) {
        return rangeQuery(0, 0, n - 1, l, r);
    }

    private int rangeQuery(int ind, int low, int high, int l, int r) {
        /update the previous remaining updates.
        // and propagates downwards.
        if(seg[ind].update != 0){
            //an update remains.
            seg[ind].val += (high - low + 1)*seg[ind].update;
            //propagate downwards.
            if(low != high){
                seg[2*ind + 1].update += seg[ind].update;
                seg[2*ind + 2].update += seg[ind].update;
            }
            seg[ind].update = 0;
        }


        //now everything is just the same.

        // 2. Check for overlap.
        // No overlap
        if (high < l || r < low) {
            return 0; // Return identity element for sum
        }

        // Complete overlap
        if (l <= low && high <= r) {
            // The value is now up-to-date, so we can return it.
            return seg[ind].val;
        }

        // Partial overlap: query left and right children and return their sum.
        int m = low + (high - low) / 2;
        int leftQuery = rangeQuery(2 * ind + 1, low, m, l, r);
        int rightQuery = rangeQuery(2 * ind + 2, m + 1, high, l, r);
        return leftQuery + rightQuery;
    }
}
