class Solution {
public:
    vector<string> result;
    void solve(int n, string curr, int open, int close) {
        if(curr.length() == 2*n) {
            result.push_back(curr);
            return;
        }
        
        if(open < n) {
            curr.push_back('(');
            solve(n, curr, open+1, close);
            curr.pop_back();
        }

        // we can only put a close bracket ifff close < open.
        // if we add a close bracket when open <= close .. 
        //    then we will add an extra close bracket..jiska opening bracket hoga hi nhi...so we won't add it.
        if(close < open) {
            curr.push_back(')');
            solve(n, curr, open, close+1);
            curr.pop_back();
        }
    }
    vector<string> generateParenthesis(int n) {
        string curr = "";
        solve(n, curr, 0, 0);
        return result;
    }
};
