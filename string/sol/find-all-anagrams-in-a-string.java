class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        int[] map = new int[26];
        List<Integer> ans = new ArrayList<>();
        for(char ch : p.toCharArray()){
            map[ch-'a']++;
        }

        int m = p.length();
        int n = s.length();
        if(m > n) return ans;

        int cnt = 0;
        int last = 0;

        for(int i = 0;i < m; i++){
            char c = s.charAt(i);
            if(map[c - 'a'] > 0) cnt++;
            map[c-'a']--;
        }

        if(cnt == m) ans.add(0);

        int i = 0;
        while(i < n-m){
            char newChar = s.charAt(i+m);
            char oldChar = s.charAt(i);


            // remove old char and add new char to the window.
            if(map[newChar - 'a'] > 0) cnt++;
            map[newChar - 'a']--;
            map[oldChar - 'a']++;
            if(map[oldChar-'a'] > 0) cnt--;
            
            if(cnt == m) ans.add(i+1);
            i++;
        }
        return ans;
    }
}
