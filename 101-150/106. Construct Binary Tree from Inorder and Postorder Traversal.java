/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
// Recursive - construct the tree from the last element of preorder(root of tthe ree)
class Solution {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if(inorder.length == 0 || postorder.length == 0) return null;
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0;i<inorder.length;i++) map.put(inorder[i], i);
        return recursive(inorder, postorder, postorder.length-1, 0, inorder.length-1, map);
    }
    
    private TreeNode recursive(int[] inorder, int[] postorder, int post, int instart, int inend, Map<Integer, Integer> map){
        TreeNode root = new TreeNode(postorder[post]);
        int rootindex = map.get(postorder[post]);
        int leftsize = rootindex - instart;
        int rightsize = inend - rootindex;
        if(rightsize > 0) root.right = recursive(inorder, postorder, post-1, rootindex+1, inend, map);
        if(leftsize > 0) root.left = recursive(inorder, postorder, post-rightsize-1, instart, rootindex-1, map);
        return root;
    }
}