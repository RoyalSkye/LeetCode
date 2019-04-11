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
// 结果不唯一，只要他们的Inorder Traversal 与 nums 一致
class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
        if(nums.length == 0) return null;
        return recursive(nums, 0, nums.length-1);
    }
    
    private TreeNode recursive(int[] nums, int start, int end){
        int rootindex = start + (end - start) / 2;
        TreeNode root = new TreeNode(nums[rootindex]);
        int leftsize = rootindex - start;
        int rightsize = end - rootindex;
        if(leftsize > 0) root.left = recursive(nums, start, rootindex-1);
        if(rightsize > 0) root.right = recursive(nums, rootindex+1, end);
        return root;
    }
}