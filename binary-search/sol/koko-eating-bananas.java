// Question : 
// Koko loves to eat bananas. There are n piles of bananas, the ith pile has piles[i] bananas. 
// The guards have gone and will come back in h hours.
// Koko can decide her bananas-per-hour eating speed of k. Each hour, she chooses some pile of bananas and eats k bananas from that pile. 
// If the pile has less than k bananas, she eats all of them instead and will not eat any more bananas during this hour.
// Koko likes to eat slowly but still wants to finish eating all the bananas before the guards return.
// Return the minimum integer k such that she can eat all the bananas within h hours.


// Example 1:
// Input: piles = [3,6,7,11], h = 8
// Output: 4

// Example 2:
// Input: piles = [30,11,23,4,20], h = 5
// Output: 30

// Example 3:
// Input: piles = [30,11,23,4,20], h = 6
// Output: 23
 

// Constraints:
// 1 <= piles.length <= 10^4
// piles.length <= h <= 10^9
// 1 <= piles[i] <= 10^9


class Solution {
    public long calculateHours(int[] nums, int m){
        long sum = 0;
        for(int b : nums){
            if(b%m == 0){
                sum = sum + (b/m);
            }else{
                sum = sum + (b/m) + 1;
            }
        }
        return sum;
    }
    public int minEatingSpeed(int[] nums, int h) {
        int s = 1;
        int e = Integer.MIN_VALUE;
        for(int a : nums) e = Math.max(e , a);

        while(s <= e){
            int mid = s + (e - s)/2;
            long hours = calculateHours(nums,mid);
            if(hours > h){
                s = mid + 1;
            }else{
                e = mid - 1;
            }
        }
        return s;
    }
}
