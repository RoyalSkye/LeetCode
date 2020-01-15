/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
// 1. reverse list
// 2. list -> add num directly -> list bug: overflow
// 3. use Stack

// 1.Stack - my solution
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();
        Stack<Integer> ans = new Stack<>();
        ListNode n1 = l1, n2 = l2;
        ListNode dummy = new ListNode(0);
        ListNode first = dummy;
        while(n1 != null || n2 != null) {
            if(n1 != null) {
                s1.push(n1.val);
                n1 = n1.next;
            }
            if(n2 != null) {
                s2.push(n2.val);
                n2 = n2.next;
            }
        }
        int remain = 0;
        while(!s1.isEmpty() || !s2.isEmpty()) {
            int sum = remain;
            if(!s1.isEmpty()) 
                sum += s1.pop();
            if(!s2.isEmpty()) 
                sum += s2.pop();
            remain = sum / 10;
            ans.push(sum % 10);
        }
        if(remain != 0) 
            ans.push(remain);
        while(!ans.isEmpty()) {
            first.next = new ListNode(ans.pop());
            first = first.next;
        }
        return dummy.next;
    }
}

// after refining
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();
        ListNode n1 = l1, n2 = l2;
        ListNode first = new ListNode(0);
        while(n1 != null) {
            s1.push(n1.val);
            n1 = n1.next;
        }
        while(n2 != null) {
            s2.push(n2.val);
            n2 = n2.next;
        }
        int sum = 0;
        while(!s1.isEmpty() || !s2.isEmpty()) {
            if(!s1.isEmpty()) sum += s1.pop();
            if(!s2.isEmpty()) sum += s2.pop();
            first.val = sum % 10;
            ListNode pre = new ListNode(sum / 10);
            pre.next = first;
            first = pre;
            sum /= 10;
        }
        return first.val == 0 ? first.next : first;
    }
}

// 2.Recursive - less readable
https://leetcode.com/problems/add-two-numbers-ii/discuss/92643/Java-O(n)-recursive-solution-by-counting-the-difference-of-length
