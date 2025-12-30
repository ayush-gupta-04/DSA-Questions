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

    bool funRecur(int i , const string& str , Trie* t){
        if (i == str.size()) return true;

        for (int k = i; k < str.size(); k++) {
            string pre = str.substr(i, k - i + 1);

            // i know if pre == str .. then pre will be in dict but i don't want to consider a whole word.
            // i want to consider a word which is min of 2 words concatenation.
            if (pre.size() == str.size() || !t->contains(pre)) continue;

            if(funRecur(k + 1, str, t)) return true;
        }
        return false;
    }


    bool funDp(int i , const string& str , Trie* t){
        int n = str.size();
        vector<bool> dp(n+1 , 0);
        dp[n] = true;

        for(int i = n-1; i>= 0;i--){
            bool ans = false;
            for (int k = i; k < str.size(); k++) {
                string pre = str.substr(i, k - i + 1);
                if (pre.size() == str.size() || !t->contains(pre)) continue;
                
                if(dp[k+1]){
                    ans = true;
                    break;
                }
            }
            dp[i] = ans;
        }

        return dp[0];
    }
    vector<string> findAllConcatenatedWordsInADict(vector<string>& words) {
        int n = words.size();
        Trie* t = new Trie();
        for(auto& word : words){
            t->insert(word);
        }
        vector<string> res;

        for(auto& it : words){
            if(funDp(0,it , t)) res.push_back(it);
        }
        return res;
    }
};
