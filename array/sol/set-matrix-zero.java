// ----------- Better -----------
// time : 2 * (M x N)
// space : 2 * M

class Solution {
    // Function to set entire row and column to 0 if an element in the matrix is 0
    public void setZeroes(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;

        // Create row marker array
        boolean[] row = new boolean[n];
        // Create column marker array
        boolean[] col = new boolean[m];

        // First pass: mark rows and columns that need to be zeroed
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // If element is zero, mark its row and column
                if (matrix[i][j] == 0) {
                    row[i] = true;
                    col[j] = true;
                }
            }
        }

        // Second pass: set cells to zero based on markers
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // If the row or column is marked, set cell to zero
                if (row[i] || col[j]) {
                    matrix[i][j] = 0;
                }
            }
        }
    }
}



// ----------------- optimal ------------
// Check if the first row has any zero and store in a boolean flag.
// Check if the first column has any zero and store in a boolean flag.
// First pass: Mark zeros in the first row and column for any zero found in the rest of the matrix.
// Second pass: Traverse again (excluding first row and column), setting cells to zero if their row marker or column marker is zero.
// Finally, update the first row and first column based on the stored flags.

class Solution {
    // Function to set entire row and column to 0 if an element in the matrix 
    public void setZeroes(int[][] matrix) {
        // Get dimensions of matrix
        int m = matrix.length;
        int n = matrix[0].length;
      
        boolean firstRowZero = false;  // Flag to track if first row should be zeroed
        boolean firstColZero = false;  // Flag to track if first column should be zeroed

        // Check if first row has any zero
        for (int j = 0; j < n; j++) {
            if (matrix[0][j] == 0) {
                firstRowZero = true;
                break;
            }
        }

        // Check if first column has any zero
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0) {
                firstColZero = true;
                break;
            }
        }

        // Use first row/column as markers
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        // Set cells to zero based on markers
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        // Zero the first row if needed
        if (firstRowZero) {
            for (int j = 0; j < n; j++) {
                matrix[0][j] = 0;
            }
        }

        // Zero the first column if needed
        if (firstColZero) {
            for (int i = 0; i < m; i++) {
                matrix[i][0] = 0;
            }
        }
    }
}
