/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
// 1.Iterative
class Solution {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        int count = 1;
        while(count!= m){
            pre = pre.next;
            count++;
        }
        ListNode first = pre.next, second = first.next, tmp = first;
        while(count!=n){
            // System.out.println(count);
            first.next = second.next;
            second.next = tmp;
            tmp = second;
            second = first.next;
            count++;
        }
        pre.next = tmp;
        return dummy.next;
    }
}

// 2.Recursive - do not really change the structure of lists, just change value
class Solution {
    private boolean stop;
    private ListNode left;
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if(head == null || head.next == null) return head;
        left = head; 
        ListNode right = head;
        recurse(right, m, n);
        return head;
    }
    
    private void recurse(ListNode right, int m, int n){
        if(n==1) return;
        right = right.next;
        if(m>1) left = left.next;
        recurse(right, m-1, n-1); 
        if(left == right || right.next == left) stop = true;
        if(!stop){
            int tmp = left.val;
            left.val = right.val;
            right.val = tmp;
            left = left.next;
        }
    }
}

// or let each node between left and right points to the previous node
class Solution {
    private ListNode tmp;
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if(head == null || head.next == null) return head;
        ListNode left = head, right = head, dummy = new ListNode(0), pre = dummy;
        dummy.next = head;
        while(m!=1){
            left = left.next;
            right = right.next;
            pre = pre.next;
            m--;
            n--;
        }
        ListNode ll = recurse(left, right, n);
        pre.next = ll;
        left.next = tmp;
        return dummy.next;
    }
    
    private ListNode recurse(ListNode left, ListNode right, int n){
        if(n==1){
            tmp = right.next;
            return right;
        }
        ListNode p = recurse(left, right.next, n-1); 
        right.next.next = right;
        right.next = null;
        return p; 
    }
}