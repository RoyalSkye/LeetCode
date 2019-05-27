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
    public boolean hasCycle(ListNode head) {
        Set<ListNode> visited = new HashSet<>();
        ListNode first = head;
        while(first!=null){
            if(visited.contains(first)) return true;
            visited.add(first);
            first = first.next;
        }
        return false;
    }
}

// 2.Two pointers - Space:O(1)
public class Solution {
    public boolean hasCycle(ListNode head) {
        if(head == null || head.next == null) return false;
        ListNode fast = head.next, slow = head;
        while(slow != fast){
            if(fast == null || fast.next == null) return false;
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }
}