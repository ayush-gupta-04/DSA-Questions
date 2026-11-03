/*
 * ============================================================================
 * PATTERN RECOGNITION & BITMASK FORMULATION NOTES: LEETCODE 1125
 * ============================================================================
 * 
 * 1. HOW TO RECOGNIZE IT AS A BITMASK DP PROBLEM:
 * ----------------------------------------------------------------------------
 * A. The Constraint Giveaway:
 *    - req_skills.length <= 16 (TINY)
 *    - people.length <= 60 (MODERATE)
 *    Rule of thumb: Whenever an attribute/set constraint is <= 16 to 20, 
 *    it strongly hints at an O(2^N) bitmask state space (2^16 = 65,536).
 *
 * B. Problem Category:
 *    - This is the classic NP-Hard "Minimum Weight Set Cover" problem.
 *    - Brute-forcing people subsets gives 2^60 combinations (impossible).
 *    - But tracking the subset of *skills covered* only gives 2^16 states.
 *    - Therefore, the DP state MUST be parameterized by the skills covered,
 *      not by the people chosen.
 *
 * C. Overlapping Subproblems:
 *    - Choosing (Person 0 + Person 2) might cover the exact same skills 
 *      as choosing (Person 1 + Person 3).
 *    - From index `i` onward, the only thing that matters is:
 *      "Which skills are still missing?", not "Who picked them earlier?"
 *
 *
 * 2. HOW BITMASKS WERE USED EFFECTIVELY (DUAL BITMASK TRICK):
 * ----------------------------------------------------------------------------
 * Instead of standard heavy objects (Sets, Lists, Tuples), we utilized 
 * two separate bitmasks:
 *
 * A. The State Bitmask (Integer: 0 to 2^16 - 1) -> "Skills Covered":
 *    - Each skill string is mapped to an index [0, N - 1].
 *    - Skill combination = integer bitmask.
 *    - Set union of skills becomes a single CPU bitwise operation:
 *        newSkillMask = currentSkillMask | personSkills[i]
 *    - Target state when all skills are covered:
 *        target = (1 << N) - 1
 *
 * B. The Value Bitmask (64-bit Long) -> "Team Composition":
 *    - Since people.length <= 60, any team subset fits inside a `long` (64 bits).
 *    - If bit `i` is 1, person `i` is included in the team:
 *        newTeam = previousTeam | (1L << i)
 *    - Finding the size of the team is an O(1) hardware operation:
 *        teamSize = Long.bitCount(teamMask)
 *    - Eliminates object allocations (`List<Integer>`) inside recursive/DP calls,
 *      reducing memory overhead and eliminating GC pauses.
 *
 *
 * 3. KEY PRUNING & TRANSITION LOGIC:
 * ----------------------------------------------------------------------------
 * - Redundancy check:
 *     if ((curr | skills[i]) == curr) { skip person i }
 *   If person `i` offers zero new skills to the current subset, picking them
 *   can never be part of an optimal sub-solution.
 *
 * - Comparison:
 *     Long.bitCount(nt) < Long.bitCount(t) ? nt : t
 *   Selects whichever decision tree path uses fewer set bits (fewer people).
 * ============================================================================
 */




// -------------- recursion -----------

/*
 * APPROACH 1: Pure Recursion (Pick / Skip)
 * Intuition: For each person, we have two choices: either include them in our team or skip them. 
 * We use an integer bitmask (`curr`) to track the skills collected so far. 
 * If `curr` reaches the `target` mask ((1 << n) - 1), we've formed a valid team.
 * 
 * Time Complexity: O(2^M) where M is the number of people. We branch 2 ways for every person. (Causes TLE)
 * Space Complexity: O(M) for the recursion stack space.
 */
class Solution {
    // It will return bitmask of chosen teams.
    long solve(int i , int target, int curr, int[] skills){   // target skill = (1<<n)-1
        if(curr==target) return 0L;
        if(i==skills.length) return (1L<<61)-1;   // all people.

        long nt = solve(i+1, target, curr, skills);
        long t = (1L<<61)-1;
        if((curr|skills[i]) != curr){
          t = solve(i+1, target, (curr|skills[i]), skills) | (1L<<i);  
        }
        if(Long.bitCount(nt) < Long.bitCount(t))  return nt;
        return t;
    }
    public int[] smallestSufficientTeam(String[] req, List<List<String>> people) {
        int n = req.length;
        int m = people.size();

        // map the skills
        HashMap<String, Integer> map = new HashMap<>();
        int idx = 0;
        for(String r : req){
            map.putIfAbsent(r, idx++);
        }

        // build skills .. skills[i] -> The ith person has this bitmask as skills.
        int[] skills = new int[m];
        idx = 0;
        for(List<String> p : people){
            for(String s : p) skills[idx] = skills[idx] | (1<<map.get(s));
            idx++;
        }

        Long teams = solve(0, (1<<n)-1, 0, skills);

        int[] res = new int[Long.bitCount(teams)];
        idx = 0;
        for(int i = 0; i < m; i++){
            if((teams & (1L<<i)) != 0){
                res[idx++] = i;
            }
        }
        return res;
    }
}








