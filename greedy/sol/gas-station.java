// Intution : 
// -> I am calculating gas[i] - cost[i] on the way as 'sum'.
// -> if i begin from x and moved to y and my sum became < 0 at y. (x was never the starting point).
// -> so 
// -> 1. x was never the starting point.
// -> 2. i cannot have any other starting point between x and y.
// -> So i have to reset my 'sum' and 'start' index
// -> how am i determining my 'start' index -> the first time my sum became +ve is my start.

// TC -> 2*N + N
//    -> 2*N -> to calc the gSUm and cSum
//    -> N -> main loop
// SC -> 1
class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int gSum = 0;
        int cSum = 0;
        for(int g : gas) gSum += g;
        for(int c : cost) cSum += c;

        if(cSum > gSum) return -1;

        int n = gas.length;
        int i = 0;
        int sum = 0;
        int start = -1;
        while(i < n){
            int extra = gas[i]-cost[i];
            sum += extra;
            if(sum < 0){
                sum = 0;
                start = -1;
            }else if(sum >= 0 && start == -1){
                start = i;
            }
            i++;
        }
        return start;
    }
}
