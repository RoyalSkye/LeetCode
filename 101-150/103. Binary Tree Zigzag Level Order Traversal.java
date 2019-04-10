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
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new LinkedList<>();
        if(root == null) return ans;
        recursive(ans, root, 0);
        return ans;
    }
    
    private void recursive(List<List<Integer>> ans, TreeNode root, int level){
        if(ans.size() == level) ans.add(new LinkedList<Integer>());
        if(level % 2 == 0) ans.get(level).add(root.val);
        else ans.get(level).add(0, root.val);
        if(root.left!=null) recursive(ans, root.left, level+1);
        if(root.right!=null) recursive(ans, root.right, level+1);
    }
}

// 2.Iterative
class Solution { 
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean reverse = false;
        while (!queue.isEmpty()) {
            int size = queue.size();
            /* use linkedlist to control time complexity of addFirst to O(1) */
            LinkedList<Integer> tempList = new LinkedList<>(); 
            //arraylist, 需要O(n), but LinkedList只需要O(1)就可以插入头部或者尾部
            //初始化LinkedList的时候不能用List interface, 要直接用LinkedList,不然找不到addFirst/removeFirst这些方法
            for (int i = 0; i < size; i++) {
                TreeNode curNode = queue.poll();
                if (reverse) tempList.addFirst(curNode.val);
                else tempList.add(curNode.val);
                if (curNode.left != null) queue.offer(curNode.left);
                if (curNode.right != null) queue.offer(curNode.right);
            }
            result.add(tempList);
            reverse = !reverse;
        }
        return result;
    }
}