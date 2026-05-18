// time : N
// space : 1
class Solution {
    public Node segregate(Node head) {
        if(head == null || head.next == null) return head;
        
        Node zeroHead = new Node(-1);
        Node oneHead = new Node(-1);
        Node twoHead = new Node(-1);
        
        Node mvZero = zeroHead;
        Node mvOne = oneHead;
        Node mvTwo = twoHead;
        
        Node temp = head;
        while(temp != null){
            if(temp.data == 0){
                mvZero.next = temp;
                mvZero = mvZero.next;
            }else if(temp.data == 1){
                mvOne.next = temp;
                mvOne = mvOne.next;
            }else{
                mvTwo.next = temp;
                mvTwo = mvTwo.next;
            }
            temp = temp.next;
        }
        
        mvZero.next = (oneHead.next != null) ? oneHead.next : twoHead.next;
        mvOne.next = twoHead.next;
        mvTwo.next = null;
        
        return zeroHead.next;
    }
}
