// change the question a little bit.
// for an index 'i' , let the number of starting points be 'x' which would contribute to score at 'i'.
// and i need to find ans for every 'i'.

// obv -> _ , _ , _ , _ , _ , _   // start is j and end is i.
//            j       i
//     -> now : hp - (dam[j] + dam[j+1] .. dam[i-1]) >= req[i]
//     ->       hp - (p[i] - p[j-1]) >= req[i]
//     ->       p[j-1] >= p[i] + req[i] - hp.      // separate i and j.
//     ->       val >= p[i] + req[i] - hp.

//     -> from 0 -> i-1 ... i need to find number of indexed such that prefix sum is >= val.
//     -> since i need to find in the prefix sum array.... prefix sum is sorted ..
///    -> therefore i will try to find the first index 'idx' such that pre[idx] >= val...the lower bound.
//     -> my ans would be score += (i - idx) ... [idx . . . i-1].


//     -> an edge case : i need to have 0 in my prefix sum initially.
//





class Solution {
public:

    int bs(vector<long>& pre , int k , int low, int high){
        int n = pre.size();
        int s = low;
        int e = high;
        while(s <= e){
            int m = s + (e - s)/2;
            if(k > pre[m]){
                s = m + 1;
            }else{
                e = m - 1;
            }
        }
        return s == high + 1 ? -1 : s;
    }
    long long totalScore(int hp, vector<int>& dam, vector<int>& req) {
        int n = dam.size();
        vector<long> pre(n + 1 , 0);
        pre[0] = 0;
        for(int i = 1 ; i <= n ; i++){
            pre[i] = pre[i-1] + dam[i-1];
        }

        long long sc = 0;
        for(int i = 1; i <= n ; i++){
            int val = req[i-1] + pre[i] - hp;
            int idx = bs(pre , val , 0 , i-1);
            if(idx != -1){
                sc += (i - idx);
            }
        }
        return sc;

    }
};
