#include<bits/stdc++.h>
using namespace std;

bool isOperand(char ch){
    return ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z') || (ch >= '0' && ch <= '9'));
}
string postfixToInfix(string& exp){
    stack<string> st;

    for(int i = 0 ; i < exp.size() ; i++){
        if(isOperand(exp[i])) st.push(string(1,exp[i]));
        else{
            string b = st.top();
            st.pop();
            string a = st.top();
            st.pop();
            st.push(string(1,exp[i]) + a + b);
        }
    }
    string ans = st.top();
    st.pop();
    return ans;
}


int main() {
    string exp = "AB-DE+F*/";
    cout << postfixToInfix(exp);
    return 0;
}
