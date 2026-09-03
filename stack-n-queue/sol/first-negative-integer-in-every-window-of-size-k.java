// we will only put -ve numbers in the deque.


class Solution {
    static List<Integer> firstNegInt(int arr[], int k) {
        // code here
        int n = arr.length;
        Deque<Integer> d = new ArrayDeque<>();
        int r = 0;
        List<Integer> list = new ArrayList<>();
        
        while(r < n){
            if(arr[r] < 0){
                d.offerLast(r);
            }
            
            if(!d.isEmpty() && r - d.peekFirst() + 1 > k) d.pollFirst();
            
            if(r >= k-1){
                if(!d.isEmpty()) list.add(arr[d.peekFirst()]);
                else list.add(0);
            }
            r++;
        }
        return list;
    }
}
