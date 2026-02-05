// ------------------------------------- DP -----------------------------
// TC -> O(N²)
// SC -> O(N²)

// for dp :
// I can either paste the old copied .. or i can just copy ans paste.
// then memoise
class Solution {
public:
    int fun(int p , int c, int n){
        if(p == n) return 0;
        int cpyPst = 1e9;
        int pst = 1e9;
        if(2*p <= n){
            cpyPst = 2 + fun(2*p , p , n);
        }
        if(p+c <= n){
            pst = 1 + fun(p+c , c , n);
        }
        return min(cpyPst,pst);
    }
    int minSteps(int n) {
        return 1 + fun(1,1,n);
    }
};



// ------------------------- MATH --------------------------------
// TC - O(√N)
// SC - O(1)
// Obv :
// 1. if n is prime .. my ans is n.
// 2. if n is even .. ex 20 then i will just repeat 10 2 times.
// 3. if n is odd ... ex 27 then i will just repeat 9 3 times.

// for 100.
// repeat 50 2 times.... ans = 2 + (ans for 50).
// for 50.
// repeat 25 2 times ... ans = 2 + 2 + (ans for 25).
// for 25.
// repeat 5 5 times .... ans = 2 + 2 + 5 + (ans for 5).
// for 5.
// 5 is prime .. ans = 2 + 2 + 5 + 5. ... which is prime factors.


class Solution {
public:
    int minSteps(int n) {
        int ans = 0;
        for(int i = 2; i*i <= n ;i++){
            while(n%i == 0){
                ans += i;
                n = n / i;
            }
        }
        if(n > 1){
            ans += n;
        }
        return ans;
    }
};
