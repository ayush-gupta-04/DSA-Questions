// TC = O(N)
// extra SC = O(1)
class Solution {
public:
    string reverseWords(string s) {
        string ans;
        int n = s.size();
        int i = n-1;
        int end = -1;
        while(i >= 0){
            // if end of word .. mark it ... or just skip it.
            if(s[i] != ' ' && end == -1){
                end = i;
            }  

            // if end of word exist after this... i need to add that word to the ans.
            else if(s[i] == ' ' && end != -1){
                ans += s.substr(i + 1 , end - i);
                ans += " ";
                end = -1;
            }
            i--;
        }
        if(end != -1){
            ans += s.substr(0 , end + 1);
            ans += " ";
        }
        ans.pop_back();
        return ans;
    }
};
