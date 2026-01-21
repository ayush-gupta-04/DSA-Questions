#include<bits/stdc++.h>
using namespace std;

void putInCorrectPlace(stack<int>& st , int ele){
    if(st.empty() || st.top() >= ele){
        st.push(ele);
        return;
    }
    int a = st.top();
    st.pop();
    putInCorrectPlace(st,ele);
    st.push(a);
}
void sort(stack<int>& st){
    if(st.empty()){
        return;
    }
    int ele = st.top();
    st.pop();
    // sort the remaining stack;
    sort(st);

    // put the element to it's correct place;
    putInCorrectPlace(st , ele);
}

int main(){
    stack<int> st;
    st.push(2);
    st.push(1);
    st.push(4);
    st.push(3);
    st.push(5);
    stack<int> st1 = st;
    sort(st);
    cout << "Before Sorting !\n";
    while(!st1.empty()){
        cout << st1.top() << ' ';
        st1.pop();
    }

    cout << "After Sorting !\n";
    while(!st.empty()){
        cout << st.top() << ' ';
        st.pop();
    }

}

