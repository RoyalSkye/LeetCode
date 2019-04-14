/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
// 1.my solution - preorder with extra space
class Solution {
    public void flatten(TreeNode root) {
        List<TreeNode> tmp = new ArrayList<>();
        preorder(root, tmp);
        for(int i=0;i<tmp.size()-1;i++){
            TreeNode node = tmp.get(i);
            node.left = null;
            node.right = tmp.get(i+1);
        }
    }
    
    private void preorder(TreeNode root, List<TreeNode> tmp){
        if(root == null) return;
        tmp.add(root);
        preorder(root.left, tmp);
        preorder(root.right, tmp);
    }
}

// 2.cooooooool!
class Solution {
    private TreeNode prev = null;
    public void flatten(TreeNode root) {
        if (root == null)
            return;
        flatten(root.right);
        flatten(root.left);
        root.right = prev;
        root.left = null;
        prev = root;
    }
}

// 3.Iterative - similar to Morris Traversal
class Solution {
    public void flatten(TreeNode root) {
        TreeNode cur = root;
        while(cur!=null){
            if(cur.left != null){
                TreeNode tmp = cur.left;
                while(tmp.right != null) tmp = tmp.right;
                tmp.right = cur.right;
                cur.right = cur.left;
                cur.left = null;
            }
            cur = cur.right;
        }
    }
}