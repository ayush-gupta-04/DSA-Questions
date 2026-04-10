// Taking input from input.txt
// Giving output int output.txt
// Using Scanner
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        System.setOut(new PrintStream(new FileOutputStream("output.txt")));
        Scanner sc = new Scanner(System.in);


        // Code
        

        
        // Code End
        sc.close();
    }
}



// ---------------------------------------------------------------------------------------------------------------------------------------------------------
// Taking input from input.txt
// Giving output int output.txt
// Using bufferReader ...
// &
// CP template



import java.io.*;
import java.util.*;

// Reading a single Word (String): 
// - String s = next();

// Reading a single Integer or Long: 
// - int n = Integer.parseInt(next());
// - long m = Long.parseLong(next());

// Reading an entire line of text (including spaces):
// - String fullLine = br.readLine();

// If you need to read a full line of text after reading integers, 
// call br.readLine() once to consume the leftover newline, 
// and then call it again to get your actual text.

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static PrintWriter out;
    static boolean ONLINE_JUDGE = false; 

    static String next() throws IOException {
        while (st == null || !st.hasMoreElements()) {
            String line = br.readLine();
            if (line == null) return null; // Handles End of File smoothly
            st = new StringTokenizer(line);
        }
        return st.nextToken();
    }
    public static void main(String[] args) throws Exception {
        if(ONLINE_JUDGE){
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }else{
            br = new BufferedReader(new FileReader("input.txt"));
            out = new PrintWriter(new FileWriter("output.txt"));
        }
        
        // ==========================================


        int t = Integer.parseInt(next());
        while(t > 0){
            solve(br,out);
            t--;
        }
        
        
        // ==========================================
        out.flush();
        out.close();
        br.close();
    }


    public static void solve(BufferedReader br, PrintWriter out)  throws Exception {
        int a = Integer.parseInt(next());
        out.println(a);
    }
}
