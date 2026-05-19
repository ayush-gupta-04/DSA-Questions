// time : N
// space : 1
class Solution {
    public ListNode oddEvenList(ListNode head) {
        if(head == null || head.next == null) return head;

        ListNode oddStart = new ListNode();
        ListNode oddEnd = oddStart;
        ListNode evenStart = new ListNode();
        ListNode evenEnd = evenStart;

        ListNode mv = head;
        int t = 1;
        while(mv != null){
            if((t&1) == 1){
                //odd
                oddEnd.next = mv;
                oddEnd = oddEnd.next;
            }else{
                //even
                evenEnd.next = mv;
                evenEnd = evenEnd.next;
            }
            t++;
            mv = mv.next;
        }
        // for safe side.
        oddEnd.next = null;
        evenEnd.next = null;

        oddEnd.next = evenStart.next;
        return oddStart.next;
    }
}
