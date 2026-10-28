// ----------------- Heap -----------------

// time : N*logK
// space : N


class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> freq = new HashMap<>();
        for (String w : words) {
            freq.put(w, freq.getOrDefault(w, 0) + 1);
        }

        // Min-heap of size k
        PriorityQueue<String> pq = new PriorityQueue<>((a, b) -> {
            int fa = freq.get(a);
            int fb = freq.get(b);
            if (fa != fb) {
                return Integer.compare(fa, fb); // lower frequency evicted first
            }
            return b.compareTo(a); // larger alphabetical string evicted first
        });

        for (String word : freq.keySet()) {
            pq.offer(word);
            if (pq.size() > k) {
                pq.poll();
            }
        }

        List<String> result = new ArrayList<>();
        while (!pq.isEmpty()) {
            result.add(pq.poll());
        }
        Collections.reverse(result);
        return result;
    }
}



// ---------------- Bucket-Sort + Trie ---------------
// TODO
