public class CelebrityProblem {

    public static int findCelebrity(int n, int[][] M) {
        int i = 0;
        int j = n - 1;

        while (i < j) {
            if (M[i][j]) {
                // If i knows j, i cannot be a celebrity
                i++;
            } else {
                // If i does not know j, j cannot be a celebrity
                j--;
            }
        }

        int candidate = i;

        // Verify the candidate
        for (int k = 0; k < n; k++) {
            if (k != candidate) {
                if (M[candidate][k] || !M[k][candidate]) {  // A celebrity must know NO ONE, and EVERYONE must know the celebrity
                    return -1;
                }
            }
        }

        return candidate;
    }

    public static void main(String[] args) {
        int[][] M = {
            {0, 1, 1, 0},
            {0, 0, 1, 0},
            {0, 0, 0, 0},
            {0, 1, 1, 0}
        };

        int celebrity = findCelebrity(M.length, M);

        if (celebrity == -1) {
            System.out.println("No celebrity exists in the group.");
        } else {
            System.out.println("Celebrity ID is: " + celebrity);
        }
    }
}
