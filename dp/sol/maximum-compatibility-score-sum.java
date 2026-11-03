/*
 * APPROACH 1: Recursion with Backtracking (Permutations)
 * 
 * 1. Intuition:
 *    - Each student i must be paired with exactly one distinct mentor j.
 *    - Instead of recomputing the match score inside leaf nodes, precompute all pairwise 
 *      compatibility scores into an m x m matrix score[i][j] in O(m^2 * n).
 *    - Systematically pair student i with every unused mentor j using a visited array, 
 *      accumulating the score on the fly and backtracking after the recursive call.
 * 
 * 2. State & Base Case:
 *    - State: solve(i, vis) -> assigns mentor to student 'i' given currently used mentors 'vis'.
 *    - Base Case: When i == m (all students assigned), return 0.
 * 
 * 3. Complexity:
 *    - Time Complexity: O(m^2 * n + m!)
 *      Precomputation takes O(m^2 * n). Permutations explore m! leaf paths.
 *    - Space Complexity: O(m^2 + m)
 *      O(m^2) for the score matrix + O(m) recursion call stack & visited array.
 */
class Solution {
    public int maxCompatibilitySum(int[][] stu, int[][] ment) {
        int m = stu.length, n = stu[0].length;
        int[][] score = new int[m][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                for (int k = 0; k < n; k++) {
                    if (stu[i][k] == ment[j][k]) score[i][j]++;
                }
            }
        }
        return solve(0, new boolean[m], score, m);
    }

    private int solve(int i, boolean[] vis, int[][] score, int m) {
        if (i == m) return 0;

        int max = 0;
        for (int j = 0; j < m; j++) {
            if (vis[j]) continue;

            vis[j] = true;
            max = Math.max(max, score[i][j] + solve(i + 1, vis, score, m));
            vis[j] = false;
        }
        return max;
    }
}



// ----------------------------------------------------------------


/*
 * APPROACH 2: Top-Down DP (Bitmask + Memoization)
 * 
 * 1. Intuition & State Compression:
 *    - Notice that if students {0, 1} take mentors {1, 2} or {2, 1}, the remaining set of 
 *      available mentors for students 2 to m-1 is identical. This causes overlapping subproblems.
 *    - Since m <= 8, compress the boolean[] visited array into an integer 'mask' (bit j = 1 means 
 *      mentor j is taken, 0 means available).
 *    - The student index 'i' is implicit: i = Integer.bitCount(mask), meaning we don't need 'i' 
 *      as a separate DP state dimension.
 * 
 * 2. State & Transition:
 *    - State: memo[mask] = max score possible for pairing the remaining mentors (0-bits in mask) 
 *      with the remaining students.
 *    - Base Case: mask == (1 << m) - 1 (all mentors picked) -> return 0.
 *    - Transition: For every mentor j where (mask & (1 << j)) == 0:
 *        score[bitCount(mask)][j] + solve(mask | (1 << j))
 * 
 * 3. Complexity:
 *    - Time Complexity: O(m^2 * n + m * 2^m)
 *      Total unique states = 2^m. From each state, we iterate over m mentors.
 *      For m = 8: 8 * 256 = 2,048 operations (vs 40,320 in plain backtracking).
 *    - Space Complexity: O(2^m + m^2 + m)
 *      O(2^m) for memo array + O(m) recursion stack + O(m^2) score matrix.
 */
class Solution {
    public int maxCompatibilitySum(int[][] stu, int[][] ment) {
        int m = stu.length, n = stu[0].length;
        int[][] score = new int[m][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                for (int k = 0; k < n; k++) {
                    if (stu[i][k] == ment[j][k]) score[i][j]++;
                }
            }
        }

        Integer[] memo = new Integer[1 << m];
        return solve(0, score, m, memo);
    }

    private int solve(int mask, int[][] score, int m, Integer[] memo) {
        if (mask == (1 << m) - 1) return 0;
        if (memo[mask] != null) return memo[mask];

        int i = Integer.bitCount(mask);
        int max = 0;

        for (int j = 0; j < m; j++) {
            if ((mask & (1 << j)) == 0) {
                max = Math.max(max, score[i][j] + solve(mask | (1 << j), score, m, memo));
            }
        }

        return memo[mask] = max;
    }
}


// ----------------------------------------------------------------

/*
 * APPROACH 3: Bottom-Up DP (Tabulation)
 * 
 * 1. Intuition:
 *    - Removes recursion stack overhead by filling the DP table iteratively.
 *    - Mirrors the top-down logic: iterate backwards from mask = (1 << m) - 1 down to 0.
 *    - Because higher masks represent states with more assigned mentors, calculating masks 
 *      in decreasing numerical order guarantees that future states (mask | (1 << j)) are 
 *      already solved before mask is evaluated.
 * 
 * 2. State & Base Case:
 *    - dp[mask] = max compatibility score obtainable given the mentor set already taken by 'mask'.
 *    - Base Case: dp[(1 << m) - 1] = 0 (all mentors assigned, 0 additional score).
 *    - Answer: dp[0] (0 mentors assigned initially).
 * 
 * 3. Complexity:
 *    - Time Complexity: O(m^2 * n + m * 2^m)
 *      Precomputation O(m^2 * n) + DP loop O(m * 2^m).
 *    - Space Complexity: O(2^m + m^2)
 *      O(2^m) for the 1D dp array (256 integers) + O(m^2) for the score matrix.
 *      Zero recursion stack frames used.
 */
class Solution {
    public int maxCompatibilitySum(int[][] stu, int[][] ment) {
        int m = stu.length, n = stu[0].length;
        int[][] score = new int[m][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                for (int k = 0; k < n; k++) {
                    if (stu[i][k] == ment[j][k]) score[i][j]++;
                }
            }
        }

        int[] dp = new int[1 << m];
        
        for (int mask = (1 << m) - 1; mask >= 0; mask--) {
            int i = Integer.bitCount(mask);
            if (i >= m) continue;

            int max = 0;
            for (int j = 0; j < m; j++) {
                if ((mask & (1 << j)) == 0) {
                    max = Math.max(max, score[i][j] + dp[mask | (1 << j)]);
                }
            }
            dp[mask] = max;
        }
        return dp[0];
    }
}
