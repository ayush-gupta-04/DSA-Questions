// time : N
// space : 1
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode slow = head;
        ListNode fast = head;
        int cnt = 0;
        while(cnt != n){       // skip n nodes.
            fast = fast.next;
            cnt++;
        }
        
        ListNode prev = null;
        while(fast != null){
            prev = slow;
            slow = slow.next;
            fast = fast.next;
        }
        if(prev==null) return head.next;  // if prev==null means i have to remove the head.
        prev.next = slow.next;
        return head;
    }
}
