/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

// 1.two pointers
class Solution {
    public boolean findTarget(TreeNode root, int k) {
        List<Integer> nums = new ArrayList<>();
        InitList(root, nums);
        int low = 0, high = nums.size() - 1;
        while(low < high) {
            if (nums.get(low) + nums.get(high) == k) 
                return true;
            else if (nums.get(low) + nums.get(high) > k)
                high--;
            else
                low++;
        }
        return false;
    }
    
    private void InitList(TreeNode root, List nums) {
        if (root == null) return;
        InitList(root.left, nums);
        nums.add(root.val);
        InitList(root.right, nums);
    }
}

// python
class Solution:
    def findTarget(self, root: TreeNode, k: int) -> bool:
        nums = []
        self.inorder(root, nums)
        low, high = 0, len(nums) - 1
        while low < high:
            sum = nums[low] + nums[high]
            if sum == k:
                return True
            elif sum > k:
                high -= 1
            else:
                low += 1
        return False
    
    def inorder(self, root: TreeNode, nums: List) -> None:
        if not root:
            return
        self.inorder(root.left, nums)
        nums.append(root.val)
        self.inorder(root.right, nums)

// 2.BFS + HashSet
class Solution {
    public boolean findTarget(TreeNode root, int k) {
        Set<Integer> set = new HashSet<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.remove();
            if (node == null) 
                continue;
            if (set.contains(k - node.val)) 
                return true;
            set.add(node.val);
            queue.add(node.left);
            queue.add(node.right);
        }
        return false;
    }
}
