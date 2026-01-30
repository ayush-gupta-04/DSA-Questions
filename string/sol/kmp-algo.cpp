#include<bits/stdc++.h>
using namespace std;

vector<int> prefix_function(const string &s) {
    int n = (int)s.size();
    vector<int> pi(n);
    for (int i = 1; i < n; i++) {
        int j = pi[i - 1];
        while (j > 0 && s[i] != s[j]) {
            j = pi[j - 1];
        }
        if (s[i] == s[j]) {
            j++;
        }
        pi[i] = j;
    }
    return pi;
}

vector<int> kmp_search(const string &s, const string &t) {
    int n = t.size(), m = s.size();
    vector<int> pi = prefix_function(s);
    vector<int> occurrences;

    for (int i = 0, j = 0; i < n; i++) {
        while (j > 0 && t[i] != s[j]) {
            j = pi[j - 1];
        }
        if (t[i] == s[j]) {
            j++;
        }
        if (j == m) {
            occurrences.push_back(i - m + 1);
            j = pi[j - 1];  // continue searching
        }
    }
    return occurrences;
}


int main(){
    string s = "aba";
    string text = "cdabcabababanaba";
    vector<int> ans = kmp_search(s, text);
    for(auto it : ans){
        cout << it << ' ';
    }
}

