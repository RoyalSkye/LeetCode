// 1.Recursive-DFS
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
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return dfs(nums, 0, nums.length-1);
    }
    
    private TreeNode dfs(int[] nums, int start, int end){
        if(start > end) return null;
        int index = start;
        for(int i=start;i<=end;i++){
            if(nums[i] > nums[index]) index = i;
        }
        TreeNode root = new TreeNode(nums[index]);
        root.left = dfs(nums, start, index-1);
        root.right = dfs(nums, index+1, end);
        return root;
    }
}