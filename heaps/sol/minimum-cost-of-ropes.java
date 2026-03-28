// Take 2 out .. add them to cost .. put their sum back to the pq.

class Solution {
    public static int minCost(int[] nums) {
        // code here
        // code here
        int n = nums.length;
        if(n <= 1) return 0;
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int a : nums){
            pq.offer(a);
        }
            
        int cost = 0;
        while(pq.size() != 1){
            int a = pq.poll();
            int b = pq.poll();
            cost += a + b;
            pq.offer(a + b);
        }
        return cost;
    }
    
}
