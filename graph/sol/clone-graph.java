class Solution {
    Node clone(Node node , HashMap<Node, Node> vis){

        Node copyNode = new Node(node.val);
        vis.put(node , copyNode);

        for(Node neigh : node.neighbors){
            if(!vis.containsKey(neigh)){   // it will return the neigh copy node.
                Node copyNeigh = clone(neigh, vis);
                copyNode.neighbors.add(copyNeigh);
            }else{
                // i have visited the neigh;
                // i must have neigh's copy node in vis.
                // just add the neigh copy node to the neghbours.
                copyNode.neighbors.add(vis.get(neigh));
            }
        }

        return copyNode;
    }
    public Node cloneGraph(Node node) {
        if(node == null) return node;

        HashMap<Node, Node> vis = new HashMap<>();
        return clone(node, vis);
    }
}
