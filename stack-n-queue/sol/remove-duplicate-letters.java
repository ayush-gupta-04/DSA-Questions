// INTUTION : 
// - we have to remove some char and make it smallest.
// - It looks like remove k digits.
// - If we use some DS to strore the characters then it will form a monotonic increasing stack.
// - and is we find a smaller value we will pop the top of the stack.
// - but popping have a condition to follow .. we can only pop if it also appears later


// - vis[26] : to track which char has been vis.
// - lastIndex[26]

// we can only pop from the stack iff :
//     currChar < top of stack 
// &&  top of stack also appears later .. because if it does not appear later then we have to keep it.




class Solution {
    public String removeDuplicateLetters(String s) {
        int n = s.length();
        int[] lastIndex = new int[26];
        for(int i = 0 ;i < n;i++){
            lastIndex[s.charAt(i)-'a'] = i;
        }
        boolean[] vis = new boolean[26];

        Deque<Character> st =  new ArrayDeque();
        for(int i = 0;i < n; i++){
            char ch = s.charAt(i);
            if(vis[ch-'a']) continue;

            while(!st.isEmpty() && ch < st.peekLast() && lastIndex[st.peekLast()-'a'] > i){
                char popped = st.pollLast();
                vis[popped-'a'] = false;
            }

            st.offerLast(ch);
            vis[ch-'a'] = true;
        }

        StringBuilder sb = new StringBuilder();
        for (char c : st) {
            sb.append(c);
        }

        return sb.toString();

    }
}
