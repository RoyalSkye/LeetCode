/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
// 1.my solution - Inorder Traversal
class Solution {
    public void recoverTree(TreeNode root) {
        if(root == null) return;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode tmp = root;
        TreeNode swap1 = null;
        TreeNode preswap1 = null;
        TreeNode swap2 = null;
        Integer pre = null;
        while(!stack.isEmpty() || tmp!=null){
            while(tmp!=null){
                stack.push(tmp);
                tmp = tmp.left;
            }
            tmp = stack.pop();
            if(pre == null) pre = tmp.val;
            else{
                if(tmp.val <= pre){
                    if(swap1==null){
                        swap1 = preswap1;
                        swap2 = tmp;
                    } 
                    else{
                        swap2 = tmp;
                        break;
                    } 
                }  
            } 
            pre = tmp.val;
            preswap1 = tmp;
            tmp = tmp.right;
        }
        int res = swap1.val;
        swap1.val = swap2.val;
        swap2.val = res;
    }
}

// Recursive
class Solution {
    TreeNode first = null;
    TreeNode second = null;
    TreeNode prev = new TreeNode(Integer.MIN_VALUE);
    
    public void recoverTree(TreeNode root) {
        traverse(root);
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }
    
    private void traverse(TreeNode root) {
        if (root == null) return;
        // System.out.println(root.val);
        traverse(root.left);
        if (first == null && prev.val >= root.val) {
            first = prev;
        }
        if (first != null && prev.val >= root.val) {
            second = root;
        }        
        prev = root;
        traverse(root.right);
    }
}