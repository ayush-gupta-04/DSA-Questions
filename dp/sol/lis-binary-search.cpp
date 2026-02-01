#include<bits/stdc++.h>
using namespace std;


int findLIS(vector<int>& vec){
    vector<int> nums;
   for(int i = 0;i < vec.size() ; i++){
        // if i can put vec[i] at the back .. then put.
        // otherwise find lower_bound of vec[i] in nums and replace it.
        if(nums.empty() || vec[i] > nums.back()){
            nums.push_back(vec[i]);
        }else{
            int lb  = lower_bound(nums.begin() , nums.end() , vec[i]) - nums.begin();
            nums[lb] = vec[i];
        }
   }
   return nums.size();
}

int main(){
   vector<int> vec = {1,7,8,4,5,6,-1,9};
    int lis = findLIS(vec);
    cout << lis;
}


