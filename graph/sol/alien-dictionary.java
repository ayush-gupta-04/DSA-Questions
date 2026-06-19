// ordering : 
// - in adjacent words.
// - first char which differ.

// main intuition : 
// - we have some characters present here.
// - we have relation between some character.
// - Cycle should not exist.
// - length of topo sort should be == uniqueCharCount.
// - other wise cycle exist.


class Solution {
    public String findOrder(String[] words) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            adj.add(new ArrayList<>());
        }
        
        int[] indegree = new int[26];
        boolean[] present = new boolean[26]; // Track characters actually in the words
        int uniqueCharCount = 0;
        
        // Mark present characters
        for (String word : words) {
            for (char c : word.toCharArray()) {
                if (!present[c - 'a']) {
                    present[c - 'a'] = true;
                    uniqueCharCount++;
                }
            }
        }
        
        // Build the dependency graph
        for (int i = 0; i < words.length - 1; i++) {
            String str1 = words[i];
            String str2 = words[i + 1];
            
            // Exception Case: "apple" cannot come before "app"
            if (str1.length() > str2.length() && str1.startsWith(str2)) {
                return ""; 
            }
            
            int len = Math.min(str1.length(), str2.length());
            for (int j = 0; j < len; j++) {
                if (str1.charAt(j) != str2.charAt(j)) {
                    int u = str1.charAt(j) - 'a';
                    int v = str2.charAt(j) - 'a';
                    adj.get(u).add(v);
                    indegree[v]++;
                    break; // Only the first differing character gives order
                }
            }
        }
        
        // Kahn's Algorithm BFS
        Deque<Integer> q = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();
        
        // Only push characters that are actually present AND have 0 indegree
        for (int i = 0; i < 26; i++) {
            if (present[i] && indegree[i] == 0) {
                q.offerLast(i);
            }
        }
        
        while (!q.isEmpty()) {
            int node = q.pollFirst();
            sb.append((char) (node + 'a'));
            
            for (int neigh : adj.get(node)) {
                indegree[neigh]--;
                if (indegree[neigh] == 0) {
                    q.offerLast(neigh);
                }
            }
        }
        
        // If the built string doesn't match unique characters count, a cycle exists!
        if (sb.length() < uniqueCharCount) {
            return "";
        }
        
        return sb.toString();
    }
}
