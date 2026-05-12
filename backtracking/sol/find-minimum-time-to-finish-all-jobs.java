//------------------ Brute Force : TLE -------------------
// try the all possible combination.
class Solution {
    int minMaxTime = Integer.MAX_VALUE;

    public int minimumTimeRequired(int[] jobs, int k) {
        int[] workers = new int[k];
        backtrack(jobs, workers, 0);
        return minMaxTime;
    }

    private void backtrack(int[] jobs, int[] workers, int jobIndex) {
        // Base Case
        if (jobIndex == jobs.length) {
            int currentMax = 0;
            for (int time : workers) {
                currentMax = Math.max(currentMax, time);
            }
            minMaxTime = Math.min(minMaxTime, currentMax);
            return;
        }

        // Try assigning the current job to each worker
        for (int i = 0; i < workers.length; i++) {
            workers[i] += jobs[jobIndex];
            backtrack(jobs, workers, jobIndex + 1);
            workers[i] -= jobs[jobIndex]; // backtrack
        }
    }
}




// ----------------------- Better Approach : Backtracking + Pruning -----------------------
// The pure backtracking was doing a lot of useless work .. we need to prune the tree.
// Pruning techniques :
//    -> Greedy Ordering
//       - If i need to build a number by the combination of multiple numbers .. like here I need to build the currSum (of a bucket) from nums.
//       - Then , remember this greedy ordering.
//       -> Choose Larger number first to check if it is feseable or not.
//       -> Example.
//             -> To build 5 we can have multiple smaller element .. [1,1,1,1,1] or [1,2,2] .. 
//             -> failing will take time to check, with big numbers tree willl have smaller branches.
//   -> Symmetry Breaking (The Bucket Exception) : 
//      - Imagine you have 3 identical, empty workers. 
//      - Giving the first job to Worker 1 is exactly the same universe as giving it to Worker 2. 
//      - We don't need to explore both
//   -> Feasibility Pruning
///     - If the current worker's time already exceeds our globally found minMaxTime, there is zero reason to continue down this path.



  import java.util.Arrays;

class Solution {
    int minMaxTime = Integer.MAX_VALUE;

    public int minimumTimeRequired(int[] jobs, int k) {
        Arrays.sort(jobs); 
        int[] workers = new int[k];
        // Start from the end of the array to process largest jobs first!
        backtrack(jobs, workers, jobs.length - 1);
        return minMaxTime;
    }

    private void backtrack(int[] jobs, int[] workers, int jobIndex) {
        if (jobIndex < 0) {
            int currentMax = 0;
            for (int time : workers) {
                currentMax = Math.max(currentMax, time);
            }
            minMaxTime = Math.min(minMaxTime, currentMax);
            return;
        }

        for (int i = 0; i < workers.length; i++) {
            // Pruning: If adding this job exceeds our known best, skip!
            if (workers[i] + jobs[jobIndex] >= minMaxTime) {
                continue;
            }

            workers[i] += jobs[jobIndex];
            backtrack(jobs, workers, jobIndex - 1);
            workers[i] -= jobs[jobIndex];

            // Symmetry Breaking: If this worker had 0 hours and the recursion
            // didn't lead to a better overall answer, trying the next empty worker 
            // will yield the exact same useless result.
            // and i am 100% sure that next workers will be empty for sure.
            if (workers[i] == 0) {
                break;
            }
        }
    }
}




// ------------------ Approach 3 : Binary Search + Backtracking ------------------
// Let's See the range first.
// nums = [1 ,2 ,4, 7, 8].
// - the minimum time possible would be 8 .. if we have N workers.
// - the maximum time possible would be 22 (sum of all) .. if we give all the job to 1 worker.
// Let's ask: "Hey, is it possible to distribute the jobs such that NO worker works more than H hours?"
// If the answer is YES, we might be able to do even better! We lower H.
// If the answer is NO, our H is too strict. We must increase H.

import java.util.Arrays;

class Solution {
    public int minimumTimeRequired(int[] jobs, int k) {
        int left = 0;
        int right = 0;
        
        for (int job : jobs) {
            left = Math.max(left, job);
            right += job;
        }
        Arrays.sort(jobs); // Sorting helps with our greedy backwards iteration
        while (left < right) {
            int mid = left + (right - left) / 2;
            int[] workers = new int[k];   
            // Check if 'mid' is a feasible max limit
            if (canFinish(jobs, workers, jobs.length - 1, mid)) {
                right = mid; // Try to find a tighter, smaller max time
            } else {
                left = mid + 1; // Limit was too strict, increase it
            }
        }
        return left;
    }
    
    private boolean canFinish(int[] jobs, int[] workers, int jobIndex, int limit) {
        if (jobIndex < 0) return true; // All jobs safely assigned!
        for (int i = 0; i < workers.length; i++) {
            if (workers[i] + jobs[jobIndex] <= limit) {
                workers[i] += jobs[jobIndex];
                if (canFinish(jobs, workers, jobIndex - 1, limit)) {
                    return true;
                }
                workers[i] -= jobs[jobIndex]; // backtrack
            }
            // Symmetry breaking (same logic as Approach 2)
            if (workers[i] == 0) break; 
        }
        
        return false;
    }
}
