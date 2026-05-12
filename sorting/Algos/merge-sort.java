import java.util.*;

// Take a Temp Array while merging.
// TC - NlogN
// SC - N
public class Main {
    public static void main(String[] args) throws Exception {
        int[] nums = {4,3,5,3,2,6,5,7,6,6};
        mergeSort(nums,0,nums.length-1);
        System.out.println(Arrays.toString(nums));
    }
    public static void mergeSort(int[] arr, int left, int right) {
        if (left >= right) return;

        int mid = left + (right - left) / 2;
        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int[] list = new int[right - left + 1];
        int i1 = left;
        int i2 = mid + 1;
        int k = 0;
        while (i1 <= mid && i2 <= right) {
            if (arr[i1] <= arr[i2]) {
                list[k++] = arr[i1++];
            } else {
                list[k++] = arr[i2++];
            }
        }
        while(i1 <= mid){
            list[k++] = arr[i1++];
        }
        while(i2 <= right){
            list[k++] = arr[i2++];
        }

        // filling back in nums.
        k = 0;
        for(int i = left ; i <= right ; i++){
            arr[i] = list[k++];
        }
    }


}


