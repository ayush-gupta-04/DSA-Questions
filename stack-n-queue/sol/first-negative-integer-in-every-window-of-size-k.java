// we are maintaining clusters in deque.
// cluster of (----)(++0+0)
// cluster of (-ve numbers)(+ve & 0's)
// -ve numbers are always in the front.
// if peekLast has +ve number .. and i am -ve .. i don;t need +ve in the deque...pop +ve


class Solution {
    static List<Integer> firstNegInt(int arr[], int k) {
        // code here
        int n = arr.length;
        Deque<Integer> d = new ArrayDeque<>();
        int r = 0;
        List<Integer> list = new ArrayList<>();
        
        while(r < n){
            while(!d.isEmpty() && arr[r] < 0 && arr[d.peekLast()] >= 0){
                d.pollLast();
            }
            d.offerLast(r);
            
            if(d.peekLast() - d.peekFirst() + 1 > k) d.pollFirst();
            
            if(r >= k-1){
                if(arr[d.peekFirst()] < 0) list.add(arr[d.peekFirst()]);
                else list.add(0);
            }
            r++;
        }
        return list;
    }
}
