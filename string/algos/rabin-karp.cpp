#include<bits/stdc++.h>
using namespace std;
typedef long long ll;


int main(){
    string s = "aba";
    string t = "ababacdabababab";

    int n = s.size();
    int m = t.size();
    const int p = 31;
    const int mod = 1e9 + 9;

    // precompute the powers of p.
    vector<ll> p_pow(m);
    p_pow[0] = 1;
    for(int i =1;i < m ; i++){
        p_pow[i] = (p_pow[i-1] * p)%mod;
    }

    // calc the prefix hash of the text;
    vector<ll> prefixHash(m);
    prefixHash[0] = t[0]- 'a' + 1;

    for(int i = 1;i < m ; i++){
        prefixHash[i] = (prefixHash[i-1] + (t[i] - 'a' + 1)*p_pow[i])%mod;
    }


    // calc the hash of string s.
    ll hash_s = 0;
    for(int i = 0;i < n ; i++){
        hash_s = (hash_s + (s[i] - 'a' + 1)*p_pow[i])%mod;
    }

    
    vector<int> ans;

    for(int i = 0; i + n - 1 < m; i++){
        ll window_hash = (prefixHash[i + n - 1] - (i-1 >= 0 ? prefixHash[i-1] : 0) + mod)%mod;
        if(window_hash == (hash_s*p_pow[i])%mod){
            ans.push_back(i);
        }
    }

    for(auto it : ans){
        cout << it << ' ';
    }
}

