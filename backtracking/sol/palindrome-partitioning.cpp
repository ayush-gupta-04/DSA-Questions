class Solution {
public:
    vector<vector<string>> result;
    bool isPalindrome(string& str , int s, int e){
        while(s <= e){
            if(str[s] != str[e]) return false;
            s++;
            e--;
        }
        return true;
    }
    void fun(string str , vector<string>& vec , int i){
        if(i == str.size()){
            result.push_back(vec);
            return;
        }

        for(int idx = i ; idx < str.size() ;idx++){
            if(!isPalindrome(str,i,idx)) continue;
            vec.push_back(str.substr(i , idx - i + 1));
            fun(str , vec , idx + 1);
            vec.pop_back();
        }
    }
    vector<vector<string>> partition(string s) {
        vector<string> vec;
        fun(s , vec , 0);
        return result;
    }
};
