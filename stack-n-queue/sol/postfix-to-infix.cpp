#include<bits/stdc++.h>
using namespace std;

bool isOperand(char ch){
    return ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z') || (ch >= '0' && ch <= '9'));
}
string postfixToInfix(string& exp){
    stack<string> st;

    for(auto& c : exp){
        if(isOperand(c)) st.push(string(1,c));
        else{
            string b = st.top();
            st.pop();
            string a = st.top();
            st.pop();
            st.push("(" + a + string(1,c) + b + ")");
        }
    }
    string ans = st.top();
    st.pop();
    return ans;
}


int main() {
    string exp = "abc^^def-*+";
    cout << postfixToInfix(exp);
    return 0;
}
