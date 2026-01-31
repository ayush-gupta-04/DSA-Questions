// TC = (NlogN * L) ... N = size of strs .... L = avg size of strs[i].
// extra SC = O(1).
class Solution {
public:
    string longestCommonPrefix(vector<string>& strs) {
        sort(strs.begin() , strs.end());
        string first = strs[0];
        string last = strs[strs.size() - 1];
        string ans = "";
        for(int i = 0; i < min(first.size(),last.size()) ; i++){
            if(first[i] != last[i]){
                break;
            }
            ans += first[i];
        }
        return ans;
    }
};
