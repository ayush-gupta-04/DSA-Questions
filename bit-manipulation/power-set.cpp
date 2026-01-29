#include<bits/stdc++.h>
using namespace std;


int main(){
    int n = 5;
    vector<int> vec = {1,2,3,4,5};
    vector<vector<int>> res;
    for(int mask = 0;mask < (1 << n) ; mask++){
        vector<int> list;   
        for(int i = 0 ;i < n; i++){
            if((mask & (1 << i)) != 0){
                list.push_back(vec[i]);
            }
        }
        res.push_back(list);
    }


    for(auto it : res){
        for(auto i : it){
            cout << i << " ";
        }
        cout << endl;
    }
}

