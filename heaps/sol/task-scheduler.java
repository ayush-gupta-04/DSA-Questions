// ------------------- APPROACH 1 : HEAP ----------------------
// TC -> 
// SC -> 
// In these type of questions .. we have 2 options 
// either we choose to execute tasks with higher freq first ot task with lower freq first.
// case 1 : lower freq first
//          -> if we execute task with lower freq first then 
//          -> then we are leaving task with higher freq for the last
//          -> and in the last we will have to give cooling period a lot.
// case 2 : higher freq first.
//          -> if we execute task with high freq frist.
//          -> then we are leaving task with lower freq for the last which is not an issue.

// algo :
// put all the freq in max-heap.
// until heap has elements.
// -> for n+1 time and until heap has elements :
//    -> take a freq out .. do freq--.
//    -> put it into a list
//    -> do task++
// -> put all freq from the list to the heap.
// -> time = time + (if it was the last cycle then add tasks otherwise add n+1).


class Solution {
    public int leastInterval(char[] tasks, int n) {
        // Build frequency map
        int[] freq = new int[26];
        for (char ch : tasks) {
            freq[ch - 'A']++;
        }

        // Max heap to store frequencies
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i < 26; i++) {
            if (freq[i] > 0) {
                pq.offer(freq[i]);
            }
        }

        int time = 0;
        // Process tasks until the heap is empty
        while (!pq.isEmpty()) {
            int cycle = n + 1;
            List<Integer> store = new ArrayList<>();
            int taskCount = 0;
            // Execute tasks in each cycle
            while (cycle-- > 0 && !pq.isEmpty()) {
                int currentFreq = pq.poll();
                store.add(currentFreq - 1);
                taskCount++;
            }
            // Restore updated frequencies to the heap
            for(int a : store){
                if(a > 0){
                    pq.offer(a);
                }
            }
            // Add time for the completed cycle
            time += (pq.isEmpty() ? taskCount : n + 1);
        }
        return time;
    }
}
