/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
// 1.Two pass algorithm
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode first = head;
        int size = 0;
        while(first!=null){
            first = first.next;
            size++;
        }
        first = dummy;
        for(int i=size-n;i>0;i--){
            first = first.next;
        }
        first.next = first.next.next;
        return dummy.next;
    }
}

// 2.One pass algorithm
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode first = dummy;
        ListNode second = dummy;
        for(int i=0;i<n;i++){  //gap n between first and second
            first = first.next;
        }
        while(first.next!=null){
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        return dummy.next;
    }
}