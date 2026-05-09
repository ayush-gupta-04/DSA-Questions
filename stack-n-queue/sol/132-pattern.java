// There are 2 options :
// 1. We can fix num1 , num2 .. then find num3 such that nums3 is in range [nums1,num2].
// 2. We can fix num2 , num3 .. then we can find num1 such that num1 < num3.


// 1. approach :
// TC -> O(N²)
// SC -> O(1)
// -> When we are at i .. nums[i] is my num2.
// -> Min till i-1 will be my num1 .. we are taking min to maximise the probability to find num3 in other side.
// -> Do a loop to find num3 lying in range [num1 , nums2] from i+1 -> n-1;
// -> We have to loop to the end.


// 2. Approach:
// TC -> O(N)
// SC -> O(N)
// -> We will find traverse from the end .. and we will somehow store the num2 num3 (3 2) pattern.
// -> for any nums[i] .. we will check if nums[i] < num3 .. return true.
// -> since we would have been 100% sure that we have num2 > num3 on the right .. i just need to check for num1.
// -> Main question is how to store 3 2 patten on the right.
// -> INTUTION : 
//    -> If i get 8 .. i will store it. (i don't know it is stack right now).
//    -> If i get 9 .. which is greater then 8 .. i am damm sure that i have found 3 2 pattern.
//       i will assign num3 = 8;
//    -> But i would have found 6 .. i will store it .. [6 , 8].
//    -> Next 5 come .. i will store it ... [5, 6, 8].
//    -> next 7 come .. i will delete some ele ..and assign last popped to num3.
//       ds -> [7,8] .. num3 = 6.

// -> Another INTUTION : 
//    -> I will maintain a monotonic dec stack [3,4,5,7,8,9 ...].
//    -> If 6 came and i am able to  pop some ele .. i am 100% sure that there is a 3 2 pattern.
//       and my num2 would be 6 and num3 would be the last popped(because it will be the closest to 6).
//    -> My stack would be [6,7,8,9 ...] and num3 = 5;


class Solution {
    public boolean find132pattern(int[] nums) {
        int num2 = Integer.MIN_VALUE;
        int n = nums.length;
        Deque<Integer> st = new ArrayDeque<>();
        
        for(int i = n-1 ; i >= 0;i--){
            if(nums[i] < num2) return true;

            while(!st.isEmpty() && nums[i] > st.peekLast()){
                num2 = st.pollLast();
            }
            st.offerLast(nums[i]);
        }

        return false;
    }
}
