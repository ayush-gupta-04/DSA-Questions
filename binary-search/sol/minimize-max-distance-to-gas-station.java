// time : N * log(max / e)
//   - N for array traversal.
//   - max : max diff between consecutive.
//   - e : limit = 1o^(-6)

// space : 1

class Solution {
    int calcGas(int[] nums,double x){
        int n= nums.length;
        int cnt = 0;
        for(int i = 0;i <= n-2 ;i++){
            double dist = (nums[i+1]-nums[i])/x;
            if(Math.floor(dist) == Math.ceil(dist)){
                dist--;
            }
            cnt += (int)(Math.floor(dist));
        }
        return cnt;
    }
    public double minMaxDist(int[] nums, int k) {
        double s = 0;
        double e = nums[0];
        for(int a : nums) e = Math.max(a , e);
        
        while(e-s > 1e-6){
            double m = s + (e-s)/2;
            int gas = calcGas(nums , m);
            if(gas > k){
                s = m;
            }else{
                e = m;
            }
        }
        
        return s;
    }
}
