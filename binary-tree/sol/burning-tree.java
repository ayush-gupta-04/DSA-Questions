// time : 3*N
//  - N : assign parent
//  - N : level order BFS.
//  - N : find
// space : 3*N
// - N : map for parent
// - N : queue
// - N : vis 

// ultimate work :
// - from target node .. move radially outward and find number of levels.
// - exactly same as nodes at a dist k from target node.

class Solution {
    void assignParent(Node root , HashMap<Node,Node> map){
        // Level order traversal and mark the parents.
        Deque<Node> Q = new ArrayDeque<>();
        Q.offerLast(root);
        while(!Q.isEmpty()){
            Node node = Q.pollFirst();
            if(node.left != null){
                Q.offerLast(node.left);
                map.put(node.left , node);
            }

            if(node.right != null){
                Q.offerLast(node.right);
                map.put(node.right , node);
            }
        }

        return;
    }
    Node find(Node node , int k){
        if(node==null) return null;
        
        if(node.data == k) return node;
        
        Node left = find(node.left , k);
        if(left != null) return left;
        
        Node right = find(node.right , k);
        return right;
    }
    public int minTime(Node root, int target) {
        HashMap<Node,Node> map = new HashMap<>();
        Deque<Node> q = new ArrayDeque<>();
        HashSet<Node> vis = new HashSet<>();
        assignParent(root,map);
        
        Node tarNode = find(root, target);
        int level = 0;
        q.offerLast(tarNode);
        vis.add(tarNode);
        
        while(!q.isEmpty()){
            int size = q.size();
            
            for(int i = 0;i < size ;i++){
                Node node = q.pollFirst();
                
                if(node.left != null && !vis.contains(node.left)){
                    vis.add(node.left);
                    q.offerLast(node.left);
                }
                if(node.right != null && !vis.contains(node.right)){
                    vis.add(node.right);
                    q.offerLast(node.right);
                }
                Node parent = map.get(node);
                if(parent != null && !vis.contains(parent)){
                    q.offerLast(parent);
                    vis.add(parent);
                }
            }
            level++;
        }
        
        return level-1;
    }
}
