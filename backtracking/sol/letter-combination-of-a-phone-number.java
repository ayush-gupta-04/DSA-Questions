
class Solution {
    List<String> fun(String digits , int i , Map<Character , String> map , StringBuilder p){
        List<String> ans = new ArrayList<>();
        if(i == digits.length()){
            ans.add(p.toString());
            return ans;
        }   

        String mapping = map.get(digits.charAt(i));
        for(char m : mapping.toCharArray()){
            p.append(m);
            ans.addAll(fun(digits , i + 1 , map , p));
            p.deleteCharAt(p.length()-1);
        }
        return ans;
    }
    public List<String> letterCombinations(String digits) {
        Map<Character , String> map = Map.of(
            '2' , "abc",
            '3' , "def",
            '4' , "ghi",
            '5' , "jkl",
            '6' , "mno",
            '7' , "pqrs",
            '8' , "tuv",
            '9' , "wxyz"
        );
        return fun(digits, 0,map,new StringBuilder());
    }
}
