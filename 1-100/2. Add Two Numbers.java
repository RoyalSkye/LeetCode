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