// this is a classic sliding window problem.
// we know that word length is same .. therefore move by k.
// algo :
// -> if a word is unknown .. reset the map .. reset the window.
// -> if a word sub is known .. add it to the map.
// -> if freq of this word sub is greater then we want .. shrink the window until its valid again.
// -> after this .. just check if the l and r covers the required length or not... if yes then push l to res.
class Solution {
public:
    vector<int> findSubstring(string s, vector<string>& words) {
        map<string , int> mainMap;
        for(auto& w : words){
            mainMap[w]++;
        }

        int n = s.size();
        int m = words.size();
        int k = words[0].size();

        int i= 0;
        vector<int> res;
        while(i < k){
            int l = i;
            int r = i;
            map<string,int> map;

            while(r <= n - k){
                string sub = s.substr(r,k);
                if(mainMap.find(sub) == mainMap.end()){
                    // reset map;
                    map.clear();
                    r += k;
                    l = r;
                    continue;
                }
                map[sub]++;
                while(l <= r && map[sub] > mainMap[sub]){
                    string leftSub = s.substr(l,k);
                    map[leftSub]--;
                    if(map[leftSub] == 0) map.erase(leftSub);
                    l += k;
                }

                if(r+k - l == (k*m)){
                    res.push_back(l);
                }
                r += k;
            }
            i++;
        }
        return res;
    }
};
