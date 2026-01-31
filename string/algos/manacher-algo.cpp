#include<bits/stdc++.h>
using namespace std;
typedef long long ll;

struct Manacher{
    vector<int> p;
    void build(string& s){
        string t = "@";
        for(auto ch : s){
            t.push_back('#');
            t.push_back(ch);
        }
        t.push_back('#');
        t.push_back('$');
        manacher(t);
    }
    void manacher(string& s){
        int n = s.size();
        p.assign(n , 0);
        int l = 0;
        int r = 0;
        for(int i = 1 ; i <= n-2 ; i++){
            p[i] = max(0 , min(r-i , p[l + r - i]));
            while(s[i - p[i]] == s[i + p[i]]){
                p[i]++;
            }
            if(i + p[i] > r){
                l = i - p[i];
                r = i + p[i];
            }
        }
        cout << s << endl;
        for(auto it : p){
            cout << it << ' ';
        }
    }
}m;
int main(){
    string s = "babbabbabc";
    m.build(s);
}

