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
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> preorder = new LinkedList<>();
        if(root == null) return preorder;
        recursive(root, preorder);
        return preorder;
    }
    
    private void recursive(TreeNode root, List<Integer> preorder){
        preorder.add(root.val);
        if(root.left != null) recursive(root.left, preorder);
        if(root.right != null) recursive(root.right, preorder);
    }
}

// 2.Iterative
class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> preorder = new LinkedList<>();
        if(root == null) return preorder;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode n = stack.pop();
            preorder.add(n.val);
            if(n.right!=null) stack.push(n.right);
            if(n.left!=null) stack.push(n.left);
        }
        return preorder;
    }
}

// 3.Morris traversal - difficult to understand
// - without additional space except the output List
// See lc94 
class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> preorder = new LinkedList<>();
        TreeNode cur = root;
        while(cur != null){
            if(cur.left == null){
                preorder.add(cur.val);
                cur = cur.right;
            }else{
                TreeNode node = cur.left;
                while(node.right != null && node.right!=cur){
                    node = node.right;
                }
                if(node.right == null){
                    preorder.add(cur.val);
                    node.right = cur;
                    cur = cur.left;
                }else{
                    node.right = null;
                    cur = cur.right;
                }
            }
        }
        return preorder;
    }
}