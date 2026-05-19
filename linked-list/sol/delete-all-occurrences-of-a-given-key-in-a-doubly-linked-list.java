// time : N
// space : 1

class Solution {
    static Node deleteNode(Node head , Node node){
        if(node.next == null && node.prev == null){
            return null;
        }
        
        // start of the DLL;
        if(node.prev == null){
            head = node.next;
            node.next = null;
            head.prev = null;
            return head;
        }
            
        // end of the DLL;    
        if(node.next == null){
            node.prev.next = null;
            node.prev = null;
            return head;
        }
        
        // middle of the DLL;
        Node prev = node.prev;
        Node next = node.next;
        prev.next = next;
        next.prev = prev;
        
        return head;
        
    }
    static Node deleteAllOccurOfX(Node head, int x) {
        Node curr = head;
        while(curr != null){
            Node next = curr.next;
            if(curr.data == x){
                head = deleteNode(head,curr);
            }
            curr = next;
        }
        
        return head;
        
    }
}
