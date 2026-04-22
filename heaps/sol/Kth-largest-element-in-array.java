// build a heap of size k
// Algorithm : 
//    -> add a to heap.
//    -> pop the top ele if size of heap > k.



class Solution {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(int a : nums){
            pq.offer(a);
            if(pq.size() > k) pq.poll();
        }

        return pq.peek();
    }
}
