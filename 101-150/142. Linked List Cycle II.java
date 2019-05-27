/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */

// 1.Time:O(N) Space:O(N)
public class Solution {
    public ListNode detectCycle(ListNode head) {
        Set<ListNode> visited = new HashSet<>();
        ListNode first = head;
        while(first!=null){
            if(visited.contains(first)) return first;
            visited.add(first);
            first = first.next;
        }
        return null;
    }
}

// 2.Floyd's Tortoise and Hare
public class Solution {
    public ListNode detectCycle(ListNode head) {
        if(head == null) return null;
        ListNode slow = head, fast = head;
        do{
            if(fast == null || fast.next == null) return null;
            slow = slow.next;
            fast = fast.next.next;
        }while(slow != fast);
        slow = head;
        while(slow != fast){
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }
}