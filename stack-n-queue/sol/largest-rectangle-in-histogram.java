class Solution {
    public int[] nse(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        Deque<Integer> st = new ArrayDeque<>();
        
        for (int i = n - 1; i >= 0; i--) {
            while (!st.isEmpty() && nums[st.peekLast()] >= nums[i]) {
                st.pollLast();
            }
            ans[i] = st.isEmpty() ? n : st.peekLast();
            st.offerLast(i);
        }
        return ans;
    }

    public int[] pse(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        Deque<Integer> st = new ArrayDeque<>();
        
        for (int i = 0; i < n; i++) {
            while (!st.isEmpty() && nums[st.peekLast()] >= nums[i]) {
                st.pollLast();
            }
            ans[i] = st.isEmpty() ? -1 : st.peekLast();
            st.offerLast(i);
        }
        return ans;
    }

    public int largestRectangleArea(int[] nums) {
        int[] nseArr = nse(nums);
        int[] pseArr = pse(nums);

        int n = nums.length;
        int maxArea = 0;
        
        for (int i = 0; i < n; i++) {
            int prev = pseArr[i];
            int next = nseArr[i];
            
            int width = next - prev - 1;
            int area = nums[i] * width;
            
            maxArea = Math.max(maxArea, area);
        }

        return maxArea;
    }
}
