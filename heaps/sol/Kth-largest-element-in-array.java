// build a heap of size k
// Algorithm : 
// if((size of heap < k)  OR (a > top)){
//    -> add a to heap.
//    -> pop the top ele if size of heap > k.
// }



class Solution {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((x,y) -> Integer.compare(x,y));

        for(int a : nums){
            if(pq.size() < k || a > pq.peek()){
                pq.offer(a);
                if(pq.size() > k) pq.poll();
            }
        }

        return pq.peek();
    }
}
