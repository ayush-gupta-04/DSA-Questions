// Thought process :
// 1. If i know the max of the whole buckets be x...
// 2. and nodeVal < fruit .. i am damm sure that i won;t have any buckets.
// 3. if nodeVal >= fruit ... i can have a basket.
//    and i have to process from the left -> right .. 
//    so i will look to the left half first then the right half..
//    ans this structure is built by segment trees.
// 4. when a basket is choosen then it will update the idx(which was choosen) .. point update.






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
        System.out.println(Arrays.toString(seg));
    }
    private void build(int idx , int low , int high , int[] nums){
        if(low == high){
            seg[idx] = nums[low];
            return;
        }
        int m = low + (high - low) / 2; 
        build(2*idx + 1 , low , m , nums);
        build(2*idx + 2 , m + 1, high , nums);
        seg[idx] = Math.max(seg[2*idx + 1] , seg[2*idx + 2]);
    }
    //point update.
    public void update(int i){
        update(0 , 0, n - 1, i);
        System.out.println(Arrays.toString(seg));
    }
    private void update(int idx , int low , int high , int i){
        if(low == high){
            seg[idx] = Integer.MIN_VALUE;
            return;
        }

        int m = low + (high - low) / 2;
        if(i <= m){
            update(2*idx + 1 , low , m , i);
        }else{
            update(2*idx + 2 , m + 1 , high , i);
        }
        seg[idx] = Math.max(seg[2*idx + 1] , seg[2*idx + 2]);
    }
    //range query.
    public int query(int x){
        return query(0 , 0, n - 1,x);
    }

    private int query(int idx, int low, int high, int x) {
        if(x > seg[idx]) return -1;

        if(low == high){
            return low;
        }

        
        int m = low + (high - low)/2;
        if(seg[2*idx + 1] >= x){
            return query(2*idx + 1 ,low, m,x);
        }
        return query(2*idx + 2 ,m + 1 , high,x);
    }
}



class Solution {
    public int numOfUnplacedFruits(int[] fruits, int[] baskets) {
        int cnt = 0;
        int n = fruits.length;
        SegmentTree sgt = new SegmentTree(n);
        sgt.build(baskets);
        for(int f : fruits){
            int idx = sgt.query(f);
            if(idx == -1){
                cnt++;
                continue;
            }
            System.out.println(idx);
            sgt.update(idx);
        }
        return cnt;
    }
}



