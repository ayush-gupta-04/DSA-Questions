// ----------------------- OPTIMAL ---------------------
// TC - O(N*K)[temp key] + O(N*logN)[map search]  .. N size of vec .. K size of a str.
// SC - O(N)[map] + O(N)[temp key].


// To Group every anagram .. we need a key.
// Key should have a common property for all anagram of same group.
// If we sort every str of a group .. they will be same.
// ex . eat, tea -> they would become aet.
// sorting the string can become my key.

class Solution {
    String findKey(String s){
        char[] arr = s.toCharArray();
        Arrays.sort(arr);
        return new String(arr);
    }
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();
        for(String s : strs){
            String key = findKey(s);
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(s);
        }

        List<List<String>> list = new ArrayList<>();
        for(String key : map.keySet()){
            list.add(map.get(key));
        }
        return list;
    }
}
