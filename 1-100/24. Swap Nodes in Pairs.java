/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
// 1.my solution - Iterative
class Solution {
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode current = dummy;
        while(current.next!=null && current.next.next!=null){
            ListNode first = current.next;
            ListNode second = current.next.next;
            first.next = second.next;
            second.next = first;
            current.next = second;
            current = current.next.next;
        }
        return dummy.next;
    }
}

// 2.Recursive
class Solution {
    public ListNode swapPairs(ListNode head) {
        if(head==null || head.next==null) return head;
        ListNode newnode = head.next;
        head.next = swapPairs(head.next.next);
        newnode.next = head;
        return newnode;
    }
}