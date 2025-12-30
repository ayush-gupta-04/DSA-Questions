// My first Solution : 
// fun(curr, next, i);
// flaws :
// 1. My recursion is doing 2 things at once. WHICH IS WORSE !!
//    a. Building the next row.
//    b. Answering the question "Can i build to the top with this curr row" ?
//    Why BAD ?
//    -> Memoisation will be difficult.
//    -> The logic will become very complex.
// 2. Changing parameters are 3.
//    WHY BAD ??
//    -> Memoisation will be difficult.

// IDEALLY :
// 1. My recursion should and only one question . "Given this curr row can i go to the top ??".
// 2. My states must be minimal .. here only "curr row".
//    -> i will generate all the next rows recursively and iterate over it.
// 3. If a current row doesn't lead to the top .. i will mark it as false.


class Solution {
public:
    unordered_map<string, vector<char>> mp;
    unordered_map<string, bool> memo;

    bool dfs(string row) {
        if (row.size() == 1) return true;

        if (memo.count(row)) return memo[row];

        string cur;
        vector<string> nextRows;
        buildNextRows(row, 0, cur, nextRows);

        for (auto &next : nextRows) {
            if (dfs(next)) {
                return memo[row] = true;
            }
        }

        return memo[row] = false;
    }

    void buildNextRows(string &row, int idx, string &cur, vector<string> &nextRows) {
        if (idx == row.size() - 1) {
            nextRows.push_back(cur);
            return;
        }

        string key = row.substr(idx, 2);
        if (!mp.count(key)) return;

        for (char ch : mp[key]) {
            cur.push_back(ch);
            buildNextRows(row, idx + 1, cur, nextRows);
            cur.pop_back();
        }
    }

    bool pyramidTransition(string bottom, vector<string>& allowed) {
        for (auto &a : allowed) {
            mp[a.substr(0, 2)].push_back(a[2]);
        }
        return dfs(bottom);
    }
};
