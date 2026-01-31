// ---------------------- BRUTE FORCE ------------------------------
// TC = O(N^2)
// SC = O(1).
class Solution {
public:
    bool rotateString(string s, string goal) {
        if (s.size() != goal.size()) return false;
        int n = s.size();

        for (int start = 0; start < n; start++) {
            int k = 0;
            while (k < n && s[(start + k) % n] == goal[k])
                k++;
            if (k == n) return true;
        }
        return false;
    }
};

// ----------------------- OPTIMAL --------------------------------

// TC = O(N)
// SC = O(N).
class Solution {
public:
    bool rotateString(string s, string goal) {
        if (s.length() != goal.length()) return false;
        string doubledS = s + s;
        return doubledS.find(goal) != string::npos;
    }
};
