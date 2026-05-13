// Time Complexity: O(4^N * N), where n is the length of the input digits. This is because each digit can map to up to 4 letters, and there are n digits.
// Space Complexity: O(N), where n is the length of the input digits. This is due to the recursion stack depth.

class Solution {    
    List<String> ans = new ArrayList<>();

    void fun(String digits , int i , String[] map , StringBuilder p){
        if(i == digits.length()){
            ans.add(p.toString());
            return;
        }   

        String mapping = map[digits.charAt(i)-'0'];
        for(char m : mapping.toCharArray()){
            p.append(m);
            fun(digits , i + 1 , map , p);
            p.deleteCharAt(p.length()-1);
        }
        return;
    }
    public List<String> letterCombinations(String digits) {
        String[] map = new String[]{"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        fun(digits, 0,map,new StringBuilder());
        return ans;
    }
}
