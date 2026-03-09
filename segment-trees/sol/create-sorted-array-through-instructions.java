public class SegmentTree{
    int[] seg;
    int n;
    
    public SegmentTree (int n){
        this.seg = new int[4*n];
        this.n = n;
    }
    //point update.
    public void update(int i){
        update(0 , 0, n - 1, i);
    }
    private void update(int idx , int low , int high , int i){
        if(low == high){
            seg[idx] += 1;
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
        return leftQuery + rightQuery;
    }
}


class Solution {
    private TreeMap<Integer,Integer> coordinateComp(int[] nums){
        TreeSet<Integer> set = new TreeSet<>();
        for(int a : nums){
            set.add(a);
        }
        int rank = 0;
        TreeMap<Integer,Integer> map = new TreeMap<>();
        for(int a :  set){
            map.put(a , rank++);
        }
        return map;
    }
    public int createSortedArray(int[] nums) {
        int MOD = (int)1e9 + 7;
        int n = nums.length;
        TreeMap<Integer,Integer> map = coordinateComp(nums);
        int cost = 0;
        int N = map.size();
        SegmentTree sgt = new SegmentTree(N);

        for(int a : nums){
            int rank = map.get(a);
            int left = sgt.query(0 , rank-1);
            int right = sgt.query(rank+1 , N-1);
            cost = (cost +  Math.min(left , right))%MOD;
            sgt.update(rank);
        }
        return cost;
    }
}
