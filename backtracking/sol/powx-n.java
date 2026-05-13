// TC -> logN
// SC -> logN
class Solution {
    double pow(double x , long n){
        if(n == 1) return x;
        double med = pow(x , n/2);

        if(n%2 == 1){
            return med*med*x;
        }
        return med*med;
    }
    public double myPow(double x, int n) {
        if(n == 0) return 1;


        long N = n;
        if(N < 0){
            x = 1L/x;
            N = -N;   // if n was -2^31 and we make it +ve .. it won't fit in integer.. so we took long for n.
        }

        return pow(x,N);
    }
}
