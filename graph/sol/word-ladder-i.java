// time : N*L*26
// - N is the number of words in the list
// - L is the length of each word
// - For each word, we attempt to change each of its L characters to 26 possible letters.

// space : 2*N
// - set and vis for every word.



// Intuition for BFS
// - shortest transformation sequence
// - for a word we can change it to multiple words...we have options.

// do every possible tranformation on a word .. if new word is in set && not visited before.

class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> set = new HashSet<>(wordList);
        if(!set.contains(endWord)) return 0;
        
        Deque<String> q = new ArrayDeque<>();        
        Set<String> vis = new HashSet<>();


        vis.add(beginWord);
        q.offerLast(beginWord);
        
        int changes = 1;
        
        while(!q.isEmpty()){
            int size = q.size();

            for(int i = 0; i < size; i++){
                String word = q.pollFirst();

                if(word.equals(endWord)) return changes;
                
                for(int j = 0; j < word.length(); j++){
                    for(int k = 'a'; k <= 'z'; k++){
                        char arr[] = word.toCharArray();
                        arr[j] = (char) k;
                        
                        String str = new String(arr);
                        if(set.contains(str) && !vis.contains(str)){
                            q.offerLast(str);
                            vis.add(str);
                        }
                    }
                }
            }
            changes++;
        }
        return 0;
    }
}
