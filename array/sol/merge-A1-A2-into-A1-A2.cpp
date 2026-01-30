#include<bits/stdc++.h>
using namespace std;


int main(){
    vector<int> A1 = {1,2,5,7,9};
    vector<int> A2 = {3,4,5,10};

    int n = A1.size();
    int m = A2.size();
    int i = n-1;
    int j = 0;
    while(i >= 0 && j < m){
        if(A1[i] > A2[j]){
            A1[i] = A1[i] ^ A2[j];
            A2[j] = A1[i] ^ A2[j];
            A1[i] = A1[i] ^ A2[j];
            i--;
            j++;
        }else{
            break;
        }
    }

    sort(A1.begin() , A1.end());
    sort(A2.begin() , A2.end());
    for(auto it : A1){
        cout << it <<' ';
    }
    for(auto it : A2){
        cout << it <<' ';
    }
}
