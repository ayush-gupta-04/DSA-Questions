// Sort according to pos.
// carA ... carB
//  t1       t2
// If t1 <= t2
//  -> carA will form a fleet with carB.
// else 
//  -> carA will form it's own new fleet.
// TC -> O(N)
// SC -> O(N)
class Solution {
    public int carFleet(int t, int[] position, int[] speed) {
        int n = position.length;
        
        double[][] cars = new double[n][2];
        for (int i = 0; i < n; i++) {
            cars[i][0] = position[i];
            cars[i][1] = speed[i];
        }
        
        // Sort cars by starting position in descending order
        Arrays.sort(cars, (a, b) -> Double.compare(a[0], b[0]));
        
        Deque<Double> stack = new ArrayDeque<>();
        
        for (int i = n-1; i >= 0; i--) {
            double p = cars[i][0];
            double s = cars[i][1];
            double time = (t - p) / s;
            
            // If stack is empty or this car takes LONGER than the fleet ahead
            // It means it cannot catch up, so it forms a new fleet!
            if (stack.isEmpty() || time > stack.peek()) {
                stack.push(time);
            }
            // If time <= stack.peek(), it catches up and joins the fleet. 
            // We ignore it (no push).
        }
        
        // Step 4: The number of elements in the stack is the number of fleets
        return stack.size();
    }
}



// -------------------- Appraoch - Optimal ---------------------
// TC -> O(N)
// SC -> O(1)
import java.util.Arrays;

class Solution {
    public int carFleet(int t, int[] position, int[] speed) {
        int n = position.length;
        
        // Combine position and speed
        double[][] cars = new double[n][2];
        for (int i = 0; i < n; i++) {
            cars[i][0] = position[i];
            cars[i][1] = speed[i];
        }
        
        // Sort descending by position
        Arrays.sort(cars, (a, b) -> Double.compare(b[0], a[0]));
        
        int fleets = 0;
        double maxTime = 0.0; // Tracks the slowest fleet ahead
        
        for (int i = 0; i < n; i++) {
            double p = cars[i][0];
            double s = cars[i][1];
            double time = (t - p) / s;
            
            // If this car takes strictly more time than the fleet ahead,
            // it cannot catch up. It becomes the new "bottleneck" (new fleet).
            if (time > maxTime) {
                maxTime = time;
                fleets++;
            }
        }
        
        return fleets;
    }
}
