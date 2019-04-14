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
class Solution {  // all values are positive? No
    public boolean hasPathSum(TreeNode root, int sum) {
        return backtracking(root, 0, sum);
    }
    
    private boolean backtracking(TreeNode root, int cur, int sum){
        if(root == null) return false;
        cur += root.val;
        if(cur == sum && root.right == null && root.left == null) return true;
        return backtracking(root.left, cur, sum) || backtracking(root.right, cur, sum);
    }
}

// 2.Iterative - Stack - DFS
class Solution {
  public boolean hasPathSum(TreeNode root, int sum) {
    if (root == null) return false;
    LinkedList<TreeNode> node_stack = new LinkedList();
    LinkedList<Integer> sum_stack = new LinkedList();
    node_stack.add(root);
    sum_stack.add(sum - root.val);
    int curr_sum = 0;
    while ( !node_stack.isEmpty() ) {
      TreeNode node = node_stack.pollLast();
      curr_sum = sum_stack.pollLast();
      if ((node.right == null) && (node.left == null) && (curr_sum == 0)) return true;
      if (node.right != null) {
        node_stack.add(node.right);
        sum_stack.add(curr_sum - node.right.val);
      }
      if (node.left != null) {
        node_stack.add(node.left);
        sum_stack.add(curr_sum - node.left.val);
      }
    }
    return false;
  }
}