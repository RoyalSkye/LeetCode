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
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        LinkedList<List<Integer>> ans = new LinkedList<>();
        if(root == null) return ans;
        recursive(ans, root, 0);
        return ans;
    }
    
    private void recursive(LinkedList<List<Integer>> ans, TreeNode root, int level){
        if(ans.size() == level){
            List<Integer> ll = new LinkedList<>();
            ans.addFirst(ll);
        }
        ans.get(ans.size()-level-1).add(root.val);
        if(root.left!=null) recursive(ans, root.left, level+1);
        if(root.right!=null) recursive(ans, root.right, level+1);
    }
}

// 2.Iterative
class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        LinkedList<List<Integer>> ans = new LinkedList<>();
        if(root == null) return ans;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            int size = q.size();
            List<Integer> ll = new LinkedList<>();
            for(int i=0;i<size;i++){
                TreeNode tmp =q.poll();
                ll.add(tmp.val);
                if(tmp.left!=null) q.add(tmp.left);
                if(tmp.right!=null) q.add(tmp.right);
            }
            ans.addFirst(ll);
        }
        return ans;
    }
}