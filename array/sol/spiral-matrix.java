// time : N*M
// space : 1

class Solution {
    public List<Integer> spiralOrder(int[][] nums) {
        int row = nums.length;
        int col = nums[0].length;
        int startCol = 0;
        int endCol = col-1;
        int startRow = 0;
        int endRow = row-1;
        int count = 0;
        
        ArrayList<Integer> ans = new ArrayList<>();
        while(count != row*col){
            //printing upper row;
            for(int i = startCol; i <= endCol && count<row*col ; i++){
                ans.add(nums[startRow][i]);
                count++;
            }
            startRow++;
            //printing last column
            for(int i = startRow ; i<=endRow && count<row*col; i++){
                ans.add(nums[i][endCol]);
                count++;
            }
            endCol--;
            //printing lower row
            for(int i = endCol ; i>=startCol && count<row*col; i--){
                ans.add(nums[endRow][i])  ;
                count++;
            }
            endRow--;
            //printing starting column
            for(int i = endRow ; i >= startRow && count<row*col; i--){
                ans.add(nums[i][startCol]) ;
                count++;
            }
            startCol++;
        }
        return ans;
    }
}
