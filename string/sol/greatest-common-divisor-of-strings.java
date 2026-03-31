// TC -> O(N+M)
// SC -> O(1)
// if gcd is possible .. then just find the gcd of the str1 and str2.

class Solution {
    int gcd(int a, int b){
        if(b==0) return a;
        return gcd(b , a%b);
    }
    public String gcdOfStrings(String str1, String str2) {
        int n1 = str1.length();
        int n2 = str2.length();
        if(!(str1 + str2).equals(str2 + str1)){
            return "";
        }

        int g = gcd(n1,n2);
        return str1.substring(0,0+g);
    }
}
