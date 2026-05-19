// ------------------------ Brute-Force --------------------
// add all vals to nums[]
// sort nums[]
// assign vals of nums to the LL

// time : N + N*logN + N
// space : N

class Solution {
    public ListNode sortList(ListNode head) {
        if(head == null || head.next == null) return head;

        List<Integer> nums = new ArrayList<>();
        
        ListNode temp = head;
        while(temp != null){
            nums.add(temp.val);
            temp = temp.next;
        }

        Collections.sort(nums);
        temp = head;
        for(int a : nums){
            temp.val = a;
            temp = temp.next;
        }

        return head;
    }
}




// ----------------------- Optimal Solution ---------------------------
// time : NlogN
// space : 1
class Solution {

    public ListNode merge(ListNode list1, ListNode list2) {
        ListNode dummyNode = new ListNode(-1, null);
        ListNode temp = dummyNode;

        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                temp.next = list1;
                list1 = list1.next;
            } else {
                temp.next = list2;
                list2 = list2.next;
            }
            temp = temp.next;
        }

        if (list1 != null) {
            temp.next = list1;
        } else {
            temp.next = list2;
        }
        return dummyNode.next;
    }

    public ListNode middle(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode slow = head;
        ListNode fast = head.next;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }


    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode middle = middle(head);

        // Split into two halves
        ListNode right = middle.next;
        middle.next = null;
        ListNode left = head;

        left = sortList(left);
        right = sortList(right);

        return merge(left, right);
    }
}
