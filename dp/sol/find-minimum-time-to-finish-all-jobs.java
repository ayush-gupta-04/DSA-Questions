// ----- IMP -----
// We are assigning a JOB to any K worker.
// We are not building worker by worker.
// This is JOB centric.


// ------------------ Brute-Force : TLE --------------------------
// Each of the N jobs can be assigned to any of the K workers.
// Once all jobs are assigned we compute the maximum load among the $k$ workers and take the minimum across all possible assignments.

// TC - K^N
// SC -  (N + K)
class Solution {
    private int minMakespan = Integer.MAX_VALUE;

    public int minimumTimeRequired(int[] jobs, int k) {
        int[] workers = new int[k];
        backtrack(jobs, 0, workers);
        return minMakespan;
    }

    private void backtrack(int[] jobs, int idx, int[] workers) {
        // Base case: all jobs assigned
        if (idx == jobs.length) {
            int currentMax = 0;
            for (int load : workers) {
                currentMax = Math.max(currentMax, load);
            }
            minMakespan = Math.min(minMakespan, currentMax);
            return;
        }

        // Try giving jobs[idx] to each of the k workers
        for (int i = 0; i < workers.length; i++) {
            workers[i] += jobs[idx];
            backtrack(jobs, idx + 1, workers);
            workers[i] -= jobs[idx]; // Backtrack
        }
    }
}



// -------------- Beter ---------------
// Three optimizations reduce the search space by several orders of magnitude:
// Sort Descending: 
//     - Assign larger jobs first. 
//     - Large jobs fill workers faster, triggering pruning conditions earlier in the search tree.
// Upper Bound Pruning: 
//     - If giving a job to worker i results in a load > our currently known best ans, we can discard this branch.
// Symmetry Breaking (Empty Worker Elimination): 
//     - Initially, all workers have 0 load and are completely indistinguishable. 
//     - Giving a job to worker 0, 1, or 2 produces identical assignments up to permutation. 
//     - If an assignment to an idle worker (workers[i] == 0) fails or finishes, we must break immediately rather than trying other idle workers.



class Solution {
    private int ans = Integer.MAX_VALUE;

    public int minimumTimeRequired(int[] jobs, int k) {
        Arrays.sort(jobs);
        reverse(jobs);

        int[] workers = new int[k];
        backtrack(jobs, 0, workers);
        return ans;
    }

    private void backtrack(int[] jobs, int idx, int[] workers) {
        if (idx == jobs.length) {
            int max = 0;
            for(int w : workers) max = Math.max(max, w);
            ans = Math.min(max, ans);
            return;
        }

        for (int i = 0; i < workers.length; i++) {
            // Pruning 1 : if it cannot beat our best ans, prune.
            if(workers[i] + jobs[idx] > ans) continue;

            workers[i] += jobs[idx];
            backtrack(jobs, idx + 1, workers);
            workers[i] -= jobs[idx];

            // Pruning 2 : If this worker was empty (0 load),
            // all subsequent empty workers are identical. Break out.
            if (workers[i] == 0) break;
        }
    }

    private void reverse(int[] arr) {
        int l = 0, r = arr.length - 1;
        while (l < r) {
            int temp = arr[l];
            arr[l++] = arr[r];
            arr[r--] = temp;
        }
    }
}






// -------------------------- Binary Search with Pruning ---------------------

// The maximum time required by any worker has a monotonic search space:
//   Lower Bound: max(jobs) (no worker can do less than the largest single job).
//   Upper Bound: sum(jobs) (one worker does every job).
// For a target limit mid, we determine whether it is feasible to schedule all jobs such that no worker exceeds mid. 
// This turns the problem into a Bin Packing decision problem, solved efficiently with pruned backtracking.


class Solution {
    public int minimumTimeRequired(int[] jobs, int k) {
        Arrays.sort(jobs);
        reverse(jobs);

        int s = jobs[0];
        int e = 0;
        for (int job : jobs) {
            e += job;
        }

        while (s <= e) {
            int mid = s + (e - s) / 2;
            int[] workers = new int[k];

            if (canFinish(jobs, 0, workers, mid)) {
                e = mid - 1;
            } else {
                s = mid + 1;
            }
        }
        return s;
    }

    private boolean canFinish(int[] jobs, int idx, int[] workers, int limit) {
        if (idx == jobs.length) {
            return true;
        }

        for (int i = 0; i < workers.length; i++) {
            if (workers[i] + jobs[idx] > limit) continue;

            workers[i] += jobs[idx];
            if (canFinish(jobs, idx + 1, workers, limit)) return true;
            workers[i] -= jobs[idx];
            
            // If a worker with 0 load couldn't take this job or lead to a solution,
            // trying another worker with 0 load is completely redundant.
            if (workers[i] == 0) break;
        }
        return false;
    }

    private void reverse(int[] arr) {
        int l = 0, r = arr.length - 1;
        while (l < r) {
            int temp = arr[l];
            arr[l++] = arr[r];
            arr[r--] = temp;
        }
    }
}
