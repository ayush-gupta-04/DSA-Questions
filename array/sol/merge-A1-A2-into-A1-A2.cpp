// --------------------- Brute-Force Approach ---------------------------
// TC = min(m + n) + O(mlogm) + O(nlogn).
// SC = O(1)
import java.util.*;

public class Main {

    public static void main(String[] args) {
        int[] A1 = {1, 2, 5, 7, 9};
        int[] A2 = {3, 4, 5, 10};
        int n = A1.length;
        int m = A2.length;
        int i = n - 1;     // start from last in A1
        int j = 0;        // start from first in A2
        while (i >= 0 && j < m) {
            if (A1[i] > A2[j]) {
                // XOR swap
                A1[i] = A1[i] ^ A2[j];
                A2[j] = A1[i] ^ A2[j];
                A1[i] = A1[i] ^ A2[j];
                i--;
                j++;
            } else {
                break;
            }
        }
        Arrays.sort(A1);
        Arrays.sort(A2);
    }
}

// ------------------- Optimal-Approach------------------------------------
// TC = (m + n)log(m + n)
// SC = O(1)


import java.util.*;

public class Main {

    static void swap(int[] A1, int[] A2, int i, int j) {
        int temp = A1[i];
        A1[i] = A2[j];
        A2[j] = temp;
    }

    public static void main(String[] args) {
        int[] A1 = {1, 2, 5, 7, 9};
        int[] A2 = {3, 4, 5, 10};
        int n = A1.length;
        int m = A2.length;
        int gap = (m + n) / 2 + (m + n) % 2;

        while (true) {
            int i = 0;
            int j = gap;
            while (j < (m + n)) {
                int a = (i < n ? A1[i] : A2[i - n]);
                int b = (j < n ? A1[j] : A2[j - n]);
                if (a > b) {
                    // swap a and b.
                    if (i < n && j < n) {
                        swap(A1, A1, i, j);
                    }else if (i < n && j >= n) {
                        swap(A1, A2, i, j - n);
                    }else {
                        swap(A2, A2, i - n, j - n);
                    }
                }
                i++;
                j++;
            }
            if (gap == 1) break;
            gap = (gap) / 2 + gap % 2;
        }
    }
}


