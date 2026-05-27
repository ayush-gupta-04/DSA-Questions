// ----------------- Optimal Approach -------------
// Time : N
// space : 1

//Question breakdown : 
//We just have to find the group of numbers from end whose just largest number can be found.
//ex. 1,2,3,4,5  --> group is 4,5  -> just greater number is 5,4.
//ex. 1,2,3,9,8,7,6 -->  group is 3,9,8,7,6  -> just greater is 6,3,7,8,9

// Intution : 
//1. Find the group.
//   --> Travel from last and find the first small element...let kth element
//       ex. 1,2,3,6,5,4.  ..kth element = 3.
//               k
//   --> The group is right of k including k.
//2. replace the kth element with it's just greater element in the right.
//3. sort the right elements excluding kth one.

class Solution {
    public void sort(int[] nums, int s, int e){
        int j = s;
        while(j < nums.length - 1){
            int i = j + 1;
            while(i > s){
                if(nums[i - 1] > nums[i]){
                    swap(nums,i-1,i);
                    i--;
                }else{
                    break;
                }
            }
            j++;
        }
    }
    public void swap(int[] nums,int s, int e){
        int temp = nums[s];
        nums[s] = nums[e];
        nums[e] = temp;
    }
    public void nextPermutation(int[] nums) {
        int i = nums.length - 1;
        while(i >= 1){
            if(nums[i - 1] < nums[i]){      //found the group.
                int k = i - 1;
                int kth = nums[k];
                //finding the just greater element than the kth one in right side.
                while(i < nums.length && nums[i] > kth){
                    i++;
                }

                //just greater then kth is i - 1 th element.
                swap(nums,k,i-1);
                sort(nums,k+1,nums.length);
                return;
            }
            i--;
        }

        //if no kth element is found then it means that we have to find the first permutation.
        sort(nums,0,nums.length);
        return;
    }
}
