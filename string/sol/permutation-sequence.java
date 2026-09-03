class Solution {
    int fact(int n){
        if(n<=1) return n;
        return n * fact(n-1);
    }
    public String getPermutation(int n, int k) {
        if(n == 1) return "1";
        List<Character> chars = new ArrayList<>();   // just to reference the chars we have.
        for(int i = 0;i < n;i++){
            chars.add((char)('1' + i));
        }
        StringBuilder sb = new StringBuilder();
        int mod = fact(n-1);
        int div = n-1;
        k--;

        List<Integer> list = new ArrayList<>();
        while(div != 0){
            int a = k/mod;  // block index.
            int b = k%mod;  // new k for next block.
            sb.append(chars.get(a));
            chars.remove(a);
            k = b;
            mod = mod/div;
            div--;
        }
        sb.append(chars.get(0));

        return sb.toString();

    }
}
