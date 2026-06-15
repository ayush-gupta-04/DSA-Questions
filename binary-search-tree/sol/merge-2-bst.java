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
