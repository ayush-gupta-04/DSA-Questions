// TC = O(N)
// SC = O(26)
class Solution {
public:
    bool isAnagram(string s, string t) {
        if(s.size() != t.size()) return false;
        vector<int> mp(26,0);
        int n = s.size();
        for(int i = 0 ; i < n ; i++){
            mp[s[i] - 'a']++;
        }
        for(int i = 0 ; i < n ; i++){
            mp[t[i] - 'a']--;
        }

        for(int i = 0 ; i < 26 ; i++){
            if(mp[i] != 0) return false;
        }
        return true;
        
    }
};
