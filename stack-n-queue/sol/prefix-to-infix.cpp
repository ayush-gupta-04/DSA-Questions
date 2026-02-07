#include<bits/stdc++.h>
using namespace std;

bool isOperand(char ch){
    return ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z') || (ch >= '0' && ch <= '9'));
}
string postfixToInfix(string& exp){
    stack<string> st;

    for(int i = exp.size()-1 ; i >= 0 ; i--){
        if(isOperand(exp[i])) st.push(string(1,exp[i]));
        else{
            string a = st.top();
            st.pop();
            string b = st.top();
            st.pop();
            st.push("(" + a + string(1,exp[i]) + b + ")");
        }
    }
    string ans = st.top();
    st.pop();
    return ans;
}


int main() {
    string exp = "*+PQ-MN";
    cout << postfixToInfix(exp);
    return 0;
}
