// TC - O(N)
// SC = O(7)


import java.util.Map;

class Solution {
    public int romanToInt(String s) {
        // Create a lookup map for Roman numerals
        Map<Character, Integer> map = Map.of(
            'I', 1,
            'V', 5,
            'X', 10,
            'L', 50,
            'C', 100,
            'D', 500,
            'M', 1000
        );
        
        int n = s.length();
        int ans = map.get(s.charAt(n - 1));
        
        // Loop backwards from the second-to-last character
        for (int i = n - 2; i >= 0; i--) {
            int curr = map.get(s.charAt(i));
            int next = map.get(s.charAt(i + 1));
            
            // If current value is less than the next, subtract it (e.g., IV -> 5 - 1 = 4) 
            if (curr < next) {
                ans -= curr;
            } else { // Otherwise, add it (e.g., VI -> 5 + 1 = 6)
                
                ans += curr;
            }
        }
        return ans;
    }
}
