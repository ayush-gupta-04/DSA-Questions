// time : N + M
// space : 1

class Solution {
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int i = 0;
        int j = 0;
        int count = 0;
        while(i < g.length && j < s.length){
            if(g[i] <= s[j]){    // if can assign.  move i
                count++;
                i++;
            }
            j++;    // find some big cookie.
        }
        return count;
    }
}
