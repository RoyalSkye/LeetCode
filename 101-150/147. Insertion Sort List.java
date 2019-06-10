/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode insertionSortList(ListNode head) {
        ListNode helper = new ListNode(0);
        ListNode pre = helper;
        ListNode cur = head;
        ListNode next = null;
        while(cur!=null){
            next = cur.next;
            while(pre.next!=null && pre.next.val<cur.val){
                pre = pre.next;
            }
            cur.next = pre.next;
            pre.next = cur;
            pre = helper;
            cur = next;
        }
        return helper.next;
    }
}

// optimization
class Solution {
    public ListNode insertionSortList(ListNode head) {
        ListNode helper = new ListNode(0);
        ListNode pre = helper;
        ListNode cur = head;
        ListNode next = null;
        while(cur!=null){
            next = cur.next;
            if(pre.val > cur.val) pre = helper;
            while(pre.next!=null && pre.next.val<cur.val){
                pre = pre.next;
            }
            cur.next = pre.next;
            pre.next = cur;
            // pre = helper;
            cur = next;
        }
        return helper.next;
    }
}