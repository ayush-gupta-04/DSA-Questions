// Approach 1: HashMap + Sorting (The Brute Force)
// 1. Count how many times each number appears.
// 2. Sort the numbers based on those counts.
// 3. Pick the top k from the sorted list.
// TC -> (NlogN)
// SC -> (N)

import java.util.*;

class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        // Step 1: Count frequencies using our 'cnt' map
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int num : nums) {
            cnt.put(num, cnt.getOrDefault(num, 0) + 1);
        }

        // Step 2: Put keys in a list
        List<Integer> uniqueNums = new ArrayList<>(cnt.keySet());

        // Step 3: Sort the list based on frequency in descending order
        uniqueNums.sort((a, b) -> cnt.get(b) - cnt.get(a));

        // Step 4: Extract the top k elements
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = uniqueNums.get(i);
        }

        return result;
    }
}





// ------------------- Approach 2 : Approach 2: HashMap + Min-Heap ---------------------
// TC -> O(NlogK)
// SC -> O(N) + O(K)

// We will have a hashmap for the frequencies.
// for "TOP K" elements we will use HEAP.


import java.util.*;

class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int num : nums) {
            cnt.put(num, cnt.getOrDefault(num, 0) + 1);
        }

        // Min-Heap based on frequencies
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(
            (a, b) -> cnt.get(a) - cnt.get(b) 
        );

        for (int num : cnt.keySet()) {
            minHeap.add(num);
            // Kick out the smallest frequency element if we exceed k
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = minHeap.poll(); 
        }

        return result;
    }
}






// ----------------- Approach 3 : Bucket Sort --------------------
// TC -> O(N)
// SC -> O(N)

// Intuition : 
// -> The frequencies for each element can be from 1 -> N  (where N = size of the array)
// -> We will make bucket array. 
// -> bucket[4] will contain all element having frequency 4;

// ALGO : 
// Populate the cnt HashMap.
// Create an array of lists, buckets, of size N + 1.
// Iterate through the cnt map. If number x has frequency f, add x to buckets[f].
// Iterate backwards from N down to 0 on the buckets array.
// If a bucket is not empty, append its contents to the result array until we have gathered k elements.

import java.util.*;

class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int num : nums) {
            cnt.put(num, cnt.getOrDefault(num, 0) + 1);
        }

        // Array of Lists (Buckets) where index = frequency
        List<Integer>[] buckets = new List[nums.length + 1];
        for (int key : cnt.keySet()) {
            int frequency = cnt.get(key);
            if (buckets[frequency] == null) {
                buckets[frequency] = new ArrayList<>();
            }
            buckets[frequency].add(key);
        }

        int[] result = new int[k];
        int resIndex = 0;
        
        // Iterate from highest possible frequency down to 0
        for (int i = buckets.length - 1; i >= 0 && resIndex < k; i--) {
            if (buckets[i] != null) {
                for (int num : buckets[i]) {
                    result[resIndex++] = num;
                    if (resIndex == k) {
                        return result; // Stop early once we have k elements
                    }
                }
            }
        }
        
        return result;
    }
}














