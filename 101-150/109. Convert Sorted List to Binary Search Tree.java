/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
// 1.the same as lc108, just use extra space to avoid iterating Linked list each time.
// a classical time-space trade-off - Time: O(N) Space: O(N)
class Solution {
    public TreeNode sortedListToBST(ListNode head) {
        if(head == null) return null;
        List<Integer> ll = new ArrayList<>();
        while(head != null){
            ll.add(head.val);
            head = head.next;
        }
        return recursive(ll, 0, ll.size()-1);
    }
    
    private TreeNode recursive(List<Integer> ll, int start, int end){
        int rootindex = start + (end - start) / 2;
        TreeNode root = new TreeNode(ll.get(rootindex));
        int leftsize = rootindex - start;
        int rightsize = end - rootindex;
        if(leftsize > 0) root.left = recursive(ll, start, rootindex-1);
        if(rightsize > 0) root.right = recursive(ll, rootindex+1, end);
        return root;
    }
}



// 2.Inorder Simulation - best solution - difficult to understand...
// Time: O(N) Space: O(logN)
// Elements processed in the inorder fashion on a binary search tree turn out to be sorted in ascending order.
class Solution {
    private ListNode node;
    public TreeNode sortedListToBST(ListNode head) {
        if(head == null) return null;
        int size = 0;
        ListNode runner = head;
        node = head;
        while(runner != null){
            runner = runner.next;
            size ++;
        }
        return inorderHelper(0, size - 1);
    }

    public TreeNode inorderHelper(int start, int end){
        if(start > end) return null;
        int mid = start + (end - start) / 2;
        TreeNode left = inorderHelper(start, mid - 1);
        
        TreeNode treenode = new TreeNode(node.val);
        treenode.left = left;
        node = node.next;

        TreeNode right = inorderHelper(mid + 1, end);
        treenode.right = right;
        return treenode;
    }
}

// 3.Two pointers to find middle ele each time 
// https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree/solution/  -  Solution 1
// Time: O(NLogN) Space: O(LogN)

// Note: Just a caveat for anyone else reading, a recursive solution can still be O(1) if it is eligible for tail call optimization. 
// These can be compiled as iterative solutions by the compiler itself.