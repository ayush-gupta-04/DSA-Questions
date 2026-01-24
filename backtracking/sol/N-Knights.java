package RecurMediumQuestions;


public class Nknights {


   static void print(boolean[][] grid){
       for(boolean[] arr : grid){
           for(boolean a : arr){
               if(a){
                   System.out.print(" K ");
               }else{
                   System.out.print(" O ");
               }
           }
           System.out.println();
       }
       System.out.println("\n");


   }




   static boolean canPut(boolean[][] grid, int row, int col){
       //checking most upper left
       if(isValid(grid,row - 2,col - 1)){
           if(grid[row - 2][col - 1]){
               return false;
           }
       }
       //checking most upper right
       if(isValid(grid,row - 2,col + 1)){
           if(grid[row - 2][col + 1]){
               return false;
           }
       }
       //checking upper left
       if(isValid(grid,row - 1,col - 2)){
           if(grid[row - 1][col - 2]){
               return false;
           }
       }
       //checking upper right
       if(isValid(grid,row - 1,col + 2)){
           if(grid[row - 1][col + 2]){
               return false;
           }
       }


       return true;
   }


   static boolean isValid(boolean[][] grid,int row, int col){
       if(row >= 0 && row < grid.length && col >= 0 && col < grid[0].length){
           return true;
       }


       return false;
   }
   static int ways(boolean[][] grid, int row, int col,int n){
       int count = 0;
       if(n == 0){
           print(grid);
           System.out.println();
           return 1;
       }


       if(row == grid.length){
           return 0;
       }


       if(col == grid[0].length){
           return ways(grid,row + 1,0,n);
       }


       if(canPut(grid,row,col)){
           grid[row][col] = true;
           count += ways(grid,row,col + 1,n - 1);
           grid[row][col] = false;
       }
       count += ways(grid,row,col + 1,n);
       return count;


   }
   public static void main(String[] args) {
       boolean[][] grid = new boolean[3][3];
       int ans = ways(grid,0,0,3);
       System.out.println(ans);
   }
}

