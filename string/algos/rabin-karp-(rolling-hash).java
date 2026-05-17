// Rabin-Karp-Algorithm : Standard Algo.
// Formula for Hash : 
//     - hash of ABC = A*(p^2) + B*(p^1) + C*(p^0)

// What we do here ?
// If we have a window size K ... then we precalculate POW = p^(K-1) % mod
// We calculate the first window .. then we slide.
// we slide a window .. remove the old char and add the new char to the hash of the string.

// How to update the Hash for the next window : 
// POW = p^(K-1) % mod
// hash = (hash - oldChar*POW)%MOD;   // remove the old char.
// hash = (hash*p + newChar)%MOD;     // add the new char.
  

import java.util.*;

public class Main {
    static List<Integer> rabinKarp(String txt , String pattern){
        List<Integer> ans = new ArrayList<>();
        long MOD = 1_000_000_009L;
        long p = 31;

        int k = pattern.length();
        int n = txt.length();

        long patternHash = 0L;
        long textHash = 0L;

        long POW = 1L;
        for(int i = 0;i < k-1;i++){
            POW = (POW * p)%MOD;
        }

        for(int i = 0;i < k ;i++){
            patternHash = (patternHash*p + pattern.charAt(i)-'a' + 1)%MOD;
            textHash = (textHash*p + txt.charAt(i)-'a' + 1)%MOD;
        }

        for(int i = 0;i <= n-k; i++){
            if(patternHash == textHash){
                boolean match = true;
                for(int j = 0;j < k ;j++){
                    if(txt.charAt(i+j) != pattern.charAt(j)){
                        match = false;
                        break;
                    }
                }
                if(match){
                    ans.add(i);
                    System.out.println("Pattern found at " + (i));
                }
            }

            if(i+k==n) continue;

            int oldCh = txt.charAt(i)-'a' + 1;
            int newCh = txt.charAt(i+k) - 'a' + 1;
            textHash = (textHash - (oldCh*POW)%MOD + MOD)%MOD;
            textHash = (textHash*p + newCh)%MOD;
        }

        return ans;
    }
    public static void main(String[] args) {
        
        String t = "ayushayush";
        String s = "ayu";
        System.out.println(rabinKarp(t, s));
    }
}

    







// Rabin-Karp-Algorithm : CP Algorithm.
// Formula for hash : 
//     - hash of ABC = A*(p^0) + B*(p^1) + C*(p^2)

// What we do here ?
// - precomputes the powers of p to the size of txt.
// - build the prefixHash of txt.

// - hash[i:j] -> hash of substring from i to j will be
//             -> (prefixHash[j] - prefixHash[i-1])/(p^i)
//             -> we never divide by (p^i) . . . instead we compare by multiplication.

// Pros and Cons : 
// Cons : 
// - not standard.
// - we never get the real hash of the substring.
// - we use 2 extra arrays ... one for powers of p ... next for prefixHash.
// Pros : 
// - Use to solve harder problems.
// - to compare any substring it just takes O(1) time.

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        String txt = "ayushayushayush";
        String str = "ayush";
        int s = str.length();
        int t = txt.length();
        long p = 31;
        long MOD = 1_000_000_009L;
        List<Integer> ans = new ArrayList<>();
        


        // precompute powers of p to size of txt.
        long[] powP = new long[t];
        powP[0] = 1;
        for(int i = 1;i < t ;i++){
            powP[i] = (powP[i-1] * p)%MOD;
        }

        // calc hash of str.
        long hashOfStr = 0;
        for(int i = 0;i < s ; i++){
            hashOfStr = (hashOfStr + (str.charAt(i)-'a' + 1)*powP[i])%MOD;
        }

        // calc prefixHash of txt.
        long[] prefixHash = new long[t];
        prefixHash[0] = (txt.charAt(0)-'a'+1);
        for(int i = 1;i < t; i++){
            prefixHash[i] = (prefixHash[i-1] + (txt.charAt(i)-'a'+1)*powP[i])%MOD;
        }

        // check for every substring
        for(int i = 0;i + s - 1 < t ; i++){
            long window = (prefixHash[i+s-1] - (i-1 >= 0 ?prefixHash[i-1]:0) + MOD)%MOD;
            if(window == (hashOfStr*powP[i])%MOD){
                ans.add(i);
            }
        }
        System.out.println(ans);
    }
}

