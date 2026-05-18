// time : N
// space : 1


class Solution {
    ListNode reverse(ListNode head){
        if(head == null || head.next == null){
            return head;
        }
        ListNode prev = null;
        ListNode curr = head;
        while(curr != null){
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    ListNode middle(ListNode head){
        ListNode slow = head;
        ListNode fast = head;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
    public boolean isPalindrome(ListNode head) {
        ListNode mid = middle(head);
        ListNode revHead = reverse(mid);
        ListNode s = head;
        ListNode e = revHead;
        while(s != null && e != null){
            if(s.val != e.val) return false;
            s = s.next;
            e = e.next;
        }
        // reverse the back to the original state.
        reverse(revHead);
        return true;
    }
}
