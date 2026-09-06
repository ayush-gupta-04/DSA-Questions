class Solution {
    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null) return head;

        // dummy node to handle edge cases.
        // last = just before the first node.
        // curr = first node;
        // currNext = second node;
        // next = just after the second node;
        
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode last = dummy;

        while(true){
            if(last.next==null || last.next.next == null) break;

            // i am 100% sure that curr != null && currNext != null;
            ListNode curr = last.next;
            ListNode currNext = last.next.next;  
            ListNode next = currNext.next;

            currNext.next = curr;
            curr.next = next;
            last.next = currNext;

            last = curr;
        }

        return dummy.next;
    }
}
