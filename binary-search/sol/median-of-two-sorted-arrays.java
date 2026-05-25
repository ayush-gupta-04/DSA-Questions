// -------------------- Approach 1 : Brute Force ----------------------
// time : n + m
// space : 1
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;

        int N = n1 + n2;

        int i1 = 0;
        int i2 = 0;
        double a = -1.0;
        double b = -1.0;
        int k = 0;
        while(i1 < n1 && i2 < n2){
            if(nums1[i1] < nums2[i2]){
                if(k == N/2) b = nums1[i1];
                if(k == (N/2) - 1) a = nums1[i1];
                i1++;
            }else{
                if(k == N/2) b = nums2[i2];
                if(k == (N/2) - 1) a = nums2[i2];
                i2++;
            }
            k++;
        }

        while(i1 < n1){
            if(k == N/2) b = nums1[i1];
            if(k == (N/2) - 1) a = nums1[i1];
            i1++;
            k++;
        }

        while(i2 < n2){
            if(k == N/2) b = nums2[i2];
            if(k == (N/2) - 1) a = nums2[i2];
            i2++;
            k++;
        }

        if(N%2 == 0){
            return (a + b)/2;
        }
        return b;
    }
}





// ----------------------------------- Approach : optimal -----------------------------------------
// time : min(n1 , n2)
// space : 1
class Solution {
    public double findMedianSortedArrays(int[] A1, int[] A2) {
        int n1 = A1.length;
        int n2 = A2.length;
        if(n1 > n2) return findMedianSortedArrays(A2,A1);
        int s = 0;
        int e = n1;
        int left = (n1 + n2)/2;
        while(s <= e){
            int mid1 = s + (e - s)/2;
            int mid2 = left - mid1;
            double l1 = -1e9 ; double l2 = -1e9;
            double r1 = 1e9; double r2 = 1e9;
            if(mid1 < n1) r1 = A1[mid1];
            if(mid2 < n2) r2 = A2[mid2];
            if(mid1-1 >= 0) l1 = A1[mid1-1];
            if(mid2-1 >= 0) l2 = A2[mid2-1];

            if(l1 <= r2 && l2 <= r1){
                if((n1 + n2)%2 == 1){
                    return Math.min(r1,r2);
                }else{
                    return Math.max(l1,l2)/2.0 + Math.min(r1,r2)/2.0;
                }
            }

            if(l2 > r1) s = mid1 + 1;
            else e = mid1 - 1;
        }

        return -1;
    }
}
