// time : 2^n 
// - (n = number of rows)
// space : n (recur st)


class Solution {
    int fun(int i , int j ,List<List<Integer>> tri){
        if(i == tri.size()-1){
            return tri.get(i).get(j);
        }

        int left = fun(i + 1, j , tri);
        int right = fun(i + 1 , j + 1 , tri);

        return Math.min(left,right) + tri.get(i).get(j);
    }
    public int minimumTotal(List<List<Integer>> triangle) {
        return fun(0,0,triangle);
    }
}



// ------------------ memo --------------------------
// time : N*N (number of states are N*N)
// space : N + N*N
// - N : recur st
// - N*N : dp[][]

class Solution {
    int fun(int i, int j, List<List<Integer>> tri, int[][] dp) {
        if (i == tri.size() - 1) {
            return tri.get(i).get(j);
        }

        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        int left = fun(i + 1, j, tri, dp);
        int right = fun(i + 1, j + 1, tri, dp);

        return dp[i][j] = Math.min(left, right) + tri.get(i).get(j);
    }

    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[][] dp = new int[n][n];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return fun(0, 0, triangle, dp);
    }
}



// ------------------------- tab ----------------------------
// time : N*N (number of states are N*N)
// space : N*N
// - N*N : dp[][]


// main states
// i : 0 -> n-1    ... -> ... n-1 -> 0
// j : 0 -> i    ... -> ... i -> 0


// base case : 
// j = 0 -> i
//    i = n-1


// inner for loop
// i : n-2 -> 0
//   j : i -> 0


class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[][] dp = new int[n][n];
        // base case.
        for (int j = 0; j < n; j++) {
            dp[n - 1][j] = triangle.get(n - 1).get(j);
        }
        // inner for loop
        for (int i = n - 2; i >= 0; i--) {
            for (int j = i; j >= 0; j--) {
                int down = dp[i + 1][j];
                int diagonal = dp[i + 1][j + 1];
                dp[i][j] = triangle.get(i).get(j) + Math.min(down, diagonal);
            }
        }
        // The top element will hold the minimum total path sum
        return dp[0][0];
    }
}



// ---------------------- space opti -------------------------
// time : N*N
// space : N 
// - N : dp[]


class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[] dp = new int[n];
      
        for (int j = 0; j < n; j++) {
            dp[j] = triangle.get(n - 1).get(j);
        }
        for (int i = n - 2; i >= 0; i--) {
            int[] temp = new int[n];
            for (int j = i; j >= 0; j--) {
                int down = dp[j];
                int diagonal = dp[j + 1];
                temp[j] = triangle.get(i).get(j) + Math.min(down, diagonal);
            }
            dp = temp;
        }

        // The top element will hold the minimum total path sum
        return dp[0];
    }
}

