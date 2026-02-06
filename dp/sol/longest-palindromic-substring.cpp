// ------------------------ BRUTE_FORCE --------------------
// TC - O(N³)
// SC - O(1)
// 1. Check for every substring if it is palindrome or not.

class Solution {
public:
    bool isPalindrome(string& s, int i , int j){
        if(i >= j) return true;
        if(s[i] == s[j]) return isPalindrome(s,i+1,j-1);
        return false;
    }
    string longestPalindrome(string s) {
        int n = s.size();
        int Max = 0;
        int start = 0;
        
        for(int i = 0;i < n; i++){
            for(int j = i ; j < n; j++){
                if(isPalindrome(s,i,j)){
                    if(j-i + 1 > Max){
                        Max = j - i + 1;
                        start = i;
                    }
                }
            }
        }
        return s.substr(start,Max);
    }
};







// ------------------- BRUTE-FORCE + MEMOISATION ------------------------------
// TC = O(N²)
// SC - O(N²) + O(N)(stack recur space at worst)
// 1. Check for every substring if it is palindrome or not.
// 2. When we were checking the substrings .. we were checking the same substrings multiple times.
//    so meoise it.

class Solution {
public:
    bool isPalindrome(string& s, int i , int j,vector<vector<int>>& dp){
        if(i >= j) return true;
        if(dp[i][j] != -1) return dp[i][j];
        if(s[i] == s[j]) return dp[i][j] = (isPalindrome(s,i+1,j-1,dp) == true) ? 1 : 0;
        return dp[i][j] = false;
    }
    string longestPalindrome(string s) {
        int n = s.size();
        int Max = 0;
        int start = 0;
        vector<vector<int>> dp(n , vector<int>(n , -1));
        for(int i = 0 ;i < n;i++){
            dp[i][i] = 1;
        }


        for(int i = 0;i < n; i++){
            for(int j = i ; j < n; j++){
                if(isPalindrome(s,i,j,dp)){
                    if(j-i + 1 > Max){
                        Max = j - i + 1;
                        start = i;
                    }
                }
            }
        }
        return s.substr(start,Max);
    }
};




// ------------------- BRUTE-FORCE - Expand from the centers  ----------------------
// TC - O(N²)
// SC - O(1)


// A palindrome can have 2 types of centers ..
// 1. odd centers.
// 2. even centers.
// Concept is to expand from the centers .. consider odd and even center diff.
class Solution {
public:
    // returns the size of the palindrome.
    int expand(string& s, int i , int j,int par){
        int cnt = 0;
        int n = s.size();
        while(i >= 0 && j < n && s[i] == s[j]){
            i--;
            j++;
            cnt++;
        }
        if(par == 0){
            return 2*cnt;
        }
        return 2*cnt - 1;
    }
    string longestPalindrome(string s) {
        int n = s.size();
        int Max = 0;
        int start = 0;

        for(int i = 0;i < n; i++){
            int size1 = expand(s,i,i,1); // odd center.
            int size2 = 0;
            if(i <= n-2) size2 = expand(s,i,i+1,0); // even center.
            if(size1 > Max){
                Max = size1;
                start = (i - size1/2);
            }
            if(size2 > Max){
                Max = size2;
                start = (i - (size2-1)/2);
            }
        }
        return s.substr(start,Max);
    }
};





// -------------------- DYNAMIC-PROGRAMMING --------------------------
// TC - O(N²)
// SC - O(N²)


// Since to check a big palindrome , we ultimately needed the smallest palindrome.
// then why don't we build the palindromes from size 1 -> to size n.
// then for size k palindrome we would have stored the information about the size k-1 palindromes.


// A palindrome can have 2 types of centers ..
// 1. odd centers.
// 2. even centers.
// Odd centers of length 1 .. are base case... all odd palindromes expand from here.
// even centers of length 2 .. are base case... all even palindromes expand from here. 

class Solution {
public:
    string longestPalindrome(string s) {
        int n = s.size();
        vector<vector<bool>> dp(n, vector<bool>(n , false));
        int start = 0;
        int Max = 0;


        for(int L = 1; L <= n ; L++){
            for(int i=0,j=L-1; j < n;j++,i++){
                if(L == 1){
                    // Base case of Length 1.
                    dp[i][i] = true;
                }else if(L == 2){
                    // Base case of Length 2.
                    dp[i][j] = (s[i] == s[j]); 
                }else{
                    dp[i][j] = (s[i] == s[j] && dp[i+1][j-1]);
                }


                if(dp[i][j] && (j-i+1) > Max){
                    Max = j - i + 1;
                    start = i;
                }
            }
        }
        return s.substr(start , Max);
    }
};
