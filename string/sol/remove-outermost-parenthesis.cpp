// TC = O(N);
// SC = O(N) for storing ans.
class Solution {
public:
    string removeOuterParentheses(string s) {
        int c = 0;
        string ans;
        for(char ch : s){
            if(ch == '('){
                c++;
                if(c > 1) ans.push_back(ch);
            }else{
                c--;
                if(c > 0) ans.push_back(ch);
            }
        }
        return ans;
    }
};
