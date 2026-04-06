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




// Taking input from input.txt
// Giving output int output.txt
// Using bufferReader ...
// &
// CP template

import java.io.*;
import java.util.*;


public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static PrintWriter out;
    static boolean ONLINE_JUDGE = true;

    public static void main(String[] args) throws Exception {
        if(ONLINE_JUDGE){
            br = new BufferedReader(new InputStreamReader(System.in));
        }else{
            br = new BufferedReader(new FileReader("input.txt"));
            out = new PrintWriter(new FileWriter("output.txt"));
        }
        
        // CODE

        
        


        // -------------
        
        if(ONLINE_JUDGE){
            br.close();
        }else{
            out.flush();
            out.close();
            br.close();
        }
    }
}
