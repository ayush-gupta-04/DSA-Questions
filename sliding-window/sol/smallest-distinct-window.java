// time : N
// space : 26

class Solution {
    public int findSubString(String str) {
        // code here
        int[] map = new int[26];
        int n = str.length();
        
        for(int i = 0;i < n; i++){
            char ch = str.charAt(i);
            map.put(ch , map.getOrDefault(ch,0) + 1);
        }
        
        int r = 0;
        int l = 0;
        int distinct = map.size();
        map.clear();
        int min = n;
        
        while(r < n){
            char cr = str.charAt(r);
            map.put(cr , map.getOrDefault(cr,0) + 1);
            
            while(l <= r && map.size() == distinct){
                min = Math.min(min , r - l + 1);
                char cl = str.charAt(l);
                map.put(cl, map.get(cl)-1);
                if(map.get(cl)==0) map.remove(cl);
                l++;
            }
            r++;
        }
        return min;
    }
}
