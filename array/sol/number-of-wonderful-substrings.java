class Solution {
    public long wonderfulSubstrings(String word) {
        long cnt = 0;
        int xr = 0;
        
        // Since characters are between 'a' and 'j', the bitmask ranges from 0 to 2^10 - 1 (1023)
        int[] freq = new int[1024];
        freq[0] = 1;  

        for (int i = 0; i < word.length(); i++) {
            int ch = 1 << (word.charAt(i) - 'a');
            xr ^= ch;

            // Case 1: All character counts are even
            cnt += freq[xr];

            // Case 2: Exactly one character count is odd
            for (int j = 0; j < 10; j++) {
                cnt += freq[xr ^ (1 << j)];
            }

            freq[xr]++;
        }

        return cnt;
    }
}
