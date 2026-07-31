// waitTime[i] = sum(0 -> i-1) of jobs.


class Solution {
    static int solve(int jobs[]) {
        Arrays.sort(jobs);

        float waitTime = 0;  
        int totalTime = 0;   
        int n = jobs.length; 

        for (int i = 0; i < n; i++) {
            waitTime += totalTime; 
            totalTime += jobs[i];   
        }

        return (int)waitTime / n;
    }
}
