/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
// 1.Iterative - BFS with queue / DFS with stack
class Solution {
    public int maxDepth(TreeNode root) {
        int depth = 0;
        if(root == null) return depth;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            int size = q.size();
            for(int i=0;i<size;i++){
                TreeNode tmp = q.poll();
                if(tmp.left!=null) q.add(tmp.left);
                if(tmp.right!=null) q.add(tmp.right);
            }
            depth++;
        }
        return depth;
    }
}

// 2.Recursive
class Solution {
    public int maxDepth(TreeNode root) {
        if(root == null) return 0;
        return recursive(root, 1);
    }
    
    private int recursive(TreeNode root, int level){
        int left = 0, right = 0;
        if(root.left!=null) left = recursive(root.left, level+1);
        if(root.right!=null) right = recursive(root.right, level+1);
        return Math.max(level, Math.max(left, right));
    }
}

// or
class Solution {
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return 1+Math.max(maxDepth(root.left),maxDepth(root.right));
    }
}