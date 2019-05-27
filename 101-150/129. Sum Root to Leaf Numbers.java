/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
// 1.my solution
class Solution {
    public int sumNumbers(TreeNode root) {
        if(root == null) return 0;
        int sum = 0;
        List<Integer> path = new ArrayList<>();
        dfs(root, 0, path);
        for(int num : path){
            sum += num;
        }
        return sum;
    }
    
    private void dfs(TreeNode root, int sum, List<Integer> path){
        if(root.left == null && root.right == null){
            sum += root.val;
            path.add(sum);
            return;
        }
        int val = sum + root.val;
        if(root.left!=null){
            dfs(root.left, val*10, path);
        }
        if(root.right!=null){
            dfs(root.right, val*10, path);
        }
    }
}

// 2.recursive
class Solution {
    public int sumNumbers(TreeNode root) {
        return dfs(root, 0);
    }
    
    private int dfs(TreeNode root, int sum){
        if(root == null) return 0;
        if(root.left == null && root.right == null) return sum*10 + root.val;
        return dfs(root.left, sum*10 + root.val) + dfs(root.right, sum*10 + root.val);
    }
}