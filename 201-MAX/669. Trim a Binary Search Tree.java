// 1.Recursive - my code
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode trimBST(TreeNode root, int L, int R) {
        if(root == null) return null;
        TreeNode node = root;
        while(node != null && (node.val < L || node.val > R)){
            if(node.val < L) node = node.right; 
            else if(node.val > R) node = node.left; 
        }
        if(node != null){
            node.left = trimBST(node.left, L, R);
            node.right = trimBST(node.right, L, R);
        }
        return node;
    }
}

// or optimize code
class Solution {
    public TreeNode trimBST(TreeNode root, int L, int R) {
        if(root == null) return null;
        if(root.val < L) return trimBST(root.right, L, R);
        if(root.val > R) return trimBST(root.left, L, R);
        root.right = trimBST(root.right, L, R);
        root.left = trimBST(root.left, L, R);
        return root;
    }
}