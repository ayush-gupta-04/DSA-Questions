class Solution {
public:

    vector<vector<int>> result;
    void fun(int n, int k, vector<int> vec , int i){
        if(n == 0 && k == 0){
            result.push_back(vec);
            return;
        }
        if(n == 0 || k == 0 || i > 9) return;

        // not take.
        fun(n , k  , vec , i + 1);

        // take only if i can.
        if(k > 0 && n >= i){
            vec.push_back(i);
            fun(n - i , k - 1,vec , i + 1);
            vec.pop_back();
        }
        return;
    }
    vector<vector<int>> combinationSum3(int k, int n) {
        vector<int> vec;
        fun(n,k,vec ,1);
        return result;
    }
};
