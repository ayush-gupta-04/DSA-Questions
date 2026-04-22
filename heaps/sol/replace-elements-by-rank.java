import java.util.*;

class Solution {
    // Function to replace elements by their rank in the array
    public int[] replaceWithRank(int[] arr) {
        // Create a copy of the original array
        int[] sortedArr = arr.clone();

        // Sort the copied array
        Arrays.sort(sortedArr);

        // Map to store rank of each unique number
        HashMap<Integer, Integer> rankMap = new HashMap<>();

        int rank = 1;

        // Assign rank to each unique number
        for (int num : sortedArr) {
            if (!rankMap.containsKey(num)) {
                rankMap.put(num, rank);
                rank++;
            }
        }

        // Replace elements in original array with their ranks
        int[] result = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            result[i] = rankMap.get(arr[i]);
        }

        return result;
    }
}

// Driver code
class Main {
    public static void main(String[] args) {
        Solution obj = new Solution();
        int[] arr = {1, 5, 8, 15, 8, 25, 9};

        int[] res = obj.replaceWithRank(arr);

        for (int x : res) {
            System.out.print(x + " ");
        }
    }
}
