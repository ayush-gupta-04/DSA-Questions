// Time Complexity: O(2^n) (Catalan number): C(n) = (2n)! / (n!(n+1)!) is the number of valid sequences.
// Each sequence takes O(n) to build.
// So, total complexity: O(C(n) × n)

// Space Complexity: O(n) recursion depth.
// O(C(n) × n) to store results.

import java.util.*;

class Solution {

    List<String> result = new ArrayList<>();

    void solve(int n, StringBuilder curr, int open, int close) {

        if (curr.length() == 2 * n) {
            result.add(curr.toString());
            return;
        }

        if (open < n) {
            curr.append('(');
            solve(n, curr, open + 1, close);
            curr.deleteCharAt(curr.length() - 1);
        }

        // we can only put a close bracket ifff close < open.
        // if we add a close bracket when open <= close .. 
        //    then we will add an extra close bracket..jiska opening bracket hoga hi nhi...so we won't add it.
        if (close < open) {
            curr.append(')');
            solve(n, curr, open, close + 1);
            curr.deleteCharAt(curr.length() - 1);
        }
    }

    public List<String> generateParenthesis(int n) {

        StringBuilder curr = new StringBuilder();

        solve(n, curr, 0, 0);

        return result;
    }
}
