// TC = O(13)
// SC = O(13)

// for every symbol .. add that while you can
class Solution {
public:
    string intToRoman(int num) {
        vector<pair<int,string>> v = {
            {1000,"M"}, {900,"CM"}, {500,"D"}, {400,"CD"},
            {100,"C"}, {90,"XC"}, {50,"L"}, {40,"XL"},
            {10,"X"}, {9,"IX"}, {5,"V"}, {4,"IV"}, {1,"I"}
        };

        string ans;
        for(auto it : v) {
            int val = it.first;
            string ch = it.second;
            while (num >= val) {
                ans += ch;
                num -= val;
            }
        }
        return ans;
    }
};
