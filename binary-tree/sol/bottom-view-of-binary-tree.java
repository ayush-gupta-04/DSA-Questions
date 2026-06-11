// time : NlogN
// space : N

// we will have a treemap of col -> node.
// we want bottom most .. therefore we processed nodes from top to bottom..level wise.
// if i have mapped col -> node1 .. and i saw col -> node2 .. i will defenately update the mapp to col -> node2,
//   because my priority is bottom node.

// just the same as top-view.


class Data{
    Node node;
    int row;
    int col;
    public Data(Node node , int r, int c){
        this.node = node;
        this.row = r;
        this.col = c;
    }
}

class Solution {
    public ArrayList<Integer> bottomView(Node root) {
        TreeMap<Integer,Integer> colToNode = new TreeMap<>();
        Deque<Data> q = new ArrayDeque<>();
        
        q.offerLast(new Data(root,0,0));
        
        while(!q.isEmpty()){
            Data data = q.pollFirst();
            Node node = data.node;
            int row = data.row;
            int col = data.col;
            
            // there will be no check here.
            colToNode.put(col , node.data);
            
            if(node.left != null){
                q.offerLast(new Data(node.left , row + 1 , col-1));
            }
            if(node.right != null){
                q.offerLast(new Data(node.right , row + 1, col+1));
            }
        }
        
        ArrayList<Integer> list = new ArrayList<>();
        for(Map.Entry<Integer,Integer> e : colToNode.entrySet()){
            list.add(e.getValue());
        }
        
        return list;
        
    }
}
