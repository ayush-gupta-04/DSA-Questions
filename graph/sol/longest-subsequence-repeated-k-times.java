// ---------------- Better-Solution ---------------
// OBV : 
// n/k < 8 ... means the ans cannot be greater than 7.

// Thought Process : 
// length of the answer is so small like max 7.
// we can build the ans using dfs or bfs and check if it is repeated K times or not.
// It would be convenient to check the strings of same length at a time.
// For this BFS would be the best option.
// we will generate every possible combinations of alphabets of length 7 max.

// time : 26^7
// space : 26^7

class Solution {
    boolean isRepeatedK(String s , String p ,int k){
        int n = s.length();
        int m = p.length();
        int j = 0;
        for(int i = 0 ;i < n;i++){
            if(s.charAt(i) == p.charAt(j)){
                j++;
                if(j==m){
                    j = 0;
                    k--;
                    if(k==0) return true;
                }
                
            }
        }
        return false;
    }

    public String longestSubsequenceRepeatedK(String s, int k) {
        Deque<String> q = new ArrayDeque<>();
        q.offer("");
        String ans = "";

        while(!q.isEmpty()){
            String curr = q.pollFirst();
            if(curr.length() > s.length()/k) continue;

            for(char c = 'a' ; c <= 'z' ; c++){
                String next = curr + c;
                if(isRepeatedK(s,next,k)){
                    q.offerLast(next);
                    ans = next;
                }
            }
        }
        return ans;
    }
}


// -------------------- Optimal Solution : pruning --------------------------------

// OLD sol problem : 
// - we were generating every possible combinations.

// Solution : 
// - If a subsequence is repeated K times ... then 100% it's chars will be repested K times.
// - We will build the combinations using only the chars which appears more or eq to K times.
// - it will prune some branches.

class Solution {
    boolean isRepeatedK(String s , String p ,int k){
        int n = s.length();
        int m = p.length();
        int j = 0;
        for(int i = 0 ;i < n;i++){
            if(s.charAt(i) == p.charAt(j)){
                j++;
                if(j==m){
                    j = 0;
                    k--;
                    if(k==0) return true;
                }
                
            }
        }
        return false;
    }

    public String longestSubsequenceRepeatedK(String s, int k) {
        List<Character> chars = new ArrayList<>();
        int[] map = new int[26];
        for(char c : s.toCharArray()){
            map[c-'a']++;
        }

        for(int i =0;i < 26 ; i++){
            if(map[i] >= k) chars.add((char)(i + 'a'));
        }

        Deque<String> q = new ArrayDeque<>();
        q.offer("");
        String ans = "";

        while(!q.isEmpty()){
            String curr = q.pollFirst();

            for(Character c : chars){
                String next = curr + c;
                if(isRepeatedK(s,next,k)){
                    q.offerLast(next);
                    ans = next;
                }
            }
        }
        return ans;
    }
}
