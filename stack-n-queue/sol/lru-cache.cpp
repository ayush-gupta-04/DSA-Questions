import java.util.HashMap;
import java.util.Map;

class LRUCache {

    // Doubly Linked List Node
    class Node {
        int key;
        int val;
        Node prev;
        Node next;

        Node(int k, int v) {
            key = k;
            val = v;
        }
    }

    private int cap;
    private int size;
    private Node head;
    private Node tail;
    private Map<Integer, Node> map;

    public LRUCache(int capacity) {
        cap = capacity;
        size = 0;
        map = new HashMap<>();

        head = new Node(-1, -1);
        tail = new Node(-1, -1);

        head.next = tail;
        tail.prev = head;
    }

    // remove node from list
    private void deleteNode(Node node) {
        Node prevNode = node.prev;
        Node nextNode = node.next;

        prevNode.next = nextNode;
        nextNode.prev = prevNode;

        node.prev = null;
        node.next = null;
    }

    // insert right after head (most recently used)
    private void insertAfterHead(Node node) {
        Node nextNode = head.next;

        head.next = node;
        node.prev = head;

        node.next = nextNode;
        nextNode.prev = node;
    }

    public int get(int key) {
        if (!map.containsKey(key)) return -1;

        Node node = map.get(key);
        deleteNode(node);
        insertAfterHead(node);

        return node.val;
    }

    public void put(int key, int value) {

        // key exists → update + move to front
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.val = value;

            deleteNode(node);
            insertAfterHead(node);
            return;
        }

        // capacity full → remove LRU (tail.prev)
        if (size == cap) {
            Node lastNode = tail.prev;

            deleteNode(lastNode);
            map.remove(lastNode.key);
            size--;
        }

        Node newNode = new Node(key, value);
        map.put(key, newNode);
        insertAfterHead(newNode);
        size++;
    }
}
