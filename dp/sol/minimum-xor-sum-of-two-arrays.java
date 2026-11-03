// ============================================================================
// 1. PURE BACKTRACKING
// ============================================================================
/*
 * CORE IDEA:
 * - Fix A2 sequentially by index 'i'. For each A2[i], branch and try pairing
 *   it with every unused element in A1.
 * - Uses a boolean[] array to track which elements of A1 are already used.
 *
 * STATE:
 * - solve(i, vis): min XOR sum pairing A2[i...n-1] with unused elements in A1.
 *
 * COMPLEXITY:
 * - Time:  O(n * n!) -> Generates all n! permutations.
 *                       For n = 14, 14! ≈ 8.71 * 10^10 operations (Severe TLE).
 * - Space: O(n)      -> Recursion call stack (depth n) + boolean[] visited array.
 *
 * BOTTLENECK:
 * - Redundant subproblems: Many distinct permutation prefixes leave the exact
 *   same subset of unused elements, but cannot be easily memoized with boolean[].
 */
class Solution1 {
    int solve(int i, int[] A1, int[] A2, boolean[] vis){
        if(i == A2.length) return 0;

        int min = Integer.MAX_VALUE;
        
        for(int j = 0; j < A1.length; j++){
            if(vis[j]) continue;

            vis[j] = true;
            min = Math.min(min, (A1[j]^A2[i]) + solve(i+1, A1, A2, vis));
            vis[j] = false;
        }
        return min;
    }
    public int minimumXORSum(int[] A1, int[] A2) {
        boolean[] vis = new boolean[A1.length];
        return solve(0, A1, A2, vis);
    }
}


// ============================================================================
// 2. BITMASK - BACKTRACKING
// ============================================================================
/*
 * CORE IDEA:
 * - Replaces boolean[] vis with an integer bitmask (j-th bit = 1 means A1[j] is used).
 * - Eliminates explicit index parameter 'i' because i = Integer.bitCount(mask)
 *   (the number of set bits in mask uniquely determines which A2 element is being matched).
 *
 * COMPLEXITY:
 * - Time:  O(n * n!) -> Still traverses the entire permutation tree without caching.
 * - Space: O(n)      -> Recursion call stack only (bitmask is passed by value as an int).
 *
 * WHY IT MATTERS:
 * - Bridges backtracking and DP: compacting the entire state into a single primitive
 *   integer (0 <= mask < 2^n) allows it to directly index a memoization table.
 */
class Solution2 {
    int solve(int[] A1, int[] A2, int mask){
        if(mask == (1<<A1.length)-1) return 0;

        int i = Integer.bitCount(mask);
        int min = Integer.MAX_VALUE;

        for(int j = 0; j < A1.length; j++){
            if((mask & (1<<j)) != 0) continue;

            mask = (mask | (1<<j));
            min = Math.min(min, (A1[j]^A2[i]) + solve(A1, A2, mask));
            mask = (mask & (~(1<<j)));
        }
        return min;
    }
    public int minimumXORSum(int[] A1, int[] A2) {
        return solve(A1, A2, 0);
    }
}


// ============================================================================
// 3. BITMASK - MEMOIZATION (TOP-DOWN DP)
// ============================================================================
/*
 * CORE IDEA:
 * - Caches overlapping subproblems in a 1D table dp[mask].
 * - If 3 elements of A1 are used, regardless of WHICH order they were picked,
 *   the optimal cost to pair the remaining elements is identical.
 *
 * STATE:
 * - dp[mask] = minimum XOR sum to match unused elements in A1 with suffix A2[bitCount(mask)...n-1].
 *
 * COMPLEXITY:
 * - Time:  O(n * 2^n) -> Exactly 2^n unique states, each doing at most n transitions.
 *                        For n = 14: 14 * 16,384 ≈ 2.29 * 10^5 ops (~10 ms -> Accepted).
 * - Space: O(2^n)     -> dp array of size 2^n (16,384 ints ≈ 64 KB) + O(n) call stack.
 */
class Solution3 {
    int solve(int[] A1, int[] A2, int mask, int[] dp){
        if(mask == (1<<A1.length)-1) return 0;

        if(dp[mask] != -1) return dp[mask];

        int i = Integer.bitCount(mask);
        int min = Integer.MAX_VALUE;

        for(int j = 0; j < A1.length; j++){
            if((mask & (1<<j)) != 0) continue;

            mask = (mask | (1<<j));
            min = Math.min(min, (A1[j]^A2[i]) + solve(A1, A2, mask, dp));
            mask = (mask & (~(1<<j)));
        }
        return dp[mask] = min;
    }
    public int minimumXORSum(int[] A1, int[] A2) {
        int n = A1.length;
        int[] dp = new int[1<<n];
        Arrays.fill(dp , -1);
        return solve(A1, A2, 0, dp);
    }
}


// ============================================================================
// 4. BITMASK - TABULATION (BOTTOM-UP DP)
// ============================================================================
/*
 * CORE IDEA:
 * - Iteratively solves states backwards from mask = (2^n - 2) down to 0.
 * - Base Case: dp[(1 << n) - 1] = 0 (all elements matched, remaining cost is 0).
 * - Dependency Order: Any valid next state (mask | (1 << j)) has strictly more
 *   set bits and is numerically greater than 'mask'. Iterating in reverse order
 *   guarantees that all required subproblems are already solved before access.
 *
 * TRANSITION:
 * - dp[mask] = min over all unset j of: (A1[j] ^ A2[bitCount(mask)]) + dp[mask | (1 << j)]
 *
 * COMPLEXITY:
 * - Time:  O(n * 2^n) -> (1 << n) states * n transitions per state.
 * - Space: O(2^n)     -> 1D array of size 2^n, eliminating all recursion stack overhead.
 */
class Solution4 {
    public int minimumXORSum(int[] A1, int[] A2) {
        int n = A1.length;
        int[] dp = new int[1<<n];
       
        dp[(1 << n) - 1] = 0;

        for(int mask = (1<<n)-2; mask >= 0; mask--){

            int i = Integer.bitCount(mask);
            int min = Integer.MAX_VALUE;

            for(int j = 0; j < A1.length; j++){
                if((mask & (1<<j)) != 0) continue;

                mask = (mask | (1<<j));
                min = Math.min(min, (A1[j]^A2[i]) + dp[mask]);
                mask = (mask & (~(1<<j)));
            }
            dp[mask] = min;
        }
        return dp[0];
    }
}
