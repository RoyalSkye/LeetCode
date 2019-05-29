/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

// 1.bottom-up merge sorting - Space:O(1)
class Solution {

    public ListNode sortList(ListNode head) {
        int len = 0;
        ListNode first = head;
        while(first!=null){
            len++;
            first = first.next;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        for(int sz=1;sz<len;sz=sz*2){
            ListNode pre = dummy;
            ListNode cur = dummy.next;
            while(cur!=null){
                ListNode left = cur;
                ListNode right = split(left, sz);
                cur = split(right, sz);
                pre = merge(left, right, pre);
            }
        }
        return dummy.next;
    }
    
    //return the second list's head
    private ListNode split(ListNode node, int sz){ 
        if(node == null) return null;
        for(int i=1;i<sz && node.next!=null;i++){
            node = node.next;
        }
        ListNode right = node.next;
        node.next = null;
        return right;
    }
    
    //return the end of the merge list
    private ListNode merge(ListNode left, ListNode right, ListNode pre){  
        while(left!=null && right!=null){
            if(left.val<=right.val){
                pre.next = left;
                left = left.next;
            }else{
                pre.next = right;
                right = right.next;
            }
            pre = pre.next;
        }
        if(left!=null) pre.next = left;
        else if(right!=null) pre.next = right;
        while(pre.next!=null) pre = pre.next;
        return pre;
    }
}

// 2.merge sorting - Recursive - Space:O(logN)
class Solution {
    public ListNode sortList(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode slow = head, fast = head;
        while(fast.next!=null && fast.next.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode right = slow.next;
        slow.next = null;
        return merge(sortList(head), sortList(right));
    }
    
    private ListNode merge(ListNode left, ListNode right){
        ListNode dummy = new ListNode(0);
        ListNode pre = dummy;
        while(left!=null && right!=null){
            if(left.val <= right.val){
                pre.next = left;
                left = left.next;
            }else{
                pre.next = right;
                right = right.next;
            }
            pre = pre.next;
        }
        if(left!=null) pre.next = left;
        else if(right!=null) pre.next = right;
        return dummy.next;
    }
}