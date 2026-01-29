class Solution {
public:
    vector<int> singleNumber(vector<int>& nums) {
        typedef long long ll;
        ll xr = 0;
        for(auto &it : nums){
            xr = xr ^ it;
        }

        ll x = (xr & (xr-1))^xr; // it will let only the rightmost set bit on.

        ll b1 = 0;  // for 1;
        ll b2 = 0;  // for 0;

        for(auto it : nums){
            if((it&x) == 0){
                // it has rightmost bit off.
                b2 = b2 ^ it;
            }else{
                b1 = b1 ^ it;
            }
        }
        return {(int)b1, (int)b2};
    }
};
