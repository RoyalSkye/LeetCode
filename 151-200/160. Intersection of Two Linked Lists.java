/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */

// 1.extra space
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        Set<ListNode> set = new HashSet<>();
        ListNode first = headA, second = headB;
        while(first!=null){
            set.add(first);
            first = first.next;
        }
        while(second!=null){
            if(set.contains(second)) return second;
            second = second.next;
        }
        return null;
    }
}

// 2.without extra space - Time:O(N) Space:O(1)
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int len1 = 0, len2 = 0;
        ListNode firstA = headA, firstB = headB;
        while(firstA!=null){
            firstA = firstA.next;
            len1++;
        }
        while(firstB!=null){
            firstB = firstB.next;
            len2++;
        }
        firstA = headA; 
        firstB = headB;
        if(len1 < len2){  //retain len1 >= len2
            ListNode tmp = firstA;
            firstA = firstB;
            firstB = tmp;
            int tmp1 = len1;
            len1 = len2;
            len2 = tmp1;
        }
        int gap = len1 - len2;
        while(gap!=0){
            firstA = firstA.next;
            gap--;
        }
        while(firstA!=null && firstB!=null){
            if(firstA == firstB) return firstA;
            firstA = firstA.next;
            firstB = firstB.next;
        }
        return null;
    }
}

// 3.two pointers - cooooool!
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA == null || headB == null) return null;
        ListNode firstA = headA, firstB = headB;
        while(firstA != firstB){
            firstA = firstA == null ? headB : firstA.next;
            firstB = firstB == null ? headA : firstB.next;
        }
        return firstA;
    }
}