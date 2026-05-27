// -------------------- Better Approach : Sort and Traverse ------------------------
// time : NlogN + N
// space : 1

class Solution {
    public int longestConsecutive(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int count = 0;
        int max = 0;
        int last = Integer.MIN_VALUE;

        for(int i = 0;i < n;i++){
            if(nums[i] == last) continue;

            if(nums[i] == last + 1){
                count++;
                last = nums[i];
            }else{
                // a new sequence.
                count = 1;
                last = nums[i];
            }
            max = Math.max(max , count);
        }
        return max;
    }
}


// ------------------ Optimal Approach : Using Hashset -----------------
// time : N
// space : N
class Solution {
    public int longestConsecutive(int[] nums) {
        int n = nums.length;
        if (n == 0){
            return 0;
        }
        int max = 1;
        Set<Integer> set = new HashSet<>();

        // put all the array elements into set
        for (int i = 0; i < n; i++) {
            set.add(nums[i]);
        }

        // Find the longest sequence
        for (int a : set) {
            // if 'it' is a starting number
            if (!set.contains(a - 1)) {
                // find consecutive numbers
                int cnt = 1;
                int x = a;
                while (set.contains(x + 1)) {
                    x++;
                    cnt++;
                }
                max = Math.max(max, cnt);
            }
        }
        return max;
    }
}
