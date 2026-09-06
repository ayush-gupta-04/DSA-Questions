class Solution {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dummy = new ListNode();
        dummy.next = head;

        int skip = left-1;
        ListNode last = dummy;

        while(skip-- > 0){
            last = last.next;
        }

        // last have reached just before left;
        ListNode r = last;
        int cntToRight = right-left+1;

        while(cntToRight-- > 0 ){
            r = r.next;
        }

        // r is at right;
        // last is just before left;

        // reverse the sub-list
        ListNode curr = last.next;  // at left
        ListNode prev = r.next;     // just after right;
        int rev = right-left+1;
        while(rev-- > 0){
            ListNode next = curr.next;
            curr.next = prev;
            prev=curr;
            curr=next;
        }

        // i have reversed the group;
        last.next = r;
        return dummy.next;
    }
}
