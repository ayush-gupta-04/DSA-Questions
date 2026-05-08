class Solution {
    public int maximalRectangle(char[][] mat) {
        int n = mat.length;
        int m = mat[0].length;
        int[][] nums = new int[n][m];

        for(int j = 0;j < m; j++){
            int sum = 0;
            for(int i = 0 ; i < n;i++){
                if(mat[i][j]-48 == 0){
                    sum = 0;
                }else{
                    sum += (mat[i][j] - 48);
                }
                nums[i][j] = sum;
            }
        }
        int max = 0;

        for(int[] row : nums){
            int rect = findRectangle(row);
            max = Math.max(max , rect);
        }
        return max;
    }

    int findRectangle(int[] nums){
        int[] nse = nse(nums);
        int[] pse = pse(nums);
        int max = 0;
        int n = nums.length;
        for(int i = 0;i < n; i++){
            int height = nums[i];
            int width = nse[i] - pse[i] - 1;
            max = Math.max(max , height*width);
        }
        return max;
    }

    int[] nse(int[] nums){
        int n = nums.length;
        int i = n-1;
        int[] ans = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();
        while(i >= 0){
            while(!stack.isEmpty() && nums[i] <= nums[stack.peekLast()]){
                stack.pollLast();
            }
            ans[i] = stack.isEmpty() ? n : stack.peekLast();
            stack.offerLast(i);
            i--;
        }
        return ans;
    }
    int[] pse(int[] nums){
        int n = nums.length;
        int i = 0;
        int[] ans = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();
        while(i < n){
            while(!stack.isEmpty() && nums[i] <= nums[stack.peekLast()]){
                stack.pollLast();
            }
            ans[i] = stack.isEmpty() ? -1 : stack.peekLast();
            stack.offerLast(i);
            i++;
        }
        return ans;
    }
}
