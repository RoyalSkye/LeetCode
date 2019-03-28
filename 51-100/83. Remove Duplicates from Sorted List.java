/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode cur = new ListNode(Integer.MIN_VALUE), ite = head;
        cur.next = head;
        while(ite!=null){
            if(ite.val > cur.val){
                cur = cur.next;
                cur.val = ite.val;
            }
            ite = ite.next;
        }
        cur.next = null;
        return head;
    }
}

//or do not modify val, manipulate list directly, remove duplicate listnodes from the list
//vote for the second

class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode cur = head;
        while(cur!=null && cur.next!=null){
            if(cur.next.val == cur.val) cur.next = cur.next.next;
            else cur = cur.next;
        }
        return head;
    }