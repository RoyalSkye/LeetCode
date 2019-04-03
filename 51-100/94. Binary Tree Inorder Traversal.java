/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
// Preorder Traversal(DLR)
// Postorder Traversal(LRD)
// Inorder Traversal(LDR)

// 1.Recursive
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) { 
        List<Integer> ans = new LinkedList<>();
        inorder(ans, root);
        return ans;
    }
    
    private void inorder(List<Integer> ans, TreeNode root){
        if(root == null) return;
        // if(root.left == null && root.right == null){
        //     ans.add(root.val);
        //     return;
        // }
        inorder(ans, root.left);
        ans.add(root.val);
        inorder(ans, root.right);
    }
}

// 2.Iterative - Stack
class Solution {
    public List < Integer > inorderTraversal(TreeNode root) {
        List < Integer > res = new ArrayList < > ();
        Stack < TreeNode > stack = new Stack < > ();
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            res.add(curr.val);
            curr = curr.right;
        }
        return res;
    }
}

// Morris Traversal - Threaded Binary Tree - non-trivial - no recursion no stack with Space O(1)
// https://stackoverflow.com/questions/5502916/explain-morris-inorder-tree-traversal-without-using-stacks-or-recursion

// Step 1: Initialize current as root
// Step 2: While current is not NULL,
// If current does not have left child
//     a. Add current’s value
//     b. Go to the right, i.e., current = current.right
// Else
//     a. In current's left subtree, make current the right child of the rightmost node
//     b. Go to this left child, i.e., current = current.left

class Solution {
    public List<Integer> inorderTraversal(TreeNode root) { 
        List<Integer> ans = new LinkedList<>();
        TreeNode cur = root;
        while(cur!=null){
            if(cur.left == null){
                ans.add(cur.val);
                cur = cur.right;
            }else{
                TreeNode rightmost = cur.left;
                while(rightmost.right!=null){
                    rightmost = rightmost.right;
                }
                rightmost.right = cur;
                TreeNode tmp = cur;
                cur = cur.left;
                tmp.left = null;
            }
        }
        return ans;
    }
}
