#include<bits/stdc++.h>
using namespace std;

int priority(char c){
    if(c == '^') return 3;
    if(c == '*' || c == '/') return 2;
    if(c == '+' || c == '-') return 1;
    return -1;
}

bool isRightAssociative(char& ch){
    return ch == '^';
}

bool isOperand(char ch){
    return ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z') || (ch >= '0' && ch <= '9'));
}
string infixToPostfix(string& exp){
    string ans;
    stack<char> st;

    for(auto& c : exp){
        if(isOperand(c)) ans += c;
        else if(c == '(') st.push(c);
        else if(c == ')'){
            while(!st.empty() && st.top() != '('){
                ans += st.top();
                st.pop();
            }
            if(!st.empty()) st.pop();
        }
        else{
            while(!st.empty() && st.top() != '(' && (
                priority(st.top()) > priority(c) || (priority(st.top()) == priority(c) && !isRightAssociative(c))
            )){
                ans += st.top();
                st.pop();
            }
            st.push(c);
        }
    }

    // Pop remaining operators
    while (!st.empty()) {
        ans += st.top();
        st.pop();
    }

    return ans;
}



int main() {
    string exp = "f+d-c*(b+a)";
    cout << infixToPostfix(exp);
    return 0;
}
