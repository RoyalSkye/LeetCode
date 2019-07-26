// 1.my solution - HashMap<children, parent>
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
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        Map<TreeNode, TreeNode> map = new HashMap<>();
        map.put(root, null);
        findAncestor(map, root);
        TreeNode n1 = p;
        Set<TreeNode> set = new HashSet<>();
        while(n1 != null){
            set.add(n1);
            n1 = map.get(n1);
        }
        n1 = q;
        while(q != null){
            if(!set.contains(n1)){
                set.add(n1);
                n1 = map.get(n1);
            } 
            else break;
        }
        return n1;
    }
    
    private void findAncestor(Map<TreeNode, TreeNode> map, TreeNode root){
        if(root == null) return;
        map.put(root.left, root);
        map.put(root.right, root);
        findAncestor(map, root.left);
        findAncestor(map, root.right);
    }
}

// 2.Recursive - Make use of the property of BST
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        int parentVal = root.val;
        int pVal = p.val;
        int qVal = q.val;
        if (pVal > parentVal && qVal > parentVal) {
            return lowestCommonAncestor(root.right, p, q);
        } else if (pVal < parentVal && qVal < parentVal) {
            return lowestCommonAncestor(root.left, p, q);
        } else {
            return root;
        }
    }
}

// 3.Iterative
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        int pval = p.val;
        int qval = q.val;
        TreeNode node = root;
        while(node != null){
            int rootval = node.val;
            if(pval < rootval && qval < rootval){
                node = node.left;
            }else if(pval > rootval && qval > rootval){
                node = node.right;
            }else return node;
        }
        return null;
    }
}