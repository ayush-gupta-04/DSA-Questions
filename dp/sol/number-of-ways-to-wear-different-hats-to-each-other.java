/*
 * ============================================================================
 * INTUITION & BITMASK APPROACH
 * ============================================================================
 *
 * 1. HOW TO RECOGNIZE BITMASK DP:
 *    - Constraint signal: n (people) <= 10, while hats <= 40.
 *    - Any constraint with N <= 10-16 strongly hints at O(2^N) state compression.
 *    - We need to track a subset of elements that have already been matched
 *      (bipartite matching count problem).
 *
 * 2. WHY THE DIRECT ASSIGNMENT (Person -> Hat) FAILS:
 *    - Natural intuition: Iterate over each person and pick an available hat.
 *    - State representation: (person_idx, used_hats_mask).
 *    - Why it breaks:
 *        * 40 hats require a 40-bit integer mask.
 *        * State space = 2^40 ≈ 10^12 states.
 *        * Even filtering only valid combinations: C(40, 10) ≈ 8.47 * 10^8 states.
 *        * Memoizing 8.5 * 10^8 states causes immediate Memory Limit Exceeded (MLE) / TLE.
 *
 * 3. WHY INVERTING (Hat -> Person) WORKS:
 *    - Flip the perspective: Iterate over hats (1 to 40) and decide which person gets it.
 *    - State representation: (hat_id, assigned_people_mask).
 *    - Why it works:
 *        * People count is tiny (n <= 10), so the mask only needs 10 bits.
 *        * State space: 40 hats * 2^10 masks = 40 * 1024 ≈ 40,960 states.
 *        * Fits comfortably in a small 2D array and executes in < 15ms.
 *
 * 4. ALGORITHM OUTLINE:
 *    - Preprocessing:
 *        Build an inverted adjacency list: hat -> list of people who prefer this hat.
 *    - Decision at hat `i`:
 *        1) Skip hat `i` (nobody gets it):
 *             ways = solve(i + 1, mask)
 *        2) Assign hat `i` to person `p` (if p does not have a hat yet):
 *             ways += solve(i + 1, mask | (1 << p))
 *    - Base cases:
 *        * mask == (1 << n) - 1: All people matched -> return 1.
 *        * hat > 40: Ran out of hats with unmatched people -> return 0.
 * ============================================================================
 */



// ------------------------- Pure - backtracking -----------------------

class Solution {
    // i = hat
    // hatsToPeople.get(i) = people that can wear this hat.

    int MOD = 1_000_000_007;
    int solve(int i, boolean[] vis, List<List<Integer>> hatsToPeople, int n){
        if(i == hatsToPeople.size()){
            for(boolean v : vis) if(!v) return 0;
            return 1;
        }

        long ways = solve(i+1, vis, hatsToPeople, n);

        for(int p : hatsToPeople.get(i)){
            if(vis[p]) continue;

            vis[p] = true;
            ways = (ways + solve(i+1, vis, hatsToPeople, n))%MOD;
            vis[p] = false;
        }
        return (int)ways;
    }
    public int numberWays(List<List<Integer>> peopleToHats) {
        int n = peopleToHats.size();

        List<List<Integer>> hatsToPeople = new ArrayList<>();
        for(int hat = 0; hat <= 40; hat++){
            hatsToPeople.add(new ArrayList<>());
        }
        for(int p = 0; p < n; p++){
            for(int hat : peopleToHats.get(p)){
                hatsToPeople.get(hat).add(p);
            }
        }
        boolean[] vis = new boolean[n];
        return solve(1, vis, hatsToPeople, n);
    }
}









// ------------------------- Bitmask - recursion ----------------------------

class Solution {
    // i = hat
    // hatsToPeople.get(i) = people that can wear this hat.

    int MOD = 1_000_000_007;
    int solve(int i, int mask, List<List<Integer>> hatsToPeople, int n){
        if(mask == (1<<n)-1) return 1;
        if(i == hatsToPeople.size()) return 0;

        long ways = solve(i+1, mask, hatsToPeople, n);

        for(int p : hatsToPeople.get(i)){
            // p already has a hat
            if((mask & (1<<p)) != 0) continue;

            // p gets ith hat
            ways = (ways + solve(i+1, mask|(1<<p), hatsToPeople, n))%MOD;
        }
        return (int)ways;
    }
    public int numberWays(List<List<Integer>> peopleToHats) {
        int n = peopleToHats.size();

        List<List<Integer>> hatsToPeople = new ArrayList<>();
        for(int hat = 0; hat <= 40; hat++){
            hatsToPeople.add(new ArrayList<>());
        }
        for(int p = 0; p < n; p++){
            for(int hat : peopleToHats.get(p)){
                hatsToPeople.get(hat).add(p);
            }
        }
        return solve(1, 0, hatsToPeople, n);
    }
}








// ------------------------------ Bitmask - Memoized --------------------------

class Solution {
    // i = hat
    // hatsToPeople.get(i) = people that can wear this hat.

    int MOD = 1_000_000_007;
    int solve(int i, int mask, List<List<Integer>> hatsToPeople, int n, int[][] dp){
        if(mask == (1<<n)-1) return 1;
        if(i == hatsToPeople.size()) return 0;

        if(dp[i][mask] != -1) return dp[i][mask];

        long ways = solve(i+1, mask, hatsToPeople, n, dp);

        for(int p : hatsToPeople.get(i)){
            // p already has a hat
            if((mask & (1<<p)) != 0) continue;

            // p gets ith hat
            ways = (ways + solve(i+1, mask|(1<<p), hatsToPeople, n, dp))%MOD;
        }
        return dp[i][mask] = (int)ways;
    }
    public int numberWays(List<List<Integer>> peopleToHats) {
        int n = peopleToHats.size();

        List<List<Integer>> hatsToPeople = new ArrayList<>();
        for(int hat = 0; hat <= 40; hat++){
            hatsToPeople.add(new ArrayList<>());
        }
        for(int p = 0; p < n; p++){
            for(int hat : peopleToHats.get(p)){
                hatsToPeople.get(hat).add(p);
            }
        }
        int[][] dp = new int[42][1<<n];
        for(int[] d : dp) Arrays.fill(d, -1);

        return solve(1, 0, hatsToPeople, n, dp);
    }
}







// --------------------------- Bitmask - Tabulation --------------------------

class Solution {
    public int numberWays(List<List<Integer>> peopleToHats) {
        int n = peopleToHats.size();

        List<List<Integer>> hatsToPeople = new ArrayList<>();
        for(int hat = 0; hat <= 40; hat++){
            hatsToPeople.add(new ArrayList<>());
        }
        for(int p = 0; p < n; p++){
            for(int hat : peopleToHats.get(p)){
                hatsToPeople.get(hat).add(p);
            }
        }
        int[][] dp = new int[42][1<<n];

        for(int i = 0; i <= 41; i++){
            dp[i][(1<<n)-1] = 1;
        }
        for(int mask = 0; mask < (1<<n)-2; mask++){
            dp[41][mask] = 0;
        }

        for(int i = 40; i >= 0; i--){
            for(int mask = (1<<n)-2 ; mask >= 0; mask--){

                long ways = dp[i+1][mask];
                for(int p : hatsToPeople.get(i)){
                    // p already has a hat
                    if((mask & (1<<p)) != 0) continue;

                    // p gets ith hat
                    ways = (ways + dp[i+1][mask|(1<<p)])%MOD;
                }
                dp[i][mask] = (int)ways;
            }
        }

        return dp[0][0];
    }
}
