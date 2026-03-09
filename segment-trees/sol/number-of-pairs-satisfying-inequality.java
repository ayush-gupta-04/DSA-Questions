// -------------------------------- METHOD-1 : Segment Trees --------------------------------
// TC -> O(NlogN).
// SC -> O(N).
// After a few simplyfication : 
// Di  <=  Dj + d.
// for a 'j' we need to find 'i' such that this cond holds ture.
// for every Dj we can query in segment from (0 , Dj + d) .. then update the segment tree index Dj.
// we will do coordinate compression here.

public class SegmentTree{
    long[] seg;
    int n;
    
    public SegmentTree (int n){
        this.seg = new long[4*n];
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
    public long query(int l , int r){
        return query(0 , 0, n - 1, l , r);
    }

    private long query(int idx, int low, int high, int l, int r) {
        // No overlap: [low, high] is completely outside [l, r]
        if (r < low || l > high) {
            return 0L;
        }
        
        // Total overlap: [low, high] is completely inside [l, r]
        if (l <= low && high <= r) {
            return seg[idx];
        }

        // Partial overlap
        int m = low + (high - low) / 2;
        long leftQuery = query(2 * idx + 1, low, m, l, r);
        long rightQuery = query(2 * idx + 2, m + 1, high, l, r);
        return leftQuery + rightQuery;
    }
}




class Solution {
    private TreeMap<Integer , Integer> coorComp(int[] A1, int[] A2 , int d){
        TreeSet<Integer> set = new TreeSet<>();
        int n = A1.length;
        for(int i = 0;i < n ;i++){
            int di = A1[i] - A2[i];
            set.add(di);
            set.add(di + d);
        }

        TreeMap<Integer,  Integer> map = new TreeMap<>();
        int rank = 0;
        for(int a : set){
            map.put(a , rank++);
        }
        return map;
    }
    public long numberOfPairs(int[] A1, int[] A2, int d) {
        int n = A1.length;
        TreeMap<Integer,Integer> map = coorComp(A1,A2,d);
        int N = map.size();
        SegmentTree sgt = new SegmentTree(N);
        long cnt = 0;
        for(int i = 0; i < n; i++){
            int Dj = A1[i] - A2[i];
            cnt += sgt.query(0 , map.get(Dj + d));
            sgt.update(map.get(Dj));
        }
        return cnt;
    }
}
