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
    // find the first ele greater than or equal to x
    public ListNode partition(ListNode head, int x) {
        if(head == null || head.next == null) return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode first = dummy;
        ListNode tmp = dummy;
        boolean isfind = false;
        while(first.next!=null){
            if(!isfind && first.next.val < x){
                first = first.next;
                continue;
            }else if(!isfind && first.next.val >= x){
                tmp = first;
                first = first.next;
                isfind = true;
                continue;
            }
            if(first.next.val < x){  //move
                if(first.next.next == null){
                    first.next.next = tmp.next;
                    tmp.next = first.next;
                    first.next = null;
                    break;
                }else{
                    ListNode l1 = first.next;
                    first.next = l1.next;
                    l1.next = tmp.next;
                    tmp.next = l1;
                    tmp = tmp.next;
                    continue;
                }
            }
            first = first.next;
        }
        return dummy.next;
    }
}

// elegant!
class Solution {
    // find the first ele greater than or equal to x
    public ListNode partition(ListNode head, int x) {
        if(head == null || head.next == null) return head;
        ListNode beforedummy = new ListNode(0);
        ListNode afterdummy = new ListNode(0);
        ListNode before = beforedummy;
        ListNode after = afterdummy;
        while(head!=null){
            if(head.val < x){
                before.next = head;
                before = before.next;
            }else{
                after.next = head;
                after = after.next;
            }
            head = head.next;
        }
        after.next = null;  //memory limit exceeded otherwise
        before.next = afterdummy.next;
        return beforedummy.next;
    }
}