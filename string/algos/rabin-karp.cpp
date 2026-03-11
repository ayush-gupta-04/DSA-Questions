import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        String txt = "ayushayushayush";
        String str = "ayush";
        int s = str.length();
        int t = txt.length();
        long p = 31;
        long MOD = (int)1e9+9;
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

