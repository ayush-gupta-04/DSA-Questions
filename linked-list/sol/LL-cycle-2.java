// time : N
// space : 1
public class Solution {
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast){
                ListNode mv = head;
                while(slow != mv){
                    slow = slow.next;
                    mv = mv.next;
                }
                return slow;
            }
        }

        return null;
    }
}
