// this is a classic sliding window problem.
// we know that word length is same .. therefore we start from 0 to k-1 .. so that we can get to the starting point of every word.
// algo :
// -> if a word at r is unknown .. reset the map .. reset the window.
// -> if a word at r (sub) is known .. add it to the map.
// -> if freq of this word sub is greater than we want .. shrink the window from left until its valid again.
// -> after this .. just check if the l and r covers the required length or not... if yes then push l to res.

// ANALOGY : 
// -> if we are given character[] rather than string[] as words.
// ->    question would be we just have to find a substring having all the characters.


class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        Map<String, Integer> mainMap = new HashMap<>();
        for (String w : words) {
            mainMap.put(w, mainMap.getOrDefault(w, 0) + 1);
        }

        int n = s.length();
        int m = words.length;
        int k = words[0].length();

        int i = 0;
        List<Integer> res = new ArrayList<>();
        
        while (i < k) {
            int l = i;
            int r = i;
            Map<String, Integer> map = new HashMap<>();

            while (r + k <= n) {
                String sub = s.substring(r, r + k);
                
                // in this case we have to remove this sub from the whole window ..
                // so we reset the map and move the r and l to after sub.
                if (!mainMap.containsKey(sub)) {
                    map.clear();
                    r += k;
                    l = r;
                    continue;
                }
                
                map.put(sub, map.getOrDefault(sub, 0) + 1);
                
                while (l <= r && map.get(sub) > mainMap.get(sub)) {
                    String leftSub = s.substring(l, l + k);
                    map.put(leftSub, map.get(leftSub) - 1);
                    if (map.get(leftSub) == 0) map.remove(leftSub);
                    l += k;
                }

                if (r + k - l == (k * m)) {
                    res.add(l);
                }
                r += k;
            }
            i++;
        }
        return res;
    }
}
