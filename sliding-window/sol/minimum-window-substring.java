// TC -> O(N)
// SC -> O(255)


class Solution {
    public String minWindow(String s, String t) {
        int[] map = new int[255];
        int n = s.length();
        int m = t.length();
        int r = 0;
        int l = 0;
        int start = -1;
        int len = Integer.MAX_VALUE;
        int cnt = 0;
        for(char ch : t.toCharArray()){
            map[ch]++;
        }
        while(r < n){
            if(map[s.charAt(r)] > 0) cnt++;
            map[s.charAt(r)]--;

            while(l <= r && cnt == m){
                if(r-l+1 < len){
                    start = l;
                    len = r-l+1;
                }
                map[s.charAt(l)]++;
                if(map[s.charAt(l)] > 0) cnt--;
                l++;
            }
            r++;
        }
        if(start == -1) return "";
        return s.substring(start , start + len);
    }
}
