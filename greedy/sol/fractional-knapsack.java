// sort according to the val/wt ratio DESC.
// for a pair .. if i can take all the weights, take them all.
//               else only take a part of it.


class Solution {
    public double fractionalKnapsack(int[] val, int[] wt, int capacity) {
        // code here
        int n = val.length;
        int[][] pair = new int[n][2];
        for(int i = 0;i < n;i++){
            pair[i][0] = val[i];
            pair[i][1] = wt[i];
        }
        
        
        Arrays.sort(pair , (x,y) -> Double.compare((y[0]/(y[1]*1.0)), (x[0]/(x[1]*1.0))));
        
        double values = 0.0;
        int cap = 0;
        
        for(int i = 0; i < n; i++){
            int w = pair[i][1];
            int v = pair[i][0];
            if(cap + w <= capacity){
                cap += w;
                values += v;
            }else{
                int rem = capacity - cap;
                values += (v/(w*1.0))*(rem*1.0);
                break;
            }
        }
        
        return values;
    }
}
