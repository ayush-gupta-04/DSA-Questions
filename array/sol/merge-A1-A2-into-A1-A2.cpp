// --------------------- Brute-Force Approach ---------------------------
// TC = min(m + n) + O(mlogm) + O(nlogn).
// SC = O(1)
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


// ------------------- Optimal-Approach------------------------------------
// TC = (m + n)log(m + n)
// SC = O(1)


#include<bits/stdc++.h>
using namespace std;

void _swap(vector<int>& A1 , vector<int>& A2 , int i , int j){
    int temp = A1[i];
    A1[i] = A2[j];
    A2[j] = temp;
}

int main(){
    vector<int> A1 = {1,2,5,7,9};
    vector<int> A2 = {3,4,5,10};

    int n = A1.size();
    int m = A2.size();
    int gap = (m + n)/2 + (m + n)%2;

    while(true){
        int i = 0;
        int j = gap;
        while(j < (m + n)){
            int a = (i < n ? A1[i] : A2[i - n]);
            int b = (j < n ? A1[j] : A2[j - n]);

            if(a > b){
                // swap a and b.
                if(i < n && j < n) _swap(A1,A1,i,j);
                else if(i < n && j >= n) _swap(A1,A2,i,j-n);
                else _swap(A2,A2,i-n,j-n);
            }
            i++;
            j++;
        }
        if(gap == 1) break;
        gap = (gap)/2 + gap%2;
    }

    for(auto it : A1){
        cout << it << ' ';
    }
    for(auto it : A2){
        cout << it << ' ';
    }
}


