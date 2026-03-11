// TC = O(N);
// SC = O(N) for storing ans.
class Solution {
    public String removeOuterParentheses(String s) {
        int cnt = 0;
        StringBuilder sb = new StringBuilder(); 
        for(int i = 0;i < s.length() ; i++){
            char ch = s.charAt(i);
            if(ch == '(')cnt++;
            else cnt--;

            if(ch == '(' && cnt > 1) sb.append(ch);
            if(ch == ')' && cnt > 0) sb.append(ch);
        }
        return sb.toString();
    }
}
