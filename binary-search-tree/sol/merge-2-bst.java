// time : 4*N
// space : 2*N


class Solution {
    ArrayList<Integer> merge(ArrayList<Integer> list1 , ArrayList<Integer> list2){
        int i = 0;
        int j = 0;
        ArrayList<Integer> ans = new ArrayList<>();
        while(i < list1.size() && j < list2.size()){
            if(list1.get(i) < list2.get(j)){
                ans.add(list1.get(i));
                i++;
            }else{
                ans.add(list2.get(j));
                j++;
            }
        }
        
        while(i < list1.size()){
            ans.add(list1.get(i));
            i++;
        }
        while(j < list2.size()){
            ans.add(list2.get(j));
            j++;
        }
        return ans;
    }
    
    void inorder(Node node , ArrayList<Integer> list){
        if(node == null) return;
        inorder(node.left , list);
        list.add(node.data);
        inorder(node.right , list);
    }
    public ArrayList<Integer> merge(Node root1, Node root2) {
        ArrayList<Integer> list1 = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();
        inorder(root1 , list1);
        inorder(root2 , list2);
        return merge(list1,list2);
    } 
}




// ----------- Optimal -----------

public class Solution {
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer> result = new ArrayList<>();
        Deque<TreeNode> stack1 = new ArrayDeque<>();
        Deque<TreeNode> stack2 = new ArrayDeque<>();

        TreeNode curr1 = root1;
        TreeNode curr2 = root2;

        while (curr1 != null || curr2 != null || !stack1.isEmpty() || !stack2.isEmpty()) {
            // Push left spine of tree 1
            while (curr1 != null) {
                stack1.push(curr1);
                curr1 = curr1.left;
            }
            // Push left spine of tree 2
            while (curr2 != null) {
                stack2.push(curr2);
                curr2 = curr2.left;
            }

            // Pick the smaller element among the two stack tops
            if (stack2.isEmpty() || (!stack1.isEmpty() && stack1.peek().val <= stack2.peek().val)) {
                curr1 = stack1.pop();
                result.add(curr1.val);
                curr1 = curr1.right;
            } else {
                curr2 = stack2.pop();
                result.add(curr2.val);
                curr2 = curr2.right;
            }
        }

        return result;
    }
}
