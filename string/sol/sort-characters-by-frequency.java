// ----------------- optimal ----------------
// time : N + (256*log(256))
// space : O(256)

class Solution {
    public String frequencySort(String s) {
        int n = s.length();
        int[][] map = new int[256][2];    // ith index will comtains [ascii-val , freq].

        for(int i = 0;i < 256;i++){
            map[i][0] = i;
            map[i][1] = 0;
        }


        for(int i = 0; i< n; i++){
            int ch  = (int)s.charAt(i);   // ascii of ch
            map[ch][1]++;
        }

        Arrays.sort(map,(x,y) -> Integer.compare(y[1],x[1]));

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 256; i++){
            if(map[i][1] > 0){
                char ch = (char)map[i][0];
                int freq = map[i][1];
                sb.repeat(ch, freq);
            }
        }
        return sb.toString();
    }
}
