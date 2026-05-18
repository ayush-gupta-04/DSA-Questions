// --------------- Iterative ---------------------
// time : N
// Space : 1
class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode curr = head;
        ListNode prev = null;
        while(curr != null){
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }
}


// -------------- Recursive ---------------------
// time : 2*N
// space : N (stack of recursion)
class Solution {
    public ListNode reverseList(ListNode head) {
        if(head==null || head.next == null){
            // i have reached the last element.
            return head;
        }
        ListNode revHead = reverseList(head.next);
        ListNode front = head.next;
        front.next = head;
        head.next = null;
        return revHead;
    }
}
