/*
 * Problem: Maximal Square (LeetCode 221)
 *
 * 1. State Definition:
 *    dp[i][j] = side length of the largest all-1 square whose BOTTOM-RIGHT corner is at (i, j).
 *
 * 2. Recurrence Relation:
 *    - mat[i][j] == '0': dp[i][j] = 0 (a '0' can never anchor a square of 1s).
 *    - mat[i][j] == '1': dp[i][j] = 1 + min(up, left, dia)
 *
 *    Intuition for min():
 *    To form a square of side `k` ending at (i, j), we need three overlapping sub-squares
 *    of side `k - 1` ending at:
 *      - (i - 1, j)     [up]
 *      - (i, j - 1)     [left]
 *      - (i - 1, j - 1) [diagonal]
 *    The expansion is constrained by the bottleneck (the smallest of the three neighbors).
 *
 * 3. Base Cases:
 *    Cells in row 0 or col 0 have no room to expand upwards/leftwards;
 *    their max side length is 1 if mat[i][j] == '1', else 0.
 *
 * 4. Complexity:
 *    - Time:  O(N * M) -> each cell visited in constant time.
 *    - Space: O(N * M) -> can be optimized to O(M) via a rolling 1D array.
 */



class Solution {
    public int maximalSquare(char[][] mat) {
        int n = mat.length;
        int m = mat[0].length;

        int[][] dp = new int[n][m];

        // base case fill
        for(int j = 0; j < m; j++){
            if(mat[0][j]=='1') dp[0][j] = 1;
        }
        for(int i = 0; i < n; i++){
            if(mat[i][0]=='1') dp[i][0] = 1;
        }

        for(int i = 1; i < n; i++){
            for(int j = 1; j < m; j++){

                if(mat[i][j]=='0') continue;  // no square ending here.

                int up = dp[i-1][j];
                int left = dp[i][j-1];
                int dia = dp[i-1][j-1];

                dp[i][j] = 1 + Math.min(up, Math.min(left, dia));
            }
        }


        int maxArea = 0;

        for(int i = 0;i < n; i++){
            for(int j = 0; j < m; j++){
                maxArea = Math.max(maxArea, dp[i][j]*dp[i][j]);
            }
        }

        return maxArea;
    }
}
