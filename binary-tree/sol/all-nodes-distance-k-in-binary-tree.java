// time : 2*N
// - N : to assign parent.
// - N : level order BFS.
// space : 4*N
// - N : for node to parent
// - N : for vis.
// - N : for queue.
// - N : for ans.
class Solution {
    void assignParent(TreeNode root , HashMap<TreeNode , TreeNode> map){
        // Level order traversal and mark the parents.
        Queue<TreeNode> Q = new LinkedList<>();
        Q.add(root);
        while(!Q.isEmpty()){
            TreeNode node = Q.remove();
            if(node.left != null){
                Q.add(node.left);
                map.put(node.left , node);
            }

            if(node.right != null){
                Q.add(node.right);
                map.put(node.right , node);
            }
        }

        return;
    }
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        HashMap<TreeNode , TreeNode> map = new HashMap<>();
        assignParent(root,map);
        int dist = 0;
        HashSet<TreeNode> visited = new HashSet<>();
        Deque<TreeNode> Q = new ArrayDeque<>();

        Q.offerLast(target);
        visited.add(target);

        while(dist != k){
            int size = Q.size();

            for(int i = 0; i < size ; i++){
                TreeNode node = Q.pollFirst();
                
                //move radially outward from node.

                //add node.left only if it is not null and not visited.
                if(node.left != null && !visited.contains(node.left)){
                    Q.offerLast(node.left);
                    visited.add(node.left);
                }

                //add node.right only if it is not null and not visited.
                if(node.right != null && !visited.contains(node.right)){
                    Q.offerLast(node.right);
                    visited.add(node.right);
                }


                //add node's parent only is it is not null and not visited.
                TreeNode parent = map.get(node);
                if(parent != null && !visited.contains(parent)){
                    Q.offerLast(parent);
                    visited.add(parent);
                }
            }
            dist = dist + 1;
        }

        List<Integer> list = new ArrayList<>();
        while(!Q.isEmpty()){
            list.add(Q.pollFirst().val);
        }
        return list;
    }
}
