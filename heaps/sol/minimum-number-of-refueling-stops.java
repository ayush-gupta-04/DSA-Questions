// Stations :  A B C D E F G H I J
// from A i can jump max to F.
// I will add B C D E to the PQ .. and will pick up the station with the most fuel.
// and after picking the next station i will try to see how much next stations i can cover .. add those also to the PQ.
// if standing at a station i found that i don't have any stations left in the PQ .. means that i have visited to all the stations before and my currFuel is not enough to take me to the next station .. means return -1; 

class Solution {
    public int minRefuelStops(int goal, int startFuel, int[][] stations) {
        if(startFuel >= goal) return 0;
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((x,y) -> Integer.compare(y[1],x[1]));

        int currFuel = startFuel;
        int currPos = 0;
        int n = stations.length;
        int cnt = 0;
        int i = 0;
        while(true){
            if(goal - currPos <= currFuel) break;

            // expand the coverage of stations.
            while(i < n){
                if(stations[i][0] - currPos <= currFuel){
                    pq.offer(new int[]{stations[i][0] , stations[i][1] , i});
                }else{
                    break;
                }
                i++;
            }

            if(pq.isEmpty()) return -1;
            int[] nextStation = pq.poll();

            if(nextStation[0] > currPos){
                currFuel -= nextStation[0] - currPos;
                currPos = nextStation[0];
            }
            currFuel += nextStation[1];
            cnt++;
        }
        return cnt;
    }
}
