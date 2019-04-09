/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
// 1.my solution - Iterative - record the number of nodes of each level
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new LinkedList<>();
        if(root == null) return ans;
        List<Integer> ll = new LinkedList<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        int count = 0, target = 0, pretarget = 1;
        while(!q.isEmpty()){
            TreeNode tmp = q.poll();
            count++;
            ll.add(tmp.val);
            if(tmp.left!=null){
                target += 1;
                q.add(tmp.left);
            } 
            if(tmp.right!=null){
                target += 1;
                q.add(tmp.right);
            } 
            if(count == pretarget){
                ans.add(ll);
                ll = new LinkedList<Integer>();
                count = 0;
                pretarget = target;
                target = 0;
            }
        }
        return ans;
    }
}

// or 
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new LinkedList<>();
        if(root == null) return ans;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        int level = 0;
        while(!q.isEmpty()){
            ans.add(new LinkedList<Integer>());
            int count = q.size();
            for(int i=0;i<count;i++){
                TreeNode tmp = q.poll();
                ans.get(level).add(tmp.val);
                if(tmp.left != null) q.add(tmp.left);
                if(tmp.right != null) q.add(tmp.right);
            }
            level++;
        }
        return ans;
    }
}

// 2.Recursive - dfs to solve bfs
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new LinkedList<>();
        if(root == null) return ans;
        recursive(ans, root, 0);
        return ans;
    }
    
    private void recursive(List<List<Integer>> ans, TreeNode root, int level){
        if(ans.size() == level) ans.add(new LinkedList<Integer>());
        ans.get(level).add(root.val);
        if(root.left!=null) recursive(ans, root.left, level+1); 
        if(root.right!=null) recursive(ans, root.right, level+1); 
    }
}