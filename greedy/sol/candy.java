// If the downhill slope becomes longer than the uphill peak (down > peak), the peak child at the very top needs $1$ extra candy to stay larger than their right neighbor! 
// 


class Solution {
    public int candy(int[] ratings) {
        if (ratings == null || ratings.length == 0) {
            return 0;
        }

        // First child always receives at least 1 candy
        int candies = 1;
        
        int up = 0;
        int down = 0;
        int peak = 0;

        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i - 1]) {
                // Case 1: Climbing Uphill
                up++;
                peak = up;
                down = 0;
                candies += (1 + up);
            } 
            else if (ratings[i] == ratings[i - 1]) {
                // Case 2: Flat Ground
                up = 0;
                down = 0;
                peak = 0;
                candies += 1;
            } 
            else {
                // Case 3: Sliding Downhill
                up = 0;
                down++;
                
                // If downhill exceeds the peak, the peak child gets +1 extra candy
                candies += down + (down > peak ? 1 : 0);
            }
        }

        return candies;
    }
}
