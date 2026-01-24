// ------------------------------ SOL - 1 --------------------------------------------------------------------------
// Using Trie.

// It is a simple front Partitioning DP.
// here only "i" is required.
// I am iterating 'i' from left -> right.
// When i think substr(0 -> i) is in wordDict ... then i find and for the other half (i+1 , n-1).



class Node {
    vector<Node*> list;
    bool flag;

public:
    Node() : list(26, nullptr), flag(false) {}

    bool contains(char ch) {
        return list[ch - 'a'] != nullptr;
    }

    Node* getRefNode(char ch) {
        return list[ch - 'a'];
    }

    void setRefNode(char ch) {
        list[ch - 'a'] = new Node();
    }

    void setEnd() {
        flag = true;
    }

    bool isSet() {
        return flag;
    }
};


class Trie {
    Node* root;

public:
    Trie() {
        root = new Node();
    }

    void insert(const string& word) {
        Node* node = root;
        for (char ch : word) {
            if (!node->contains(ch)) {
                node->setRefNode(ch);
            }
            node = node->getRefNode(ch);
        }
        node->setEnd();
    }

    bool startsWith(const string& word) {
        Node* node = root;
        for (char ch : word) {
            if (!node->contains(ch)) return false;
            node = node->getRefNode(ch);
        }
        return true;
    }

    bool contains(const string& word) {
        Node* node = root;
        for (char ch : word) {
            if (!node->contains(ch)) return false;
            node = node->getRefNode(ch);
        }
        return node->isSet();
    }
};







class Solution {
public:
    bool fun(int i , const string& str , Trie* t){
        if (i == str.size()) return true;

        for (int k = i; k < str.size(); k++) {
            string pre = str.substr(i, k - i + 1);
            if (!t->contains(pre)) continue;

            if (fun(k + 1, str, t)) return true;
        }
        return false;
    }


// this is dp solution
    bool wordBreak(string s, vector<string>& wordDict) {
        int n = s.size();
        Trie* t = new Trie();
        for(auto& word : wordDict){
            t->insert(word);
        }

        vector<int> dp(n+1 , 0);
        dp[n] = 1;

        for(int i = n - 1 ; i >= 0 ; i--){
            bool ans = false;
            for (int k = i; k < s.size(); k++) {
                string pre = s.substr(i, k - i + 1);
                if (!t->contains(pre)) continue;

                if (dp[k+1]) {
                    ans = true;
                    break;
                }
            }
            dp[i] = ans;
        }
        return dp[0];
    }
};





// ------------------------------------------- SOL - 2 -------------------------------------------------------------------------
// Using DP
