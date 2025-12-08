// use string as a the stack.


class Solution {
public:
    string removeKdigits(string num, int k) {
        string st; // use this as a stack

        for (char c : num) {
            while (!st.empty() && k > 0 && st.back() > c) {
                st.pop_back();
                k--;
            }
            st.push_back(c);
        }

        // If k is still > 0, remove from the end
        while (k > 0 && !st.empty()) {
            st.pop_back();
            k--;
        }

        // Remove leading zeros
        int i = 0;
        int n = st.size();
        while (i < n && st[i] == '0') i++;

        // If all digits removed or are zeros
        if (i == n) return "0";

        // Return the substring from the first non-zero
        return st.substr(i);
    }
};
