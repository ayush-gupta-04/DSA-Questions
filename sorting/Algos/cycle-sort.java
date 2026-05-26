public static void cyclicSort(int[] arr) {
    int i = 0;
    int n = arr.length;
    while (i < n) {
        // Check if the number is within array bounds.
        if(!(arr[i] >= 0 && arr[i] < n)){    // this number will does not belong to any index.
            i++;
            continue;
        }

        // find the correct index of the number.
        int corrIdx = arr[i];
        
        if (arr[i] != arr[corrIdx]) {
            swap(arr, i, corrIdx);
        } else {
            i++;  // If it is at the correct index (or is out of bounds like 'n'), move on
        }
    }
}
