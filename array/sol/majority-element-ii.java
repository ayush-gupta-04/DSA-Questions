// time : N
// space : 1

// 1. we can have max of 2 maj elements.
// 2. we will have moore's voting algorithm but with 2 elements.

// IMP : moore's voting algorithm doesn't gurantee that in the end el1 and el2 are majority elements.
//       we have to confirm.

class Solution {
    public List<Integer> majorityElement(int[] nums) {
        int n = nums.length; //size of the array

        int cnt1 = 0; int cnt2 = 0; // counts
        int el1 = Integer.MIN_VALUE; // element 1
        int el2 = Integer.MIN_VALUE; // element 2

        // applying the Extended Boyer Moore's Voting Algorithm:
        for (int i = 0; i < n; i++) {
            if (cnt1 == 0 && el2 != nums[i]) {
                cnt1 = 1;
                el1 = nums[i];
            } 
            else if (cnt2 == 0 && el1 != nums[i]) {
                cnt2 = 1;
                el2 = nums[i];
            } 
            else if (nums[i] == el1) cnt1++;
            else if (nums[i] == el2) cnt2++;
            else {
                cnt1--; cnt2--;
            }
        }

        List<Integer> list = new ArrayList<>(); // list of answers

        // Manually check if the stored elements in
        // el1 and el2 are the majority elements:
        cnt1 = 0; cnt2 = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] == el1) cnt1++;
            if (nums[i] == el2) cnt2++;
        }

        int min = (int)(n / 3) + 1;
        if (cnt1 >= min) list.add(el1);
        if (cnt2 >= min) list.add(el2);
        return list;
    }
}
