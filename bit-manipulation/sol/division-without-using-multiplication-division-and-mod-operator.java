// time : logN
// space : 1


// a = 30;
// b = 4;
// 30 = 4*q + r
// 30 = 4*7 + 2
// 30 = 4*(2^2) + 4*(2^1) + 4*(2^0) + 2;
//    = 4*4 + 4*2 + 4*1 + 2
//    = 16  + 8   + 4   + 2

class Solution {
    public int divide(int A, int B) {
        if(A == B) return 1;
        if (A == Integer.MIN_VALUE && B == -1) return Integer.MAX_VALUE;
        if (B == 1) return A;
            

        // The sign will be negative only if sign of 
        // divisor and dividend are different
        int sign = ((A < 0) ^ (B < 0)) ? -1 : 1;

        long a = Math.abs((long)A);
        long b = Math.abs((long)B);

        int q = 0;


        for (int i = 31; i >= 0; --i) {

            // Check if (divisor << i) <= dividend
            if ((b << i) <= a) {
                a -= (b << i);
                q |= (1 << i);
            }
        }

        return sign * q;
    }
}
