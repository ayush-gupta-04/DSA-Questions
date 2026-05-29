// Important points : 
// - Can only be applied when nums[i] are smaller like 10^5 or 10^6
// - non comparitive sorting.

// ALGO : 
// - we make a freq array of size (maxElement + 1)
// - we traverse the freq array from i = 0 to maxElement
// - while traversing we update the original array.


import java.util.*;

public class Main {
    public static void countSort(int[] nums){
        int max = 0;
        for(int a : nums) max = Math.max(max , a);

        int[] freq = new int[max + 1];
        for(int a : nums){
            freq[a]++;
        }

        int k = 0;
        for(int i = 0; i <= max; i++){
            while(freq[i] > 0){
                nums[k++] = i;
                freq[i]--;
            }
        }
    }
    public static void main(String[] args) {
        int[] nums = {7,8,6,5,4,9,10};
        System.out.println("before sorting : ");
        System.out.println(Arrays.toString(nums));
        countSort(nums);
        System.out.println("after sorting : ");
        System.out.println(Arrays.toString(nums));
    }
}
