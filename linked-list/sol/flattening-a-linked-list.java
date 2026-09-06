// ------------ Recursion ---------------
// time : N*K ... N is nodes vertically....K is nodes horizontally

class Solution {
    Node merge(Node l1, Node l2){
        Node dummy = new Node(-1);
        Node curr = dummy;
        
        while(l1 != null && l2 != null){
            if(l1.data < l2.data){
                curr.bottom = l1;
                curr = curr.bottom;
                l1 = l1.bottom;
            }else{
                curr.bottom = l2;
                curr = curr.bottom;
                l2 = l2.bottom;
            }
            curr.next = null;
        }
        curr.bottom = (l1==null) ? l2 : l1;
        dummy.next = null;
        return dummy.bottom;
    }
    public Node flatten(Node head) {
        if(head == null || head.next == null) return head;
        
        Node flatHead = flatten(head.next);
        
        // break the next-link;
        head.next = null;
        // head & flatHead are independent now;
        
        Node merged = merge(head, flatHead);
        
        return merged;
    }
}




// ---------- priority queue : K-way merge -------------
// time : N*logK
// space : K


class Solution {
    public Node flatten(Node head) {
        if(head == null || head.next == null) return head;
        
        Node dummy = new Node(-1);
        Node curr = dummy;
        
        PriorityQueue<Node> pq = new PriorityQueue<Node>((x,y) -> Integer.compare(x.data, y.data));
        
        // add every list-head to the pq;
        Node temp = head;
        while(temp != null){
            pq.offer(temp);
            temp = temp.next;
        }
        
        
        while(!pq.isEmpty()){
            Node node = pq.poll();
            
            curr.bottom = node;
            curr = curr.bottom;
            if(node.bottom != null){
                pq.offer(node.bottom);
            }
            curr.next = null;    // ensure no dangling next pointer.
        }
        
        dummy.next = null;
        return dummy.bottom;
    }
}
