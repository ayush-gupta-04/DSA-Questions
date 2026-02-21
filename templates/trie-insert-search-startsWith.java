class Trie{
    Node root;
    Trie(){
        this.root = new Node();
    }
    public void insert(String word){
        Node node = this.root;
        for(char ch : word.toCharArray()){
            //if the node does'nt contains the key , i.e key points to null
            // make the key point to a ref trie.
            if(!node.containsKey(ch)){
                node.put(ch);
            }
            //move to the ref trie of the key.
            node = node.get(ch);
        }
        //in the end set the flag to true.
        node.setEnd();
    }

    public boolean search(String word){
        Node node = root;
        for(char ch : word.toCharArray()){
            //if the node doesn't contains the key then just return false.
            if(!node.containsKey(ch)) return false;
            //if contains then move to the ref of the key.
            node = node.get(ch);
        }

        //return true only if flag is true...
        //flaf true denotes that it is end and no word is there down the line.
        return node.isEnd();
    }

    public boolean startsWith(String prefix){
        //same as search .. but in the end we don't care about the flag.
        Node node = root;
        for(char ch : prefix.toCharArray()){
            if(!node.containsKey(ch)) return false;
            node = node.get(ch);
        }
        return true;
    }


}

class Node {
    Node[] links = new Node[26];
    boolean flag = false;

    boolean containsKey(char ch){
        return (links[ch - 'a'] != null);
    }

    void put(char ch){
        links[ch - 'a'] = new Node();
    }

    Node get(char ch){
        return links[ch - 'a'];
    }

    void setEnd(){
        this.flag = true;
    }

    boolean isEnd(){
        return flag;
    }
}
