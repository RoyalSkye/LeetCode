/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

// 1.Recursive
class Solution {
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        if(root==null || root.left==null) return root;
        TreeNode newRoot = upsideDownBinaryTree(root.left);
        root.left.left = root.right;
        root.left.right = root;
        root.right = null;
        root.left = null;
        return newRoot;
    }
}

// 2.Iterative
class Solution {
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        if(root == null || root.left==null) return root;
        TreeNode newRoot = new TreeNode(0);
        Stack<TreeNode> stack = new Stack<>();
        while(root.left!=null){
            stack.push(root);
            root = root.left;
        }
        newRoot = root;
        TreeNode tmp = new TreeNode(0);
        while(!stack.isEmpty()){
            tmp = stack.pop();
            newRoot.right = tmp;
            newRoot.left = tmp.right;
            tmp.left = null;
            tmp.right = null;
            newRoot = tmp;
        }
        return root;
    }
}

// or without stack
class Solution {
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        if(root == null || root.left==null) return root;
        TreeNode cur = root;
        TreeNode prev = null;
        TreeNode tmp = null;   
        TreeNode next = null;
        while(cur!=null){
            next = cur.left;
            cur.left = tmp;
            tmp = cur.right;
            cur.right = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }
}