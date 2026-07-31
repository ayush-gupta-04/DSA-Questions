// --------------------------- brute force -----------------
// time : (NlogN + N*M)



// we will choose the job with max Profit first.
// if that job has deadline if 5.
// we will check from (5 -> 1) if we can schedule that job.
// Saving earlier slots (Days 1, 2, 3, 4) keeps them available for urgent jobs that have tighter deadlines!



class Solution {
    public ArrayList<Integer> jobSequencing(int[] deadline, int[] profit) {
        int n = deadline.length;
        
        // Step 1: Store jobs as pairs [deadline, profit]
        int[][] jobs = new int[n][2];
        int maxDeadline = 0;
        for (int i = 0; i < n; i++) {
            jobs[i][0] = deadline[i];
            jobs[i][1] = profit[i];
            maxDeadline = Math.max(maxDeadline, deadline[i]);
        }

        // Step 2: Sort jobs by profit in descending order 
        Arrays.sort(jobs, (a, b) -> Integer.compare(b[1], a[1]));

        // Step 3: Slots array to keep track of occupied days
        int[] slot = new int[maxDeadline + 1];
        Arrays.fill(slot, -1);

        int countJobs = 0;
        int maxProfit = 0;

        // Step 4: Place each job in the latest available slot
        for (int i = 0; i < n; i++) {
            int jobDeadline = jobs[i][0];
            int jobProfit = jobs[i][1];

            // Linear search backwards for a free slot
            for (int j = Math.min(maxDeadline, jobDeadline); j > 0; j--) {
                if (slot[j] == -1) {
                    slot[j] = i; // Slot occupied
                    countJobs++;
                    maxProfit += jobProfit;
                    break;
                }
            }
        }

        return new ArrayList<>(Arrays.asList(countJobs, maxProfit));
    }
}





// --------------------------- optimal approach ----------------------
// time : N log N
// space : N


// sort the jobs according to the deadline ASC.
// we will store the profit of the scheduled jobs in the min-heap.
// why min-heap ? - so that we could remove the low-profit job.
// if (number of jobs scheduled < deadline) .. add this job to the min-heap.
// else (number of jobs scheduled ==  deadline)
//   - we cannot schedule this job.
//   - but but but .. we can schedule this job if (profit of this job > min profit in all scheduled jobs)

class Solution {
    public ArrayList<Integer> jobSequencing(int[] deadline, int[] profit) {
        int n = deadline.length;
        int[][] jobs = new int[n][2];
        
        for (int i = 0; i < n; i++) {
            jobs[i][0] = deadline[i];
            jobs[i][1] = profit[i];
        }

        // Step 1: Sort jobs by deadline in ascending order 🗓️
        Arrays.sort(jobs, (a, b) -> Integer.compare(a[0], b[0]));

        // Step 2: Min-heap to maintain selected profits
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        // Step 3: Process jobs
        for (int[] job : jobs) {
            int jobDeadline = job[0];
            int jobProfit = job[1];

            if (minHeap.size() < jobDeadline) {
                minHeap.add(jobProfit);
            } else if (!minHeap.isEmpty() && jobProfit > minHeap.peek()) {
                minHeap.poll(); // Remove low profit job
                minHeap.add(jobProfit); // Insert high profit job
            }
        }

        // Step 4: Calculate results
        int totalProfit = 0;
        int totalJobs = minHeap.size();
        while (!minHeap.isEmpty()) {
            totalProfit += minHeap.poll();
        }

        return new ArrayList<>(Arrays.asList(totalJobs, totalProfit));
    }
}
