// If i take less end time interval first .. then there will be enough room for more inervals to fit.
// sort according to the end time.
class Solution {
public:

    int findLongestChain(vector<vector<int>>& pairs) {
        int n = pairs.size();
        sort(pairs.begin() , pairs.end() , [](auto& a , auto& b){
            return a[1] < b[1];
        });

        for(auto it : pairs){
        for(auto i : it){
            cout << i << ' ';
        }
        cout << endl;
    }
        int cnt = 1;
        int last = 0;
        for(int i = 1 ;i < n; i++){
            if(pairs[i][0] > pairs[last][1]){
                cnt++;
                last = i;
            }
        }
        return cnt;
    }
};
