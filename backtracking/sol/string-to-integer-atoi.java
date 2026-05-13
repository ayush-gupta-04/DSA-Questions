// Time Complexity: O(n) since each character is processed once.
// Space Complexity: O(n) since the recursion stack grows up to n calls.
class Solution {
    int fun(int i , int sign , long num ,String s){
        if(i >= s.length() || !Character.isDigit(s.charAt(i))){
            return (int)(sign*num);
        }

        num = num*10 + (s.charAt(i)-'0');

        if(sign*num >= Integer.MAX_VALUE) return Integer.MAX_VALUE;
        if(sign*num <= Integer.MIN_VALUE) return Integer.MIN_VALUE;

        return fun(i + 1 , sign , num,s);
    }
    public int myAtoi(String s) {
        int i = 0;
        int n = s.length();

        while(i < n && s.charAt(i) == ' ') i++;
        int sign = 1;
        if(i < n && (s.charAt(i) == '+' || s.charAt(i) == '-')){
            sign = (s.charAt(i) == '+') ? 1 : -1;
            i++;
        }
        return fun(i,sign,0L,s);
    }
}
