// Data Structures : 
// 1. HashMap of {key : Integer , node : Node} .. to know whether a key exist or not.
// 2. HashMap of {freq : Integer , list : DoublyLinkedList} .. to store the elements freq wise.


// DoublyLinkedList :
// - It will store elements in LRU style.
// - Implements : 
//     - insertAfterHead(node)
//     - deleteNode(node)

class Node{
    int key;
    int val;
    int freq;
    Node next;
    Node prev;
    public Node(int k , int v){
        this.key = k;
        this.val = v;
        this.freq = 1;
    }
    public Node(){
        this.key = -1;
        this.val = -1;
        this.freq = 1;
    }
}



class DoublyLinkedList{
    Node head;
    Node tail;
    int size;

    public DoublyLinkedList(){
        this.head = new Node();
        this.tail = new Node();
        this.size = 0;
        head.next = tail;
        tail.prev = head;
    }

    public void insertAfterHead(Node node){
        Node nextNode = head.next;
        head.next = node;
        node.prev = head;
        node.next = nextNode;
        nextNode.prev = node;
        size++;
    }

    public void deleteNode(Node node){
        Node prevNode = node.prev;
        Node nextNode = node.next;
        prevNode.next = nextNode;
        nextNode.prev = prevNode;
        node.prev = null;
        node.next = null;
        size--;
    }
}


class LFUCache {
    int capacity;
    int size;
    int minFreq;
    HashMap<Integer , DoublyLinkedList> freqToDLL;
    HashMap<Integer , Node> keyToNode;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.minFreq = 0;
        freqToDLL = new HashMap<>();
        keyToNode = new HashMap<>();
    }

    private void updateFreqListMap(Node node){
        // AIM :
        // - to delete the node from it's current freq list.
        // - to insert this node into next freq list at the front;
        
        // ALGO : 
        // - delete the node from it's current freq list.
        // - if node was in minFreqList and now it is empty .. then do minFreq++;
        // - increment the node's freq.
        // - take the nextFreqList and insert the node after the head. 

        freqToDLL.get(node.freq).deleteNode(node);
        if(node.freq == minFreq && freqToDLL.get(node.freq).size == 0){
            minFreq++;
        }
        DoublyLinkedList nextFreqList = new DoublyLinkedList();
        if(freqToDLL.containsKey(node.freq + 1)){
            nextFreqList = freqToDLL.get(node.freq + 1);
        }
        node.freq++;
        nextFreqList.insertAfterHead(node);
        freqToDLL.put(node.freq , nextFreqList);
    }
    
    public int get(int key) {
        // - if key exits .. then updateFreqListMap of this node.
        if(keyToNode.containsKey(key)){
            Node node = keyToNode.get(key);
            updateFreqListMap(node);
            return node.val;
        }
        return -1;
    }
    
    public void put(int key, int value) {
        // - if key exits .. then updateFreqListMap of this node.
        if(keyToNode.containsKey(key)){
            Node node = keyToNode.get(key);
            node.val = value;
            updateFreqListMap(node);
            return;
        }


        // if cap is full : 
        // - take the DLL of minFreq.
        // - remove the lruNode in this DLL.
        // - remove this from the keyToNode also.
        if(size == capacity){
            DoublyLinkedList list = freqToDLL.get(minFreq);
            Node lruNode = list.tail.prev;
            keyToNode.remove(lruNode.key);
            list.deleteNode(lruNode);
            size--;
        }


        // since i am adding a fresh node .. minFreq will be 1 for sure.
        // take the DLL of the freq 1.
        // make a new Node and add this node in front of the DLL.
        // add the key,node to keyToNode also.
        // size++;

        minFreq = 1;
        DoublyLinkedList newDLL = new DoublyLinkedList();
        if(freqToDLL.containsKey(minFreq)){
            newDLL = freqToDLL.get(minFreq);
        }
        Node newNode = new Node(key , value);
        newDLL.insertAfterHead(newNode);
        keyToNode.put(key , newNode);
        freqToDLL.put(minFreq,newDLL);
        size++;
    }
}
