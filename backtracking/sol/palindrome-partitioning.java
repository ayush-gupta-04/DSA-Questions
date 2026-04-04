class Solution {
    boolean isPalindrome(String str , int i , int j){
        while(i < j){
            if(str.charAt(i) == str.charAt(j)){
                i++;
                j--;
            }
            else return false;
        }
        return true;
    }
    void fun(String s , int i, List<String> list ,List<List<String>> ans){
        if(i == s.length()){
            ans.add(new ArrayList<>(list));
            return;
        }

        for(int k = i ; k < s.length() ; k++){
            if(!isPalindrome(s,i,k)) continue;

            list.add(s.substring(i , k+1));
            fun(s,k+1,list,ans);
            list.removeLast();
        }
        return;
    }
    public List<List<String>> partition(String s) {
        List<List<String>> ans = new ArrayList<>();
        List<String> list = new ArrayList<>();
        fun(s,0,list,ans);
        return ans;
    }   
}
