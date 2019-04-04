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
// Time: O(N) Space: O(logn) - best case, O(N) - worst case
class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p == null || q == null){
            if(p == null && q == null) return true;
            else return false;
        }
        if(p.val != q.val) return false;
        if(isSameTree(p.left, q.left)) return isSameTree(p.right, q.right);
        return false;
    }
}

// elegant code
class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {   
        if (p == null && q == null) return true;
        if (q == null || p == null) return false;
        if (p.val != q.val) return false;
        return isSameTree(p.right, q.right) && isSameTree(p.left, q.left);
    }
}

// 2.Iterative - Queue/Stack - BFS/DFS
class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {   
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(p);
        queue.add(q);
        while(!queue.isEmpty()){
            TreeNode t1 = queue.poll();
            TreeNode t2 = queue.poll();
            if(t1 == null && t2 == null){
                continue;
            }else if(t1 == null || t2 == null || t1.val != t2.val){
                return false;
            }
            queue.add(t1.left);
            queue.add(t2.left);
            queue.add(t1.right);
            queue.add(t2.right);
        }
        return true;
    }
}