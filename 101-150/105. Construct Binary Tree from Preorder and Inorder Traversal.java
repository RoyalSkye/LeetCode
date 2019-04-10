/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
// 获得子数组: Arrays.copyOfRange or subList
// note: subList返回的是原列表的一个视图，它所有的操作最终都会作用在原列表上
// Recursive: construct the left subtree and right subtree, respectively, recursively.
// 17ms - a little bit time-consuming, since we build 4 copyOfRange operation and iterate preorder[0] each time.
class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder.length == 0 && inorder.length == 0) return null;
        // for(int i=0;i<preorder.length;i++) System.out.print(preorder[i]+" ");
        // System.out.println();
        // for(int i=0;i<inorder.length;i++) System.out.print(inorder[i]+" ");
        // System.out.println();
        TreeNode root = new TreeNode(preorder[0]);
        int ro = 0;
        for(int i=0;i<inorder.length;i++){
            if(inorder[i] == preorder[0]){
                ro = i;
                break;
            } 
        }
        if(ro == 0) root.left = null;
        else root.left = buildTree(Arrays.copyOfRange(preorder, 1, ro+1), Arrays.copyOfRange(inorder, 0, ro));
        if(ro == inorder.length-1) root.right = null;
        else root.right = buildTree(Arrays.copyOfRange(preorder, ro+1, preorder.length), Arrays.copyOfRange(inorder, ro+1, inorder.length));
        return root;
    }
}

// use map avoid iterating preorder[prestart] each time - 3ms
class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0 || inorder.length == 0) return null;
        Map<Integer, Integer> indexes = new HashMap<Integer, Integer>();
        for (int i = 0; i < inorder.length; i++) {
            indexes.put(inorder[i], i);
        }
        return buildHelper(preorder, inorder, 0, 0, inorder.length - 1, indexes);
    }
    
    private TreeNode buildHelper(int[] preorder, int[] inorder, int prestart, int instart, int inend, Map<Integer, Integer> indexes) {
        TreeNode root = new TreeNode(preorder[prestart]);
        int index = indexes.get(preorder[prestart]);
        int leftSize = index - instart;
        int rightSize = inend - index;
        if (leftSize > 0) {
            root.left = buildHelper(preorder, inorder, prestart + 1, instart, index - 1, indexes);
        }
        if (rightSize > 0) {
            root.right = buildHelper(preorder, inorder, prestart + leftSize + 1, index + 1, inend, indexes);
        }
        return root;
    }
}