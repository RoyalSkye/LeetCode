/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
// 1.Recursive
class Solution {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode p = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return p;
    }
}

// or (mess)
class Solution {
    private ListNode left;
    private boolean stop = false;
    public ListNode reverseList(ListNode head) {
        if(head == null || head.next == null) return head;
        left = head;
        ListNode right = head;
        recurse(right);
        return head;
    }
    
    private void recurse(ListNode right){
        if(right == null) return;
        recurse(right.next);
        if(right == left || right.next == left) stop = true;
        if(!stop){
            int tmp = left.val;
            left.val = right.val;
            right.val = tmp;
            left = left.next;
        }
    }
}

// 2.Iterative 
class Solution {
    public ListNode reverseList(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode first = head, second = head.next;
        while(second!=null){
            first.next = second.next;
            second.next = dummy.next;
            dummy.next = second;
            second = first.next;
        }
        return dummy.next;
    }
}

// or - upvote
class Solution {
    public ListNode reverseList(ListNode head) {
        if(head==null || head.next==null) return head;
        ListNode cur = head;
        ListNode pre = null;
        ListNode next = null;
        while(cur!=null){
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
}