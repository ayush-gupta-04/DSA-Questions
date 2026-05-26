// - we try to build our ans from MSB to LSB.
// - for 10000 (forbid) ... if i can have at least one valid cell in every row .. then i successfully avoided this bit.
// - for 11000 (forbid) ... if i cannot have at least one valid cell in every row ... then i should not have this bit set in forbid.
// - for 10100 (forbid) ... if i can have at least one valid cell in every row .. then i successfully avoided this bit.

// this is how we check.
class Solution {
    public int minimumOR(int[][] grid) {
        int n= grid.length;
        int m = grid[0].length;

        int ans = 0;
        int test = 0;
        for(int pos = 30; pos >= 0; pos--){
            int forbid = test | (1 << pos);

            boolean found = true;
            for(int[] row : grid){
                boolean possible = false;
                for(int val : row){
                    if((val & forbid) == 0){
                        possible = true;
                        break;
                    }
                }

                if(!possible){
                    found = false;
                    break;
                }
            }

            if(found){
                // i have successfully avoided the bit at pos.
                // i can have 0 there.
                test = forbid;
            }else{
                // i cannot have 0 there.
                // i will try the next bit with the old candidates.
                ans = (ans | (1 << pos));
            }
        }

        return ans;
    }
}
