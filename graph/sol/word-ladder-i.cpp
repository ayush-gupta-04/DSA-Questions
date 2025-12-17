
class Solution {
public:
    int ladderLength(string startWord, string endWord, vector<string>& wordList) {
        // Queue for BFS storing {current word, steps taken}
        queue<pair<string, int>> q;
        q.push({startWord, 1});

        // Set for quick lookup and deletion
        unordered_set<string> st(wordList.begin(), wordList.end());
        st.erase(startWord);

        while (!q.empty()) {
            string word = q.front().first;
            int steps = q.front().second;
            q.pop();

            // If target word is found, return steps
            if (word == endWord) return steps;

            // Try changing every character in the current word
            for (int i = 0; i < word.size(); i++) {
                char original = word[i];
                for (char ch = 'a'; ch <= 'z'; ch++) {
                    word[i] = ch;
                    if (st.find(word) != st.end()) {
                        st.erase(word);
                        q.push({word, steps + 1});
                    }
                }
                word[i] = original;
            }
        }
        // If no sequence exists
        return 0;
    }
};
