/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
// 1.my solution  - 191 / 193 test cases passed. 
// failed test case: [1,2,3,3,null,2,null]
class Solution {
    // Inorder Traversal: symmetric around the root 3241423
    public boolean isSymmetric(TreeNode root) {
        TreeNode first = root;
        Stack<TreeNode> stack = new Stack<>();
        Stack<Integer> tmp = new Stack<>();
        boolean flag = false;
        while(!stack.isEmpty() || first!=null){
            while(first!=null){
                stack.push(first); 
                first = first.left;
            }
            first = stack.pop();
            if(flag){
                if(tmp.isEmpty()) return false;
                if(tmp.pop() != first.val) return false;
            }
            if(first == root) flag = true;
            if(!flag) tmp.push(first.val);
            first = first.right;
        }
        if(!tmp.isEmpty()) return false;
        return true;
    }
}

// 2.Recursive
class Solution {
    public boolean isSymmetric(TreeNode root) {
        if(root == null) return true;
        return recursive(root.left, root.right);
    }
    
    private boolean recursive(TreeNode left, TreeNode right){
        if(left == null && right == null) return true;
        if(left == null || right == null) return false;
        if(left.val != right.val) return false;
        return recursive(left.left, right.right) && recursive(left.right, right.left);
    }
}

// 3.Iterative - BFS with Queue
class Solution {
    public boolean isSymmetric(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        q.add(root);
        while(!q.isEmpty()){
            TreeNode t1 = q.poll();
            TreeNode t2 = q.poll();
            if(t1==null && t2==null) continue;
            if(t1==null || t2==null) return false;
            if(t1.val != t2.val) return false;
            q.add(t1.left);
            q.add(t2.right);
            q.add(t1.right);
            q.add(t2.left);
        }
        return true;
    }
}