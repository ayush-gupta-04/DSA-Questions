// ---------------------- -------- Brute force : N^3 --------------------------------
// The idea is to iterate through all possible substrings of s1 and check each one to see if s2 appears as a subsequence within it.

// TC -> N^3
// SC -> 1

class Solution {
    // function to check if s2 is a subsequence of sub
    static boolean isSubsequence(String sub, String s2) {
        int i = 0, j = 0;
        while (i < sub.length() && j < s2.length()) {
            if (sub.charAt(i) == s2.charAt(j)) j++;
            i++;
        }
        return j == s2.length();
    }

    // function to find the smallest substring of s1
    // containing s2 as subsequence
    static String minWindow(String s1, String s2) {
        int n = s1.length();
        String ans = "";
        int minLen = Integer.MAX_VALUE;

        for (int start = 0; start < n; start++) {
            for (int end = start; end < n; end++) {
                String sub = s1.substring(start, end + 1);
                if (isSubsequence(sub, s2)) {
                    if (sub.length() < minLen) {
                        minLen = sub.length();
                        ans = sub;
                    }
                    // no need to extend further for this start
                    // as we want smallest
                    break;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        String s1 = "abcdebdde";
        String s2 = "bde";
        System.out.println(minWindow(s1, s2));
    }
}





// -------------------- Better Approach : N^2 approach -----------------------------
// The idea is to iterate through s1 and, for each position where the first character of s2 matches, move forward with two pointers to find the complete subsequence s2.
// Once matched (complete subsequence s2), backtrack from the end position to shrink the window to the smallest substring that still contains s2 as a subsequence.
// why backtrac : 
// s1 = aaabc
// s2 = abc
// s2 is present as subsequence in s1 but s1 is not the smallest ... we must match from the back also.

// TC -> N*N  
// SC -> 1
public class Main {

    static String minWindow(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();
        String ans = "";
        int minLen = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            // find starting point where s1[i] matches s2[0]
            if (s1.charAt(i) == s2.charAt(0)) {
                int p1 = i, p2 = 0;

                // move forward until s2 is matched
                while (p1 < n && p2 < m) {
                    if (s1.charAt(p1) == s2.charAt(p2)) p2++;
                    p1++;
                }

                // if we matched all characters of s2
                if (p2 == m) {
                    // last matched index
                    int end = p1 - 1;

                    // will move p11 and p22 to reach the start index.
                    int p11 = p1 - 1;
                    int p22 = m - 1;

                    // backtrack to find minimal starting index
                    while (p11 >= i && p22 >= 0) {
                        if (s1.charAt(p11) == s2.charAt(p22)) p22--;
                        p11--;
                    }

                    int start = p11 + 1;
                    int len = end - start + 1;
                    if (len < minLen) {
                        minLen = len;
                        ans = s1.substring(start, start + len);
                    }
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        String s1 = "abcdebdde";
        String s2 = "bde";
        System.out.println(minWindow(s1, s2));
    }
}




// ----------------- Optimal Approach : Preprocessing with Next Occurrence Table ------------------------

// Build nextPos table where nextPos[i][c] stores the next occurrence of character c in s1 at or after index i.
// Fill nextPos from right to left so each position knows where each letter appears next.
// For each index in s1 matching the first character of s2, try to match s2 by jumping using nextPos.
// If a complete match is found, record the window and update the smallest one found so far.
// Return the smallest window substring containing s2 as a subsequence.
  
// TC -> N*26  + N * M
//    -> N*26 -> To fill the Next Occurrence Table
//    -> N*M  -> Main loop 
// SC -> N*26
//    -> N*26 -> Next Occurrence Table

import java.util.*;

public class Main {
    public static String minWindow(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();

        // nextPos[i][ch] : next occurrence of character
        // ch after index i in s1
        int[][] nextPos = new int[n + 1][26];
        for (int c = 0; c < 26; c++) {
            nextPos[n][c] = -1; // initialize last row with -1
        }

        // fill table by going backwards through s1
        for (int i = n - 1; i >= 0; i--) {
            for (int c = 0; c < 26; c++) {
                nextPos[i][c] = nextPos[i + 1][c];
            }
            nextPos[i][s1.charAt(i) - 'a'] = i;
        }

        String ans = "";
        int minLen = Integer.MAX_VALUE;

        // try starting at each position in s1
        for (int start = 0; start < n; start++) {
            if (s1.charAt(start) != s2.charAt(0)) continue;

            int idx = start;
            boolean ok = true;

            // match s2 by jumping through nextPos
            for (int j = 0; j < m; j++) {
                if (idx == -1) {
                    ok = false;
                    break;
                }
                idx = nextPos[idx][s2.charAt(j) - 'a'];
                if (idx == -1) {
                    ok = false;
                    break;
                }
                // move to next index for next char .. IMP
                idx++; 
            }

            if (ok) {
                int endIdx = idx - 1;
                int len = endIdx - start + 1;
                if (len < minLen) {
                    minLen = len;
                    ans = s1.substring(start, start + len);
                }
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        String s1 = "abcdebdde";
        String s2 = "bde";
        System.out.println(minWindow(s1, s2));
    }
}

