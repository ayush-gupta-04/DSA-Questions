// time : N
// space : N

class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode();
        ListNode temp = dummyHead;
        int carry = 0;

        while(l1 != null && l2 != null){
            int num = l1.val + l2.val + carry;
            int dig = num%10;

            ListNode newNode = new ListNode(dig);
            temp.next = newNode;
            temp = temp.next;
            carry = num/10;
            l1 = l1.next;
            l2 = l2.next;
        }

        while(l1 != null){
            int num = l1.val + carry;
            int dig = num%10;

            ListNode newNode = new ListNode(dig);
            temp.next = newNode;
            temp = temp.next;
            carry = num/10;
            l1 = l1.next;
        }

        while(l2 != null){
            int num = l2.val + carry;
            int dig = num%10;

            ListNode newNode = new ListNode(dig);
            temp.next = newNode;
            temp = temp.next;
            carry = num/10;
            l2 = l2.next;
        }

        if(carry != 0){
            ListNode newNode = new ListNode(carry);
            temp.next = newNode;
            temp = temp.next;
        }


        return dummyHead.next;
    }
}
