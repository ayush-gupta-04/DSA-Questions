// TC = O(N)
// extra SC = O(2*256)
class Solution {
public:
    bool isIsomorphic(string s, string t) {
        if(s.size() != t.size()) return false;
        int n = s.size();
        vector<int> mp1(255 , -1);
        vector<int> mp2(255 , -1); 
        for(int i = 0; i< n;i++){
            char u = s[i];
            char v = t[i];
            // map u -> v. 
            if(mp1[u] != -1 && mp1[u] != v) return false;
            if(mp2[v] != -1 && mp2[v] != u) return false;
            
            mp1[u] = v;
            mp2[v] = u;
        }
        return true;
    }
};
