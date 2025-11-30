// if (a + b) % k == 0  -->  a%k + b%k == 0  -->  modA + modB = 0  -->  modA = k - modB;
// if nums[i] == modA .... we need to find k-modB in the map.
//    special case .. if modA == 0 ... we will have to find modB == 0 in the map not k - modB.

// if nums[i] finds a pair :
//     - inc the pair count.
//     - dec count of pair of nums[i]... because it made a pair with nums[i].
// if nums[i] don't find a pair :
//    - add this to the map.

class Solution {
    public boolean canArrange(int[] A, int k) {
        int n = A.length;
        int[] map = new int[k];
        int cnt = 0;
        for(int i = 0; i < n ; i++){
            int a = (A[i]%k + k) % k;   // it handles the -ve
            int b = (k - a) % k;   // it handles the special case.
            if(map[b] > 0){
                cnt++;
                map[b]--;
                continue;
            }
            map[a]++;
        }

        return cnt == n/2;
    }
}
