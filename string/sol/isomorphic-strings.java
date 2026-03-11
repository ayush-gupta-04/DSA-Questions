// TC = O(N)
// extra SC = O(2*256)

// we don't allow many-to-one  or  one-to-many.
// we have to  maintain two maps.
// one for u -> v ... another for v -> u
class Solution {
    public boolean isIsomorphic(String s, String t) {
        HashMap<Character,Character> map = new HashMap<>();
        HashMap<Character,Character> revMap = new HashMap<>();
        int n = s.length();
        for(int i = 0;i < n;i++){
            char u = s.charAt(i);
            char v = t.charAt(i);
            if(map.containsKey(u) && map.get(u) != v) return false;
            if(revMap.containsKey(v) && revMap.get(v) != u) return false;
            map.put(u,v);
            revMap.put(v,u);
        }
        return true;
    }
}
