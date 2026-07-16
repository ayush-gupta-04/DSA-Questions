// TC = O(13)
// SC = O(13)

// for every symbol .. add that while you can
class Solution {
    public String intToRoman(int num) {
        String[][] arr = {
            {"1000","M"}, {"900","CM"}, {"500","D"}, {"400","CD"},
            {"100","C"}, {"90","XC"}, {"50","L"}, {"40","XL"},
            {"10","X"}, {"9","IX"}, {"5","V"}, {"4","IV"}, {"1","I"}
        };


        StringBuilder ans = new StringBuilder();
        for(String[] pair : arr){
            int val = Integer.parseInt(pair[0]);
            String ch = pair[1];
            while(num >= val){
                num -= val;
                ans.append(ch);
            }
        }

        return ans.toString();
    }
}
