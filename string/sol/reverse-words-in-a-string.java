// TC = O(N)
// extra SC = O(1)
class Solution {
    public String reverseWords(String s) {
        StringBuilder ans = new StringBuilder();
        int n = s.length();
        int i = n - 1;
        int end = -1;
        
        while (i >= 0) {
            char ch = s.charAt(i);

            if (ch != ' ' && end == -1) {
                end = i;
            }
            else if (ch == ' ' && end != -1) {
                ans.append(s.substring(i + 1, end + 1)).append(" ");
                end = -1;
            }
            i--;
        }
        
        if (end != -1) {
            ans.append(s.substring(0, end + 1)).append(" ");
        }
        
        if (ans.length() > 0) {
            ans.deleteCharAt(ans.length()-1);
        }
        return ans.toString();
    }
}
