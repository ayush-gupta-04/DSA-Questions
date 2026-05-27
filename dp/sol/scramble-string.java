// solve(s1 , s2) : this answers ... whether any possible scramble of s1 equals s2 ? or not ?
// Thought Process : 
// - there can be multiple possible partitions of s1.
// - s1 = abc|defgh  .. s2 = fgh|abcde
// - s1 = abc|defgh  .. s2 = fghab|cde
//        - if no swap then ... abc will scramble inside to become fgh ...same for right part
//        - if swap then ... abc will scramble inside to become cde(the later part) .. same for right part.


class Solution {
    boolean solve(String s1, String s2,HashMap<String,Boolean> dp){
        if(s1.equals(s2)) return true;
        String key = s1 + ":" + s2;

        if(dp.containsKey(key)) return dp.get(key);

        int n = s1.length();
        for(int i = 1;i < n; i++){
            boolean sw = solve(s1.substring(0,i) , s2.substring(0,i),dp) && solve(s1.substring(i) , s2.substring(i),dp);
            if(sw){
                dp.put(key,true);
                return true;
            }
            boolean nsw = solve(s1.substring(0,i) , s2.substring(n-i),dp) && solve(s1.substring(i) , s2.substring(0,n-i),dp);
            if(nsw){
                dp.put(key,true);
                return true;
            }
        }
        dp.put(key,false);
        return false;
    }
    public boolean isScramble(String s1, String s2) {
        return solve(s1,s2,new HashMap<>());
    }
}
