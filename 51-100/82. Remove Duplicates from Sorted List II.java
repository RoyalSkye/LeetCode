/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

// mess
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(Integer.MIN_VALUE);
        dummy.next = head;
        ListNode cur = head, first = dummy;
        boolean flag = true;
        while(cur!=null && cur.next!=null){
            if(cur.next.val == cur.val){
                cur = cur.next;
                flag = false;
                continue;
            }
            if(!flag){
                cur = cur.next;
                flag = true;
            }else{
                first.next = cur;
                first = first.next;
                cur = cur.next;
            }
        }
        if(!flag) first.next = cur.next;
        else first.next = cur;
        return dummy.next;
    }
}

//simplify

class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy=new ListNode(0);
        dummy.next=head;
        ListNode pre=dummy;
        ListNode cur=head;
        while(cur!=null){
            while(cur.next!=null&&cur.val==cur.next.val){
                cur=cur.next;
            }
            if(pre.next==cur){  //non-duplicate
                pre=pre.next;
            }else{
                pre.next=cur.next;  //note: do not move pre to pre.next
            }
            cur=cur.next;
        }
        return dummy.next;
    }
}