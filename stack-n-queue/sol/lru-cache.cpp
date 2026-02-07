class Node{ 
public:
    int key;
    int val;
    Node* prev;
    Node* next;
    Node(int k, int v){
        this->key = k;
        this->val = v;
    }
};

class LRUCache {
private:
    int cap;
    int size;
    Node* head;
    Node* tail;
    map<int,Node*> mp;

    void deleteNode(Node* node){
        Node* nextNode = node->next;
        Node* prevNode = node->prev;
        prevNode->next = nextNode;
        nextNode->prev = prevNode;
        node->next = nullptr;
        node->prev = nullptr;
    }
    void insertAfterHead(Node* node){
        Node* headNode = this->head;
        Node* nextNode = head->next;
        headNode->next = node;
        node->prev = headNode;
        node->next = nextNode;
        nextNode->prev = node;
    }
public:
    LRUCache(int capacity) {
        this->cap = capacity;
        this->size = 0;
        this->head = new Node(-1,-1);
        this->tail = new Node(-1,-1);
        head->next = tail;
        tail->prev = head;
    }
    
    int get(int key) {
        if(mp.find(key) == mp.end()) return -1;
        Node* node = mp[key];
        deleteNode(node);
        insertAfterHead(node);
        return node->val;
    }
    
    void put(int key, int value) {
        if(mp.find(key) != mp.end()){
            //key exist.
            Node* node = mp[key];
            node->val = value;
            deleteNode(node);
            insertAfterHead(node);
            return;
        }

        // either the cap is full ..
        // or we need to add more nodes.
        if(size == cap){
            Node* lastNode = this->tail->prev;
            deleteNode(lastNode);
            mp.erase(lastNode->key);
            size--;
        }

        Node* newNode = new Node(key,value);
        mp[key] = newNode;
        insertAfterHead(newNode);
        size++;
    }
};

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache* obj = new LRUCache(capacity);
 * int param_1 = obj->get(key);
 * obj->put(key,value);
 */
