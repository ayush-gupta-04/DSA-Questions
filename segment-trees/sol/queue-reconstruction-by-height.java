// OBV : we can easily place the one with the smallest height at it's kth place.
//       ex. 4,5 ... we can place it at 5th index.
//     : Next we will think of next greater in height.
// Sort with height ASC.
// if height same then k DESC.

// for every (h,k) we have to ask .. what is the k-th index in the queue...or the k+1 th empty place.
// and place the (h,k) there.

// Why segment tree ? 
// 1. we need to find the kth empty slot in the queue.
// 2. we need to update once we place the item.




// Implementation.⭐
// 1. The size N of segment tree = the empty places in the queue.
// 2. The leaf will be 1.
// 3. Node of segment tree = How many empty places are there in that range.
// 4. We will find index of k+1 the empty place.
// 5. if leftChildVal >= k  .... move left with k.
//    else ..... move right with k - leftChildVal.


public class SegmentTree{
    int[] seg;
    int n;
    
    public SegmentTree (int n){
        this.seg = new int[4*n];
        this.n = n;
    }
    public void build(){
        build(0,0,n-1);
    }
    private void build(int idx ,int low , int high){
        if(low == high){
            seg[idx] = 1;
            return;
        }
        int m = low + (high - low)/2;
        build(2*idx + 1 , low , m);
        build(2*idx + 2 , m + 1,  high);
        seg[idx] = seg[2*idx + 1] + seg[2*idx + 2];
    }
    //point update.
    public void update(int i){
        update(0 , 0, n - 1, i);
    }
    private void update(int idx , int low , int high , int i){
        if(low == high){
            seg[idx] = 0;
            return;
        }

        int m = low + (high - low) / 2;
        if(i <= m){
            update(2*idx + 1 , low , m , i);
        }else{
            update(2*idx + 2 , m + 1 , high , i);
        }
        seg[idx] = seg[2*idx + 1] + seg[2*idx + 2];
    }


    //range query.
    public int query(int k){
        return query(0 , 0, n - 1, k);
    }
    private int query(int idx, int low, int high, int k) {
        if(low == high){
            return low;
        }
        int m = low + (high - low)/2;
        if(seg[2*idx + 1] >= k){
            return query(2*idx + 1, low , m , k);
        }
        return query(2*idx + 2 , m + 1 , high , k - seg[2*idx + 1]);
    }
}

class Solution {
    public int[][] reconstructQueue(int[][] nums) {
        Arrays.sort(nums , (x,y) -> {
            if(x[0] != y[0]) return Integer.compare(x[0],y[0]);
            else return Integer.compare(y[1] , x[1]);
        });
        int n = nums.length;
        int[][] ans = new int[n][2];
        SegmentTree sgt = new SegmentTree(n);
        sgt.build();
        int i = 0;
        while(i < n){
            int k = nums[i][1] + 1;
            int idx = sgt.query(k);
            ans[idx] = nums[i];
            sgt.update(idx);
            i++;
        }
        return ans;
    }
}
