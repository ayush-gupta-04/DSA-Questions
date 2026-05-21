import java.util.ArrayList;
import java.util.List;

public class KMP {

    // 1️⃣ The Prefix Function (LPS Array)
    public static int[] prefixFunction(String s) {
        int n = s.length();
        int[] pi = new int[n];
        
        for (int i = 1; i < n; i++) {
            int j = pi[i - 1];
            while (j > 0 && s.charAt(i) != s.charAt(j)) {
                j = pi[j - 1];
            }
            if (s.charAt(i) == s.charAt(j)) {
                j++;
            }
            pi[i] = j;
        }
        return pi;
    }

    // 2️⃣ The KMP Search Function
    public static List<Integer> kmpSearch(String s, String text) {
        int n = text.length();
        int m = s.length();
        int[] pi = prefixFunction(s);
        List<Integer> occurrences = new ArrayList<>();

        for (int i = 0, j = 0; i < n; i++) {
            while (j > 0 && text.charAt(i) != s.charAt(j)) {
                j = pi[j - 1];   // Jump using the LPS array
            }
            if (text.charAt(i) == s.charAt(j)) {
                j++;
            }
            if (j == m) {
                // 0-indexing match found!
                occurrences.add(i - m + 1);  
                j = pi[j - 1];   // Continue searching for more matches
            }
        }
        return occurrences;
    }

    // 3️⃣ Main Method to test
    public static void main(String[] args) {
        String s = "aba";
        String text = "cdabcabababanaba";
        List<Integer> ans = kmpSearch(s, text);
        
        System.out.println("Pattern found at indices:");
        for (int it : ans) {
            System.out.print(it + " ");
        }
    }
}
