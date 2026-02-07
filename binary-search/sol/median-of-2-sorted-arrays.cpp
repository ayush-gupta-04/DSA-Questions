class Solution {
public:
    double findMedianSortedArrays(vector<int>& nums1, vector<int>& nums2) {
        int n1 = nums1.size();
        int n2 = nums2.size();
        // do bs on smaller array.
        if(n1 > n2) return findMedianSortedArrays(nums2,nums1);
        int s = 0;
        int e = n1;
        int left = (n1 + n2)/2;
        while(s <= e){
            int mid1 = s + (e - s)/2;
            int mid2 = left - mid1;
            double l1 = -1e9 ; double l2 = -1e9;
            double r1 = 1e9; double r2 = 1e9;
            if(mid1 < n1) r1 = nums1[mid1];
            if(mid2 < n2) r2 = nums2[mid2];
            if(mid1-1 >= 0) l1 = nums1[mid1-1];
            if(mid2-1 >= 0) l2 = nums2[mid2-1];

            if(l1 <= r2 && l2 <= r1){
                if((n1 + n2)%2 == 1){
                    return min(r1,r2);
                }else{
                    return max(l1,l2)/2.0 + min(r1,r2)/2.0;
                }
            }

            if(l2 > r1) s = mid1 + 1;
            else e = mid1 - 1;
        }

        return -1;
    }
};
