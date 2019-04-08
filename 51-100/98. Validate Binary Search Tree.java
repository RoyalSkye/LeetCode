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
    public boolean isValidBST(TreeNode root) {
        if(root == null) return true;
        return dfs(root, null, null);
    }
    
    private boolean dfs(TreeNode root, Integer min, Integer max){
        if(root == null) return true;
        if(min != null && root.val <= min) return false;
        if(max != null && root.val >= max) return false;
        return dfs(root.left, min, root.val) && dfs(root.right, root.val, max);
    }
}

// bug: test case: [2147483647]
// class Solution {
//     public boolean isValidBST(TreeNode root) {
//         return dfs(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
//     }
    
//     private boolean dfs(TreeNode root, int min, int max){
//         if(root == null) return true;
//         // System.out.println(root.val+" "+min+" "+max);
//         if(root.val <= min || root.val >= max) return false;
//         return dfs(root.left, min, root.val) && dfs(root.right, root.val, max);
//     }
// }

// 2.Iterative
class Solution {
  public boolean isValidBST(TreeNode root) {
    if (root == null) return true;
    LinkedList<TreeNode> stack = new LinkedList();
    LinkedList<Integer> upper_limits = new LinkedList();
    LinkedList<Integer> lower_limits = new LinkedList();
    stack.add(root);
    upper_limits.add(null);
    lower_limits.add(null);
    while (!stack.isEmpty()) {
      TreeNode node = stack.poll();
      Integer lower_limit = lower_limits.poll();
      Integer upper_limit = upper_limits.poll();
      if (node.right != null) {
        if (node.right.val > node.val) {
          if ((upper_limit != null) && (node.right.val >= upper_limit))
            return false;
          stack.add(node.right);
          lower_limits.add(node.val);
          upper_limits.add(upper_limit);
        } else
          return false;
      }

      if (node.left != null) {
        if (node.left.val < node.val) {
          if ((lower_limit != null) && (node.left.val <= lower_limit))
            return false;
          stack.add(node.left);
          lower_limits.add(lower_limit);
          upper_limits.add(node.val);
        } else
          return false;
      }
    }
    return true;
  }
}

// 3.Inorder Traversal
class Solution {
  public boolean isValidBST(TreeNode root) {
    Stack<TreeNode> stack = new Stack();
    double inorder = - Double.MAX_VALUE;
    while (!stack.isEmpty() || root != null) {
      while (root != null) {
        stack.push(root);
        root = root.left;
      }
      root = stack.pop();
      if (root.val <= inorder) return false;
      inorder = root.val;
      root = root.right;
    }
    return true;
  }
}