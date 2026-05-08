// If i just sort and store the time (time to reach the target) for every car.
// It will be like a monotonic stack.
class Solution {
    public int carFleet(int target, int[] position, int[] speed) {
        int n = position.length;
        double[][] cars = new double[n][2];
        for (int i = 0; i < n; i++) {
            cars[i][0] = position[i];
            cars[i][1] = speed[i];
        }
        Arrays.sort(cars, (a, b) -> Double.compare(a[0], b[0]));
        
        ArrayDeque<Double> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            // if current car's time >= last car's time -> last car will pair up this current car.
            // we will pop the last car.... because we will keep the slowest car in the stack.
            double time = (target - cars[i][0]) / cars[i][1];
            
            while(!stack.isEmpty() && time >= stack.peekLast()){
                stack.pollLast();
            }
            stack.offerLast(time);
        }
        return stack.size();
    }
}
