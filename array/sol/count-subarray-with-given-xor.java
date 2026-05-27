// ------------- Optimal Approach : Using Prefix Xor ---------------
// time : N
// spcae : N

class Solution {
    public long subarrayXor(int nums[], int k) {
        HashMap<Integer,Integer> map = new HashMap<>();
        map.put(0,1);
        int xor = 0;
        int cnt = 0;
        for(int a : nums){
            xor = xor^a;
            int toFind = xor^k;
            cnt += map.getOrDefault(toFind , 0);
            map.put(xor , map.getOrDefault(xor , 0) + 1);
        }
        return cnt;
    }
}
