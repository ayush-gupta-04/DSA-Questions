import java.util.*;

class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        Deque<Integer> d = new ArrayDeque<>();
        int n = nums.length;
        int[] ans = new int[n - k + 1];
        int idx = 0;

        for (int i = 0; i < n; i++) {

            // pushing into deque (remove smaller elements from back)
            while (!d.isEmpty() && nums[i] >= nums[d.peekLast()]) {
                d.pollLast();
            }
            d.offerLast(i);

            // remove elements outside window
            if (d.peekFirst() <= i - k) {
                d.pollFirst();
            }

            // first window formed -> record answer
            if (i >= k - 1) {
                ans[idx++] = nums[d.peekFirst()];
            }
        }

        return ans;
    }
}
