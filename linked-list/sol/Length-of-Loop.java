// find the start of the loop.
// count number of nodes in loop

// time : N
// space : 1
class Solution {
    public int lengthOfLoop(Node head) {
        // code here
        if(head == null || head.next == null) return 0;
        Node slow = head;
        Node fast = head;
        Node start = null;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast){
                Node mv = head;
                while(mv != slow){
                    mv = mv.next;
                    slow = slow.next;
                }
                start = mv;
                break;
            }
        }
        
        if(start==null) return 0;
        
        Node temp = start.next;
        int cnt = 1;
        while(temp != start){
            temp = temp.next;
            cnt++;
        }
        return cnt;
    }
}
