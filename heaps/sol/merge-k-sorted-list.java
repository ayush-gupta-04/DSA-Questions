// Push all head to the heap.
// pop one , connect it , add it's child to the heap.



/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length == 0) return null;
        PriorityQueue<ListNode> pq = new PriorityQueue<ListNode>((x,y) -> Integer.compare(x.val,y.val));
        for(ListNode node : lists){
            if(node == null) continue;
            pq.offer(node);
        }
        ListNode head = new ListNode(-1);
        ListNode temp = head;
        while(!pq.isEmpty()){
            ListNode node = pq.poll();
            temp.next = node;
            temp = node;
            if(node.next != null) pq.offer(node.next);
        }
        return head.next;
    }
}
