/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
// 1.top down approach - O(N^2)
class Solution {
    public boolean isBalanced(TreeNode root) {
        if(root == null) return true;
        int lefttree = recursive(root.left);
        int righttree = recursive(root.right);
        return Math.abs(lefttree - righttree) <= 1 && isBalanced(root.left) && isBalanced(root.right);
    }
    
    private int recursive(TreeNode root){
        if(root == null) return 0;
        return Math.max(recursive(root.left), recursive(root.right)) + 1;
    }
}

// 2.bottom up approach - O(N)
class Solution {
    public boolean isBalanced(TreeNode root) {
        return recursive(root) != -1;
    }
    
    private int recursive(TreeNode root){
        if(root == null) return 0;
        int left = recursive(root.left);
        if(left == -1) return -1;
        int right = recursive(root.right);
        if(right == -1) return -1;
        if(Math.abs(left - right) > 1) return -1;
        return Math.max(left, right) + 1;
    }
}