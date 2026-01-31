// -------------- CODE -----------------
// TC = O(m+n) at worst case
// SC = O(m+n)
#include<bits/stdc++.h>
using namespace std;


int main(){
   vector<int> A1 = {1,2,3,3,4,5,6,6,7,7,7,7};
   vector<int> A2 = {2,2,2,3,4,6,10,12,13};

   int n = A1.size();
   int m = A2.size();
   int i = 0;
   int j = 0;
   vector<int> un;
   while(i < n && j < m){
        if(A1[i] < A2[j]){
            // do not push duplicates.
            if(un.empty() || un.back() != A1[i]){
                un.push_back(A1[i]);
            }
            i++;
        }else if(A1[i] > A2[j]){
            // do not push duplicates.
            if(un.empty() || un.back() != A2[j]){
                un.push_back(A2[j]);
            }
            j++;
        }else{
            // do not push duplicates.
            if(un.empty() || un.back() != A2[j]){
                un.push_back(A2[j]);
            }
            j++;i++;
        }
   }

   while(i < n){
        // do not push duplicates.
        if(un.empty() || un.back() != A1[i]){
            un.push_back(A1[i]);
        }
        i++;
   }
   while(j < m){
        // do not push duplicates.
        if(un.empty() || un.back() != A2[j]){
            un.push_back(A2[j]);
        }
        j++;
   }

   for(auto it : un){
    cout << it << ' ';
   }
}

