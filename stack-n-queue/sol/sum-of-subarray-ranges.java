// TC -> O(N)
// SC -> O(N)
class Solution {

    public int[] nse(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        ArrayDeque<Integer> st = new ArrayDeque<>();
        
        for (int i = n - 1; i >= 0; i--) {
            while (!st.isEmpty() && nums[st.peekLast()] >= nums[i]) {
                st.pollLast();
            }
            ans[i] = st.isEmpty() ? n : st.peekLast();
            st.offerLast(i);
        }
        return ans;
    }

    public int[] psee(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        ArrayDeque<Integer> st = new ArrayDeque<>();
        
        for (int i = 0; i < n; i++) {
            while (!st.isEmpty() && nums[st.peekLast()] > nums[i]) {
                st.pollLast();
            }
            ans[i] = st.isEmpty() ? -1 : st.peekLast();
            st.offerLast(i);
        }
        return ans;
    }

    public int[] nge(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        ArrayDeque<Integer> st = new ArrayDeque<>();
        
        for (int i = n - 1; i >= 0; i--) {
            while (!st.isEmpty() && nums[st.peekLast()] <= nums[i]) {
                st.pollLast();
            }
            ans[i] = st.isEmpty() ? n : st.peekLast();
            st.offerLast(i);
        }
        return ans;
    }

    public int[] pgee(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        ArrayDeque<Integer> st = new ArrayDeque<>();
        
        for (int i = 0; i < n; i++) {
            while (!st.isEmpty() && nums[st.peekLast()] < nums[i]) {
                st.pollLast();
            }
            ans[i] = st.isEmpty() ? -1 : st.peekLast();
            st.offerLast(i);
        }
        return ans;
    }

    public long minRanges(int[] nums) {
        int[] nseArr = nse(nums);
        int[] pseeArr = psee(nums);
        int n = nums.length;
        long ans = 0;
        
        for (int i = 0; i < n; i++) {
            long left = i - pseeArr[i];
            long right = nseArr[i] - i;
            ans += (long) nums[i] * left * right;
        }
        return ans;
    }

    public long maxRanges(int[] nums) {
        int[] ngeArr = nge(nums);
        int[] pgeeArr = pgee(nums);
        int n = nums.length;
        long ans = 0;
        
        for (int i = 0; i < n; i++) {
            long left = i - pgeeArr[i];
            long right = ngeArr[i] - i;
            ans += (long) nums[i] * left * right;
        }
        return ans;
    }

    public long subArrayRanges(int[] nums) {
        return maxRanges(nums) - minRanges(nums);
    }
}
