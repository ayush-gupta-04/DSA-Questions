// ------------------ Optimal Approach ----------------------
// time : NlogN
// space : N



class Solution {
    static int mergeSort(int[] nums , int low,  int high){
        if(low >= high) return 0;
        int cnt = 0;
        
        int mid = low + (high - low)/2;
        cnt += mergeSort(nums , low , mid);
        cnt += mergeSort(nums , mid + 1, high);
        cnt += merge(nums , low, mid, high);
        return cnt;
    }
    
    static int merge(int[] nums, int low, int mid ,int high){
        int l = low;
        int r = mid + 1;
        int[] temp = new int[high - low + 1];
        int k = 0;
        int cnt = 0;
        while(l <= mid && r <= high){
            if(nums[l] <= nums[r]){
                temp[k++] = nums[l++];
            }else{
                cnt += (mid - l + 1); // all elements after l from the left arr.
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
        return cnt;
    }
    static int inversionCount(int nums[]) {
        // Code Here
        return mergeSort(nums , 0,nums.length-1);
        
    }
}
