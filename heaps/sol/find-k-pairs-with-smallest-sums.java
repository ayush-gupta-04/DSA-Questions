// Time Complexity: O(k * log k),The loop runs k times, and in each iteration, we perform heap insertions and deletions, which cost O(log k). In total, this results in O(k log k) time.
// Space Complexity: O(k), The heap and visited set can grow to size k in the worst case.
// We will store triplet in PQ.
// for i , j ... we will add ( i+1 , j )  and  (i , j + 1) into the PQ.
// because others will be bigger than these.
// It will be like BFS solution.
// Edge case : we could end up adding the same indexes twice .. therefore we will use a set.


class Solution {
    public List<List<Integer>> kSmallestPairs(int[] a, int[] b, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((x,y) -> Integer.compare(x[0],y[0]));
        HashSet<String> set = new HashSet<>();

        int an = a.length;
        int bn = b.length;
        
        pq.offer(new int[]{a[0] + b[0] , 0 , 0});
        set.add("0:0");

        List<List<Integer>> ans = new ArrayList<>();

        while(k-- > 0 && !pq.isEmpty()){
            int ai = pq.peek()[1];
            int bi = pq.peek()[2];
            pq.poll();

            ans.add(Arrays.asList(a[ai],b[bi]));

            String key1 = (ai+1) + ":" + bi;
            String key2 = ai + ":" + (bi + 1);
            if(ai+1 < an && !set.contains(key1)){
                pq.offer(new int[]{a[ai+1] + b[bi],ai + 1 , bi});
                set.add(key1);
            }
            if(bi+1 < bn && !set.contains(key2)){
                pq.offer(new int[]{a[ai] + b[bi+1] ,ai , bi+1});
                set.add(key2);
            }
        }
        return ans;
    }
}
