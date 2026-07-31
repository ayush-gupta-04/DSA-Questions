// We maintain a range [minOpen, maxOpen] to represent the minimum and maximum possible open brackets at each point in the string.
// If we see a '(', both minOpen and maxOpen increase by 1.
// If we see a ')', both minOpen and maxOpen decrease by 1.
// If we see '*', it can behave like '(', ')' or be empty:
// minOpen decreases by 1 (assuming '*'' acts like ')')
// maxOpen increases by 1 (assuming '*' acts like '(')
// If at any point maxOpen becomes negative, return false — too many closing brackets.
// We also make sure minOpen doesn’t drop below 0 — because you can't have negative opening brackets.
// If after processing the whole string, minOpen is 0, it means a valid configuration exists.


class Solution {
    public boolean checkValidString(String s) {
        int minOpen = 0;
        int maxOpen = 0;
        for(int i = 0;i < s.length(); i++){
            char ch = s.charAt(i);
            if(ch == '('){
                minOpen += 1;
                maxOpen += 1;
            }else if(ch == ')'){
                minOpen -= 1;
                maxOpen -= 1;
            }else{
                minOpen -= 1;
                maxOpen += 1;
            }

            if(maxOpen < 0) return false;
            if(minOpen < 0) minOpen = 0;
        }
        return minOpen == 0;
    }
}
