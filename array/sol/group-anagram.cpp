// ----------------- Approach-1-----------------------
// TC - TLE - O(N^2 . L)
// SC - O(N) + O(26).
// make the ans on the run.
// if i can group a str in a list .. i will push it .. otherwise make a new list for it.

class Solution {
public:
    bool isAnagram(string& a , string& b){
        vector<int> mp(26, 0);
        for(auto& c : a){
            mp[c - 'a']++;
        }
        for(auto& c : b){
            mp[c - 'a']--;
        }

        for(int i = 0;i < 26;i++){
            if(mp[i] != 0) return false;
        }
        return true;

    }
    vector<vector<string>> groupAnagrams(vector<string>& strs) {
        vector<vector<string>> vec;
        for(auto& str : strs){
            int flag = 0;
            for(int i = 0; i < vec.size() ; i++){
                if(isAnagram(vec[i].back() , str)){
                    vec[i].push_back(str);
                    flag = 1;
                    break;
                }
            }
            if(!flag){
                vec.push_back({str});
            }
        }
        return vec;
    }   
};



// ----------------------- OPTIMAL ---------------------
// TC - O(N*K)[temp key] + O(N*logN)[map search]  .. N size of vec .. K size of a str.
// SC - O(N)[map] + O(N)[temp key].


// To Group every anagram .. we need a key.
// Key should have a common property for all anagram of same group.
// If we sort every str of a group .. they will be same.
// ex . eat, tea -> they would become aet.
// sorting the string can become my key.

class Solution {
public:
    vector<vector<string>> groupAnagrams(vector<string>& strs) {
        map<string,vector<string>> map;
        for(const auto& str : strs){
            string sortedStr = str;
            sort(sortedStr.begin(),sortedStr.end());
            map[sortedStr].push_back(str);
        }
        vector<vector<string>> vec;
        for(auto& it : map){
            vec.push_back(it.second);
        }
        return vec;
    }   
};
