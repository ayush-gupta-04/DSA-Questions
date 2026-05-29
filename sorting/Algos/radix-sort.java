
// Time Complexity: (d * (n + k))
//  - n = number of elements in the array.
//  - k = base of the number system (10 for decimal numbers).
//  - d = number of digits in the maximum element.
//  - We run Counting Sort d times, and each Counting Sort takes O(n + k) time.

//  Space Complexity: (n + k)
//  - n = number of elements in the array.
//  - k = base of the number system (10 for decimal numbers).


// Important Points : 
// - The count sort runs d times .. where d is the number of digits in the max element.
// - We iterate backward while filling the output array.

// Pitfalls : 
// - for -ve numbers , we can add offset to make every number +ve for calculations.
// - We iterate backward while filling the output array.
// - It can easily sort -ve or +ve numbers effficiently.


// Use cases : 
// - String Sorting: It is fantastic for sorting fixed-length strings (like phone numbers, zip codes, or dates) where each character is treated as a digit.


import java.util.Arrays;

public class RadixSort {

    // 1. Utility function to get the maximum value in the array
    // This tells us how many digits the largest number has.
    static int getMax(int[] arr, int n) {
        int max = arr[0];
        for (int i = 1; i < n; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }

    static void countSort(int[] arr, int exp) {
        int n = arr.length;
        int[] output = new int[n];
        int[] count = new int[10];

        // Store the count of occurrences in count[]
        for (int i = 0; i < n; i++) {
            int digit = (arr[i] / exp) % 10;
            count[digit]++;
        }

        // we are taking the prefix sum of count to place arr[i] into output[]
        // if .. count[6] = 8 .. then .. number with digit 6 will be placed at pos 8.
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        // IMPORTANT: We iterate backwards to keep the sort STABLE!
        for (int i = n - 1; i >= 0; i--) {
            int digit = (arr[i] / exp) % 10;
            output[count[digit] - 1] = arr[i];
            count[digit]--;
        }

        // Copy the output array to arr[], so that arr[] now
        //   contains sorted numbers according to current digit
        for (int i = 0; i < n; i++) {
            arr[i] = output[i];
        }
    }

    public static void radixSort(int[] arr) {
        int n = arr.length;
        if (n <= 1) return;

        int max = getMax(arr, n);

        // Do counting sort for every digit. 
        // exp is passed as 1, 10, 100, 1000... 
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countSort(arr,exp);
        }
    }

    public static void main(String[] args) {
        int[] arr = {170, 45, 75, 90, 802, 24, 2, 66};
        radixSort(arr);
        System.out.println("Sorted array: " + Arrays.toString(arr));
    }
}
