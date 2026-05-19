// we will have a dummy list.
// we will remove the node from the original list and add it to it's correct position in dummy list.

// time : N*N
// space : 1
class Solution {
    public ListNode insertionSortList(ListNode head) {
        ListNode dummy = new ListNode();
        ListNode curr = head;

        while (curr != null) {
            ListNode prev = dummy;

            // we will insert a new node like this ... prev -> currNode -> temp 
            ListNode temp = prev.next;
            while (temp != null && temp.val <= curr.val) {
                prev = temp;
                temp = temp.next;
            }

            ListNode next = curr.next;

            // remove curr from original list and add to the result list.
            curr.next = prev.next;
            prev.next = curr;

            curr = next;
        }

        return dummy.next;
    }
}
