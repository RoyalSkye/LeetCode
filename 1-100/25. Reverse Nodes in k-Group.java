/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
// 1.my solution - O(N)
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) { 
        if(head==null || head.next==null || k==1) return head;
        int count = 0;
        ListNode dummy = new ListNode(0);  //for return 
        dummy.next = head;
        ListNode l1 = dummy, l2 = head, pre = dummy;
        while(l1!=null && l2!=null){
            if(count==0){  //check whether the rest of list is long enough or not
                if(IsLong(pre,k)){
                    l1 = l1.next;
                    l2 = l2.next;
                }else break;
            }
            l1.next = l2.next;
            l2.next = pre.next;
            pre.next = l2;
            l2 = l1.next;
            count++;
            if(count == k-1){
                pre = l1;
                count = 0;
            }
        }
        return dummy.next;
    }
    private boolean IsLong(ListNode head, int k){
        while(head!=null && k>0){
            head = head.next;
            k--;
        }
        if(head == null || k>0) return false;
        else return true;  //k==0 || head.next!=null
    }
}

// 2.concise recursive solution
public ListNode reverseKGroup(ListNode head, int k) {
    ListNode curr = head;
    int count = 0;
    while (curr != null && count != k) { // find the k+1 node
        curr = curr.next;
        count++;
    }
    if (count == k) { // if k+1 node is found
        curr = reverseKGroup(curr, k); // reverse list with k+1 node as head
        // head - head-pointer to direct part, 
        // curr - head-pointer to reversed part;
        while (count-- > 0) { // reverse current k-group: 
            ListNode tmp = head.next; // tmp - next head in direct part
            head.next = curr; // preappending "direct" head to the reversed list 
            curr = head; // move head of reversed part to a new node
            head = tmp; // move "direct" head to the next node in direct part
        }
        head = curr;
    }
    return head;
}