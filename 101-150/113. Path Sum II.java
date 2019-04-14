/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
// 1.dfs/backtracking
class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> ans = new LinkedList<>();
        if(root == null) return ans;
        List<Integer> ll = new LinkedList<>();
        ll.add(root.val);
        dfs(root, ans, ll, 0, sum);
        return ans;
    }
    
    private void dfs(TreeNode root, List<List<Integer>> ans, List<Integer> ll, int cur, int sum){
        cur += root.val;
        if(root.left == null && root.right == null){
            if(cur == sum) ans.add(new LinkedList<Integer>(ll));
            return;
        }
        if(root.left!=null){
            ll.add(root.left.val);
            dfs(root.left, ans, ll, cur, sum);
            ll.remove(ll.size()-1);
        }
        if(root.right!=null){
            ll.add(root.right.val);
            dfs(root.right, ans, ll, cur, sum);
            ll.remove(ll.size()-1);
        }
    }
}

// 2.Iterative - don't understand
class Solution {
  public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> list = new ArrayList<>();
        if (root == null) return list;
        List<Integer> path = new ArrayList<>();
        Stack<TreeNode> s = new Stack<>();
        int pathSum = 0;
        TreeNode prev = null; //?
        TreeNode curr = root;
        while (curr != null || !s.isEmpty()){
            while (curr != null){
                s.push(curr);
                path.add(curr.val);
                pathSum += curr.val;
                curr = curr.left;
            }
            // check left leaf node's right subtree 
            // or check if it is not from the right subtree
            // why peek here? 
            // because if it has right subtree, we don't need to push it back
            curr = s.peek();
            if (curr.right != null && curr.right != prev){
                curr = curr.right;
                continue;
            }
            if (curr.left == null && curr.right == null && pathSum == sum){
                list.add(new ArrayList<Integer>(path));
            }
            s.pop();
            prev = curr;
            pathSum -= curr.val;
            path.remove(path.size()-1);
            // reset current node to null, so check the next item from the stack 
            curr = null;
        }
        return list;
    }
}