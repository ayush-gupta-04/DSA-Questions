// ------------------------- Approach : Using Extra space -------------------
// time : N + M
// space : N + M

// approach : 
// - merge nums1 and nums2 into ans.
// - put elements of ans into nums1 back

class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = 0;
        int j = 0;
        int[] ans = new int[m+n];
        int k = 0 ;
        //take two pointers i and j....i for nums1.....j for nums2.
        //jisme chota element hoga usko ans array m daalo and uska pointer ko aage move kro.
        while(i < m && j < n){
            if(nums1[i] <= nums2[j]){
                ans[k++] = nums1[i++];
            }else{
                ans[k++] = nums2[j++];
            }
        }
        //some elements may remain in either of the two array
        while(i<m){
            ans[k++] = nums1[i++];
        }
        while(j<n){
            ans[k++] = nums2[j++];
        }
        for(int l = 0; l < nums1.length  ; l++){
            nums1[l] = ans[l];
        }
    }
}





// --------------------------- Optimal : No extra space --------------------
// time : M + N
// space : 1

// - put elements from larger to smaller into A1
class Solution {
    public void merge(int[] A1, int m, int[] A2, int n) {
        int i = m - 1;
        int j = n - 1;
        int k = m + n - 1;
        while(j >= 0 && i >= 0){
            if(A1[i] > A2[j]){
                A1[k--] = A1[i--];
            }else{
                A1[k--] = A2[j--];
            }
        }
        while(j >= 0){
            A1[k--] = A2[j--];
        }
        while(i >= 0){
            A1[k--] = A1[i--];
        }
    }
}
