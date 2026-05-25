class Solution {
    public int kthElement(int[] A1, int[] A2, int k) {
        // code here
        int n1 = A1.length;
        int n2 = A2.length;
        if(n1 > n2) return kthElement(A2,A1,k);
        
        // You cannot take fewer than (k - 1) - n2 elements from A1. 
        // If you take fewer, you'd be forced to take more elements than exist in A2.
        int s = Math.max(0 , k - 1 - n2);   
        int e = Math.min(n1 , k-1);
        int left = k-1;   // i will have to take kth from r1 and r2
        while(s <= e){
            int mid1 = s + (e - s)/2;
            int mid2 = left - mid1;
            int l1 = (int)-1e9; int l2 = (int)-1e9;
            int r1 = (int)1e9; int r2 = (int)1e9;
            if(mid1 < n1) r1 = A1[mid1];
            if(mid2 < n2) r2 = A2[mid2];
            if(mid1-1 >= 0) l1 = A1[mid1-1];
            if(mid2-1 >= 0) l2 = A2[mid2-1];

            // since i have to take only one element .. so i removed one case.
            if(l1 <= r2 && l2 <= r1){
                return Math.min(r1,r2);
            }

            if(l2 > r1) s = mid1 + 1;
            else e = mid1 - 1;
        }

        return -1;
    }
}
