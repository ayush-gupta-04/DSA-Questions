// time : N
// space : N
class Solution {
    public static ArrayList<ArrayList<Integer>> findPairsWithGivenSum(int target,
                                                                      Node head) {
        // code here
        if(head == null || head.next == null) return new ArrayList<>();
        Node end = head;
        
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        
        while(end.next != null){
            end = end.next;
        }
            
        Node start = head;
        while(start != end){
            int sum = start.data + end.data;
            if(sum == target){
                ArrayList<Integer> list  = new ArrayList<>();
                list.add(start.data);
                list.add(end.data);
                ans.add(list);
                
                start = start.next;
                if(start==end) break;
            }else if(sum > target){
                end = end.prev;
            }else{
                start=start.next;
            }
            if(start==end) break;
        }
        
        return ans;
        
    }
}
