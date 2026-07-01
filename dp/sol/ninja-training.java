// ------------ Recursion -----------------
// time : 3^n
// space : n (recur st)

class Solution {
    static int fun(int day , int last , int[][] A){
      //base case.
      if(day == 0){
          //return the max task that i can perform;
          int max = 0;
          for(int i = 0 ;i < 3 ; i++){
              if(i != last){
                  max = Math.max(max , A[0][i]);
              }
          }
          return max;
      }

      //do all the stuff on the index.
      int max = 0;
      for(int i = 0 ; i < 3 ; i++){
          if(i != last){
              max = Math.max(max , A[day][i] + fun(day - 1 , i , A));
          }
      }
      return max;
    }

    public int maximumPoints(int nums[][]) {
        // code here
        int n = nums.length;
        return fun(n-1 , 3 , nums);
    }
}

// -------------- memo ------------------
// time : N*4
// space : N + 4*N

// day  : n-1 -> 0
// last : 0 -> 3

class Solution {
	static int fun(int day, int last, int[][] A, int[][] dp) {
		// base case.
		if (day == 0) {
			// return the max task that i can perform;
			int max = 0;
			for (int i = 0 ; i < 3 ; i++) {
				if (i != last) {
					max = Math.max(max, A[0][i]);
				}
			}
			return max;
		}

		if (dp[day][last] != -1)
			return dp[day][last];

		// do all the stuff on the index.
		int max = 0;
		for (int i = 0 ; i < 3 ; i++) {
			if (i != last) {
				max = Math.max(max, A[day][i] + fun(day - 1, i, A, dp));
			}
		}
		return dp[day][last] = max;
	}

	public int maximumPoints(int nums[][]) {
		// code here
		int n = nums.length;
		int[][] dp = new int[n][4];
		for (int[] a : dp)
			Arrays.fill(a, -1);

		return fun(n - 1, 3, nums, dp);
	}
}






// --------------------- Tabulation ---------------------
// time : N*4
// space : N*4


// main loop :
// day : n-1 -> 0   ... recur
//     last : 3 -> 0

// day : 0 -> n-1  ... tab
//     last : 0 -> 3

// base case :
// last : 0 -> 3
//     day : 0

// inner for loop :
// day : 1 -> n-1
//     last : 0 -> 3

class Solution {
	public int maximumPoints(int A[][]) {
		int n = A.length;
		int[][] dp = new int[A.length][4];
		dp[0][0] = Math.max(A[0][1], A[0][2]);
		dp[0][1] = Math.max(A[0][0], A[0][2]);
		dp[0][2] = Math.max(A[0][0], A[0][1]);
		dp[0][3] = Math.max(A[0][0], Math.max(A[0][1], A[0][2]));
		
		for (int day = 1 ; day < n ; day ++) {
			for (int last = 0 ; last < 4 ; last++) {
				for (int i = 0 ; i < 3 ; i++) {
					if (i != last) {
						dp[day][last] = Math.max(dp[day][last], A[day][i] + dp[day - 1][i]);
					}
				}
			}
		}
		return dp[n - 1][3];
	}
}






// ----------------- space opti -----------------------
// time : N*4
// space : 1

class Solution {
	public int maximumPoints(int A[][]) {
		int[] dp = new int[4];
		dp[0] = Math.max(A[0][1], A[0][2]);
		dp[1] = Math.max(A[0][0], A[0][2]);
		dp[2] = Math.max(A[0][0], A[0][1]);
		dp[3] = Math.max(A[0][0], Math.max(A[0][1], A[0][2]));
		
		for (int day = 1 ; day < A.length ; day ++) {
			int[] temp = new int[4];
			for (int last = 0 ; last < 4 ; last++) {
				for (int i = 0 ; i < 3 ; i++) {
					if (i != last) {
						temp[last] = Math.max(temp[last], A[day][i] + dp[i]);
					}
				}
			}
			dp = temp;
		}
		return dp[3];
	}
}