// ----------------- memoisation ---------------

/*
 * APPROACH 2: Top-Down DP (Memoization)
 * Intuition: The pure recursive approach recalculates the same (person_index, current_skills_mask) 
 * states multiple times. By caching the smallest team representation in a 2D array `dp[i][curr]`, 
 * we only compute each unique state once.
 * 
 * Time Complexity: O(M * 2^N) where M is people count and N is required skills count.
 * Space Complexity: O(M * 2^N) for the 2D DP table + O(M) recursion stack.
 */
class Solution {
    // It will return bitmask of chosen teams.
    long solve(int i , int target, int curr, int[] skills, long[][] dp){   // target skill = (1<<n)-1
        if(curr==target) return 0L;
        if(i==skills.length) return (1L<<61)-1;   // all people.

        if(dp[i][curr] != -1) return dp[i][curr];

        long nt = solve(i+1, target, curr, skills, dp);
        long t = (1L<<61)-1;
        if((curr|skills[i]) != curr){
          t = solve(i+1, target, (curr|skills[i]), skills, dp) | (1L<<i);  
        } 

        if(Long.bitCount(nt) < Long.bitCount(t))  return dp[i][curr] = nt;
        return dp[i][curr] = t;
    }
    public int[] smallestSufficientTeam(String[] req, List<List<String>> people) {
        int n = req.length;
        int m = people.size();

        // map the skills
        HashMap<String, Integer> map = new HashMap<>();
        int idx = 0;
        for(String r : req){
            map.putIfAbsent(r, idx++);
        }

        // build skills .. skills[i] -> The ith person has this bitmask as skills.
        int[] skills = new int[m];
        idx = 0;
        for(List<String> p : people){
            for(String s : p) skills[idx] = skills[idx] | (1<<map.get(s));
            idx++;
        }

        long[][] dp = new long[m][1<<n];
        for(long[] d : dp) Arrays.fill(d, -1L);
        Long teams = solve(0, (1<<n)-1, 0, skills, dp);

        // generating the ans.
        int[] res = new int[Long.bitCount(teams)];
        idx = 0;
        for(int i = 0; i < m; i++){
            if((teams & (1L<<i)) != 0){
                res[idx++] = i;
            }
        }
        return res;
    }
}







// ----------------- tabulation ----------------

/*
 * APPROACH 3: Bottom-Up DP (Tabulation)
 * Intuition: We convert the memoized logic into an iterative approach, building the solution 
 * starting from the base cases (i = m, or curr = target). This eliminates the overhead of 
 * recursive function calls while preserving the exact same state transitions.
 * 
 * Time Complexity: O(M * 2^N) 
 * Space Complexity: O(M * 2^N) for the 2D DP table. (Can be optimized to O(2^N) using a 1D array).
 */
class Solution {
    // Note: The solve method is left here from your previous code but is unused in this bottom-up approach.
    long solve(int i , int target, int curr, int[] skills, long[][] dp){   // target skill = (1<<n)-1
        if(curr==target) return 0L;
        if(i==skills.length) return (1L<<61)-1;   // all people.

        if(dp[i][curr] != -1) return dp[i][curr];

        long nt = solve(i+1, target, curr, skills, dp);
        long t = solve(i+1, target, (curr|skills[i]), skills, dp) | (1L<<i);  

        if(Long.bitCount(nt) < Long.bitCount(t))  return dp[i][curr] = nt;
        return dp[i][curr] = t;
    }
    public int[] smallestSufficientTeam(String[] req, List<List<String>> people) {
        int n = req.length;
        int m = people.size();

        // map the skills
        HashMap<String, Integer> map = new HashMap<>();
        int idx = 0;
        for(String r : req){
            map.putIfAbsent(r, idx++);
        }

        // build skills .. skills[i] -> The ith person has this bitmask as skills.
        int[] skills = new int[m];
        idx = 0;
        for(List<String> p : people){
            for(String s : p) skills[idx] = skills[idx] | (1<<map.get(s));
            idx++;
        }

        int target = (1<<n)-1;
        long[][] dp = new long[m+1][target+1];

        // base case
        for(int i = 0; i <= m; i++) dp[i][target] = 0L;
        for(int curr = 0; curr < target; curr++) dp[m][curr] = (1L<<61)-1;

        for(int i = m-1; i >= 0; i--){
            for(int curr = target-1; curr >= 0; curr--){
                long nt = dp[i+1][curr];
                long t = (1L<<61)-1;
                if((curr|skills[i]) != curr){
                    t = dp[i+1][(curr|skills[i])] | (1L<<i);
                }
                if(Long.bitCount(nt) < Long.bitCount(t)) dp[i][curr] = nt;
                else dp[i][curr] = t;
            }
        }

        Long teams = dp[0][0];

        // generating the ans.
        int[] res = new int[Long.bitCount(teams)];
        idx = 0;
        for(int i = 0; i < m; i++){
            if((teams & (1L<<i)) != 0){
                res[idx++] = i;
            }
        }
        return res;
    }
}
