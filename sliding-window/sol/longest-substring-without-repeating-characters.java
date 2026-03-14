class Solution {
    public int lengthOfLongestSubstring(String s) {
        int l = 0;
        int r = 0;
        int[] map = new int[255];
        int n = s.length();
        int max = 0;
        while(r < n){
            char right = s.charAt(r);
            map[right]++;
            while(l <= r && map[right] > 1){
                char left = s.charAt(l);
                map[left]--;
                l++;
            }
            max = Math.max(max , r - l + 1);
            r++;
        }
        return max;
    }
}
