// if we schedule the meet having the earliest finish time.
// we can have room to schedule more meetings.


// Sort meetings by end time
// for every further meetings, check if it overlaps with the last one.
// if not overlap then add it to the list, otherwise don't add it.


class Solution {
    public ArrayList<Integer> maxMeetings(int[] s, int[] e) {
        // code here
        int n = s.length;
        int[][] times = new int[n][3];
        for(int i = 0;i < n; i++){
            times[i][0] = s[i];
            times[i][1] = e[i];
            times[i][2] = i+1;
        }
        
        
        Arrays.sort(times, (x,y) -> {
            if(x[1] != y[1]) return Integer.compare(x[1],y[1]);   // first finish time.
            return Integer.compare(x[2],y[2]);             // if same finish time .. based on index.
        });
        
        ArrayList<Integer> ans = new ArrayList<>();
        int last = 0;
        ans.add(times[0][2]);
        
        for(int i = 1; i < n; i++){
            if(times[last][1] < times[i][0]){   // if last doesn't overlaps with the current one.
                last = i;
                ans.add(times[i][2]);
            }
        }
        Collections.sort(ans);
        return ans;
    }
}
