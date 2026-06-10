// time : N * logN * logN + N * logN
// - N * logN * logN : for DFS.
// - N * logN : for map traversal...logN for sorting.

// space : N + logN
// - N : for storing nodes in the map.
// - logN : for recursion stack.
class Solution {
    public void solve(TreeNode node ,int row , int col, TreeMap<Integer , TreeMap<Integer,List<Integer>>> map){
        if(node == null){
            return;
        }
        map.computeIfAbsent(col, k -> new TreeMap<>()).computeIfAbsent(row , k -> new ArrayList<>()).add(node.val);
        solve(node.left,row+1, col-1 , map);
        solve(node.right,row + 1, col+1 , map);
    }
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        TreeMap<Integer , TreeMap<Integer,List<Integer>>> map = new TreeMap<>();
        solve(root,0,0,map);

        List<List<Integer>> ans = new ArrayList<>();
        for(Map.Entry<Integer , TreeMap<Integer,List<Integer>>> e : map.entrySet()){
            List<Integer> list = new ArrayList<>();
            TreeMap<Integer,List<Integer>> rowToNodes = e.getValue();

            // rows will also be sorted.
            for(Map.Entry<Integer,List<Integer>> entry : rowToNodes.entrySet()){
                List<Integer> nodes = entry.getValue();
                Collections.sort(nodes);
                list.addAll(nodes);
            }
            ans.add(list);
        }   
        return ans;
    }
}
