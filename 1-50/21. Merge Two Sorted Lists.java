/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

// 1.my solution
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null) return l2;
        if(l2 == null) return l1;
        ListNode dummy1 = new ListNode(Integer.MIN_VALUE);
        dummy1.next = l1;
        ListNode first = dummy1;
        ListNode second = l2;
        while(second!=null){
            if(first.next == null){  // l1 is in the end
                first.next = second;
                break;
            }
            if(first.val <= second.val && second.val <= first.next.val){
                ListNode newnode = second;
                second = second.next;
                newnode.next = first.next;
                first.next = newnode;
            }else first = first.next;
        }
        return dummy1.next;
    }
}

// 2.Iteration
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(Integer.MIN_VALUE);
        ListNode first = dummy;
        while(l1!=null && l2!=null){
            if(l1.val <= l2.val){
                first.next = l1;
                l1 = l1.next;
            }else{
                first.next = l2;
                l2 = l2.next;
            }
            first = first.next;
        }
        first.next = l1 == null ? l2 : l1;
        return dummy.next; 
    }
}

// 3.Recursion
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }else if (l2 == null) {
            return l1;
        }else if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        }else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }
}