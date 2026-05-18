// time : N
// space : 1

public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode A = headA;
        ListNode B = headB;
        int chance = 2;
        while(A != null && B != null){
            if(A == B){
                return A;
            }
            A = A.next;
            B = B.next;
            if(A == null && chance > 0){
                A = headB;
                chance--;
            }
            if(B == null && chance > 0){
                B = headA;
                chance--;
            }
        }
        return null;
    }
    
}
