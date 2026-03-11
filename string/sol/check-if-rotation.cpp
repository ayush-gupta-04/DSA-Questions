// ---------------------- BRUTE FORCE ------------------------------
// TC = O(N^2)
// SC = O(1).
class Solution {
    public boolean rotateString(String s, String goal) {
        if(s.length() != goal.length()) return false;

        int n = s.length();
        for(int i = 0;i < n; i++){
            int k = 0;
            while(k < n && s.charAt((i+k)%n) == goal.charAt(k)){
                k++;
            }
            if(k == n) return true;
        }
        return false;
    }
}

// ----------------------- OPTIMAL --------------------------------

// TC = O(N)
// SC = O(N).
class Solution {
    public boolean rotateString(String s, String goal) {
        if(s.length() != goal.length()) return false;
        String d = s + s;
        return d.indexOf(goal) != -1;
    }
}
