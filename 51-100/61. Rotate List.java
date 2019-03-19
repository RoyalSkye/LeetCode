/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        int len = 0;
        ListNode dummy = new ListNode(0);
        ListNode first = head;
        ListNode second = head;
        dummy.next = head;
        while(first!=null){
            first = first.next;
            len++;
        }
        if(len <= 1 || k%len == 0) return head;
        if(k >= len) k = k % len;
        first = head;
        int count = 0;
        while(first.next!=null){
            if(count == k){
                first = first.next;
                second = second.next;
            }else{
                first = first.next;
                count++;
            }
        }
        dummy.next = second.next;
        first.next = head;
        second.next = null;
        return dummy.next;
    }
}