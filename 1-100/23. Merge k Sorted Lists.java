/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
// 1.first try - 172ms - minds concur with Divide and Conquer, but the list used to merge in this method becomes larger and larger with the times of running increases.)
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length == 0) return null;
        for(int i=0;i<lists.length-1;i++){
            lists[i+1] = mergeTwoLists(lists[i], lists[i+1]);
        }
        return lists[lists.length-1];
    }
    
    private ListNode mergeTwoLists(ListNode l1, ListNode l2){
        ListNode dummy1 = new ListNode(Integer.MIN_VALUE);
        ListNode first = dummy1;
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
        return dummy1.next;
    }
}

// 2.second try - Divide And Conquer（5ms) - best solution - just optimize for loop compared with the first try.
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length == 0) return null;
        for(int k=lists.length,c=1;k>1;k=(k+1)/2,c*=2){
            for(int i=0;i+c<lists.length;i+=2*c){
                lists[i] = mergeTwoLists(lists[i], lists[i+c]);
            }
        }
        return lists[0];
    }
    
    private ListNode mergeTwoLists(ListNode l1, ListNode l2){
        ListNode dummy1 = new ListNode(Integer.MIN_VALUE);
        ListNode first = dummy1;
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
        return dummy1.next;
    }
}