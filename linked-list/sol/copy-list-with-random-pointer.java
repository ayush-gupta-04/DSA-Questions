class Solution {
    public Node copyRandomList(Node head) {

        // insert copy nodes between org nodes.
        // make triangle structure.
        Node curr = head;
        while(curr != null){
            Node newNode = new Node(curr.val);
            newNode.next = curr.next;
            curr.next = newNode;
            curr = curr.next.next;
        }

        // connect random pointers of copy list.
        curr = head;
        while(curr != null){
            curr.next.random = (curr.random == null) ? null : curr.random.next;
            curr = curr.next.next;
        }


        // connect copy list next pointers.
        // & restore original list next pointers.
        Node dummy = new Node(-1);
        Node mv = dummy;
        curr = head;

        while(curr != null){
            mv.next = curr.next;
            curr.next = curr.next.next;
            curr = curr.next;
            mv = mv.next;
        }

        return dummy.next;

        
    }
}
