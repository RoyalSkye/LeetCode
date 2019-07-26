// 1.Recursive
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
    public TreeNode invertTree(TreeNode root) {
        if(root == null) return null;
        TreeNode tmp = root.left;
        root.left = invertTree(root.right);
        root.right = invertTree(tmp);
        return root;
    }
}

// 2.BFS
class Solution {
    public TreeNode invertTree(TreeNode root) {
        if(root == null) return root;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            TreeNode node = queue.remove();
            TreeNode left = node.left;
            TreeNode right = node.right;
            node.left = right;
            node.right = left;
            if(left != null) queue.add(left);
            if(right != null) queue.add(right);
        }
        return root;
    }
}

