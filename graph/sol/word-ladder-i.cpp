
class Solution {
public:
    int ladderLength(string begin, string end, vector<string>& list) {
        int n = list.size();

        unordered_set<string> st;
        unordered_set<string> vis;
        queue<pair<string,int>> q;

        for(auto it : list) st.insert(it);

        if(st.find(end) == st.end()) return 0;

        q.push({begin,1});
        vis.insert(begin);

        while(!q.empty()){
            string node = q.front().first;
            int dist = q.front().second;
            q.pop();

            if(node == end) return dist;


            // for next word .. i will try to insert every alphabet from (a -> z) in every pos of node.
            // and if the newWord exist in the list.. i will add this to q , and mark visited.
            for(int i = 0; i < node.size() ; i++){
                for(int k = 97 ; k  <= 122 ; k++){
                    char ch = (char)k;
                    string newWord = node;
                    newWord[i] = ch;
                    if(st.count(newWord) == 1 && vis.count(newWord) == 0){
                        q.push({newWord , dist + 1});
                        vis.insert(newWord);
                    }
                }
            }

        } 

        return 0;
    }
};
