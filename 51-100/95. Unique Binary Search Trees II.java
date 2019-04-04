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
    public List<TreeNode> generateTrees(int n) {
        List<TreeNode> ans = new ArrayList<>();
        if(n == 0) return ans;
        return dfs(1, n);
    }
    
    private List<TreeNode> dfs(int start, int end){
        List<TreeNode> tree = new ArrayList<>();
        if(start > end){
            tree.add(null);
            return tree;
        }
        
        for(int i=start;i<=end;i++){
            List<TreeNode> left = dfs(start, i-1);
            List<TreeNode> right = dfs(i+1, end);
            for(TreeNode tmpleft : left){
                for(TreeNode tmpright : right){
                    TreeNode cur = new TreeNode(i);
                    cur.left = tmpleft;
                    cur.right = tmpright;
                    tree.add(cur);
                } 
            }
        }
        return tree;
    }
}