// 1. we will do coordinate compression.
//    -> sort uniques.
//    -> give them rank from 0 by putting them into map.
// 2. move from left -> right.
//    - we can also move from right -> left.
//    - but since we have +ve and -ve. we can have issues in finding the boundaries.
//    - because we will have to divide ... 
//      and in division we will have to take care of the floor and ceil.
// 3. for every x .. we will look for elements y > 2*x.
//    - we will store the rank of x , 2*x both.
//    - let rank of 2x = r.
//    - query in segment tree for range [r + 1 , N].
//    - where N = all compressed coordinates = size of the map.


// Important to note : 
// 1. Coordinate Compression : 
//    -> store a.
//    -> store 2*a (since we will query (rankOf(2*a) + 1 , N));
// 2. Size of Tree = number of ranks ...or... size of map ... number of unique values of map.



class Solution {
    public class SegmentTree{
        int[] seg;
        int n;
        
        public SegmentTree (int n){
            this.seg = new int[4*n + 1];
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


    public TreeMap<Long , Integer> cdComp(int[] nums){
        TreeSet<Long> st = new TreeSet<>();
        for(int a : nums){
            st.add((long)a);
            st.add((long)2*a);
        }
        TreeMap<Long , Integer> map = new TreeMap<>();
        int rank = 0;

        for(Long a : st){
            map.put(a , rank++);
        }
        return map;
    }


    public int reversePairs(int[] nums) {
        // coordinate compression.
        TreeMap<Long , Integer> map = cdComp(nums);
        int N = map.size();
        int n = nums.length;
        SegmentTree sgt = new SegmentTree(N);
        

        int cnt = 0;

        for(int i = 0;i < n ; i++){
            int L = map.get((long)2*nums[i]);
            int number = sgt.query(L + 1 , N);
            cnt += number;
            sgt.update(map.get((long)nums[i]));
        }

        return cnt; 
    }
}
