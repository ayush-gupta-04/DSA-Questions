// TC - N -> Single pass
// SC - 1
class Solution {
    public int numberOfSubstrings(String s) {
        int a = -1;
        int b = -1;
        int c = -1;
        int i = 0;
        int count =0;
        while(i < s.length()){
            if(s.charAt(i) == 'a'){
                a = i;
            }else if(s.charAt(i) == 'b'){
                b = i;
            }else{
                c = i;
            }
            int min = Math.min(a , Math.min(b , c));
            if(min != -1){
                count = count + min + 1;
            }
            i++;
        }
        return count;
    }
}
