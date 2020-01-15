/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
// int: -2147483648~2147483647(-2^31~2^31-1)
//class Solution {
//	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
//		int v1 = 0, v2 = 0, sum = 0;
//		int weight = 1;
//		for(int i=0; l1!=null; i++){
//			v1 += l1.val*weight;
//			weight *= 10;
//			l1 = l1.next;
//		}
//		weight = 1;
//		for(int i=0; l2!=null; i++){
//			v2 += l2.val*weight;
//			weight *= 10;
//			l2 = l2.next;
//		}
//		sum = v1+v2;
//		//record the first node
//		ListNode ln = new ListNode(sum%10);
//		ListNode result = new ListNode(0);
//		result = ln;
//		sum /= 10;
//		//insert
//		while(sum != 0){
//			ListNode newnode = new ListNode(sum%10);
//			sum /= 10;
//			ln.next = newnode;
//			ln = ln.next;
//		}
//		return result;
//	}
//

//O(max(m,n))
class Solution {
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode ln = new ListNode(0);
		ListNode result = ln;
		ListNode p = l1, q = l2;
		int v1 = 0, v2 = 0, carry = 0;
		while(p!=null || q!=null){
			int sum = 0;
			ListNode newNode = new ListNode(0);
			v1 = (p == null) ? 0 : p.val;
			v2 = (q == null) ? 0 : q.val;
			sum = v1 + v2 + carry;
			carry = sum>9 ? 1 : 0;
			newNode.val = sum>9 ? sum-10 : sum;
			ln.next = newNode;
			ln = ln.next;
			if(p!=null) p = p.next;
			if(q!=null) q = q.next;
		}
		if(carry == 1){
			ln.next = new ListNode(1);
		}
		return result.next;
	}
}

// 01/15/2020
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode first1 = l1;
        ListNode first2 = l2;
        ListNode dummy = new ListNode(0);
        ListNode first = dummy;
        int remain = 0;
        while(first1 != null || first2 != null) {
            int sum = remain;
            if(first1 != null) {
                sum += first1.val;
                first1 = first1.next;
            }
            if (first2 != null) {
                sum += first2.val;
                first2 = first2.next;
            }
            remain = sum / 10;
            first.next = new ListNode(sum % 10);
            first = first.next;
        }
        if(remain != 0) {
            first.next = new ListNode(remain);
        }
        return dummy.next;
    }
}

//follow up:
1. lc445: What if the the digits in the linked list are stored in non-reversed order? For example:
(3→4→2)+(4→6→5)=8→0→7
2. lc445: What if you cannot modify the input lists? In other words, reversing the lists is not allowed.