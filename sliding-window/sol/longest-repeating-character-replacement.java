// TC -> N
// SC -> O(26)
// the substring should hold this property ..  len - maxFreq <= k

class Solution {
    public int characterReplacement(String s, int k) {
        int n = s.length();
        int r = 0;
        int l = 0;
        int maxFreq = 0;
        int[] map = new int[26];
        int max = 0;
        while(r < n){
            int ch = s.charAt(r);
            map[ch-'A']++;
            maxFreq = Math.max(maxFreq , map[ch-'A']);

            while(l<=r && r-l+1-maxFreq > k){  // invalid when len - maxFreq > k.
                map[s.charAt(l)-'A']--;
                l++;
                for(int i = 0;i < 26 ; i++){   // we have to re-calc the maxFreq from the map.
                    if(map[i] != 0) maxFreq = Math.max(maxFreq , map[i]);
                }
            }
            max = Math.max(max , r - l + 1);
            r++;
        }
        return max;
    }
}
