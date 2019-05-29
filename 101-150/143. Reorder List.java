/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

// 1.HashMap
class Solution {
    public void reorderList(ListNode head) {
        Map<Integer, ListNode> map = new HashMap<>();
        ListNode first = head;
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        for(int i=1;first!=null;first=first.next,i++){
            map.put(i, first);
        }
        for(int i=1,j=map.size();i<=j;i++,j--){
            ListNode node1 = map.get(i);
            ListNode node2 = map.get(j);
            cur.next = node1;
            if(i!=j) node1.next = node2;
            node2.next = null;
            cur = node2;
        }
    }
}

// 2.reverse a linked list
class Solution {
    public void reorderList(ListNode head) {
        if(head == null || head.next == null || head.next.next == null) return;
        
        //1.find the middle node - p1
        ListNode p1 = head, p2 = head;
        while(p2.next!=null && p2.next.next!=null){
            p1 = p1.next;
            p2 = p2.next.next;
        }
        //2.reverse all nodes after the middle 123456->123654
        ListNode middle = p1;
        ListNode first= p1.next;
        ListNode second = p1.next.next;
        while(second!=null){
            first.next = second.next;
            second.next = middle.next;
            middle.next = second;
            second = first.next;
        }
        //3.reorder 123654->162534
        p1 = head;
        p2 = middle.next;
        while(p1!=middle){
            ListNode tmp1 = p1.next;
            ListNode tmp2 = p2.next;
            middle.next = tmp2;
            p1.next = p2;
            p2.next = tmp1;
            p1 = tmp1;
            p2 = tmp2;
        }
    }
}