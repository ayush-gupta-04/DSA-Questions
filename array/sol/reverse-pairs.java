// [1, 4, 7, 8] [2, 3, 4, 5]
//     i         j
// - for merging we should move j .. 
// - but i still don't how many elements in nums1 after i will be paired with j.
// - i should not count while merging.

class Solution {
    static int mergeSort(int[] nums , int low,  int high){
        if(low >= high) return 0;
        int cnt = 0;
        
        int mid = low + (high - low)/2;
        cnt += mergeSort(nums , low , mid);
        cnt += mergeSort(nums , mid + 1, high);

        int l = low;
        int r = mid + 1;
        while(l <= mid && r <= high){
            long a = (long)nums[l];
            long b = 2*(long)nums[r];
            if(a > b){                 // if b can pair with a .. then b can pair with everyone after a also.
                cnt += (mid - l + 1);  // all elements after a from the left arr.
                r++;
            }else{            // if b cannot pair with a .. then a cannot be paired with anyone after b...move a
                l++;
            }
        }
        merge(nums , low, mid, high);
        return cnt;
    }

    static void merge(int[] nums, int low, int mid ,int high){
        int l = low;
        int r = mid + 1;
        int[] temp = new int[high - low + 1];
        int k = 0;
        while(l <= mid && r <= high){
            if(nums[l] <= nums[r]){
                temp[k++] = nums[l++];
            }else{
                temp[k++] = nums[r++];
            }
        }
        
        while(l <= mid){
            temp[k++] = nums[l++];
        }
        
        while(r <= high){
            temp[k++] = nums[r++];
        }
        
        for(int i = 0;i < temp.length ; i++){
            nums[low + i] = temp[i];
        }
    }

    public int reversePairs(int[] nums) {
        return mergeSort(nums , 0,nums.length-1);
    }
}
