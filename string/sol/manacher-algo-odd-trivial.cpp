#include<bits/stdc++.h>
using namespace std;
typedef long long ll;

vector<int> manacher_odd_trivial(string s) {
    int n = s.size();
    s = "$" + s + "^";
    vector<int> p(n + 2);
    for(int i = 1; i <= n; i++) {
        while(s[i - p[i]] == s[i + p[i]]) {
            p[i]++;
        }
    }
    return vector<int>(begin(p) + 1, end(p) - 1);
}

int main(){
    string s = "adababac";
    vector<int> arr = manacher_odd_trivial(s);
    for(auto it : arr){
        cout << it << ' '; 
    }
}

