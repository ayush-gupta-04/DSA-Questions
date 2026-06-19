// we will store List<String> in the queue.
// if a word transforms to X .. we will not make it vis right away.
// if we make it vis right away .. another list on the same level will not be able to transform to X anymore.
// we will wait to process all the list on the level .. then add that to the vis.


class Solution {
    public List<List<String>> findLadders(String start, String end, List<String> wordList) {
        Set<String> set = new HashSet<>(wordList);
        ArrayDeque<List<String>> q = new ArrayDeque<>();
        Set<String> vis = new HashSet<>();
        List<List<String>> ans = new ArrayList<>();

        vis.add(start);
        q.offerLast(new ArrayList<>(Arrays.asList(start)));

        while(!q.isEmpty()){
            int size = q.size();
            List<String> toVis = new ArrayList<>();

            for(int j = 0;j < size ; j++){
                List<String> list = q.pollFirst();

                String word = list.getLast();

                if(word.equals(end)){
                    ans.add(list);
                    continue;
                }
                for(int i = 0;i < word.length(); i++){
                    for(char k = 'a'; k <= 'z' ; k++){
                        char[] arr = word.toCharArray();
                        arr[i] = k;
                        String nextWord = new String(arr);
                        if(set.contains(nextWord) && !vis.contains(nextWord)){
                            list.add(nextWord);
                            q.offerLast(new ArrayList<>(list));
                            list.removeLast();

                            // do not add this nextWord to vis .. 
                            // add this word to vis once we are done with this level.
                            toVis.add(nextWord);
                        }
                    }
                }
            }

            for(String w : toVis) vis.add(w);
        }

        return ans;
    }
}



