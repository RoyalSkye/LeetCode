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
    public List<Integer> postorderTraversal(TreeNode root) {
        //left-right-middle
        List<Integer> postorder = new LinkedList<>();
        recursive(root, postorder);
        return postorder;
    }
    
    private void recursive(TreeNode root, List<Integer> postorder){
        if(root == null) return;
        recursive(root.left, postorder);
        recursive(root.right, postorder);
        postorder.add(root.val);   
    }
}

// 2.Iterative with Stack and Set
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> postorder = new LinkedList<>();
        if(root == null) return postorder;
        Stack<TreeNode> stack = new Stack<>();
        Set<TreeNode> visited = new HashSet<>();
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode node = stack.peek();
            if(visited.contains(node)){
                node = stack.pop();
                postorder.add(node.val);
                continue;
            } 
            if(node.left == null && node.right == null){
                node = stack.pop();
                postorder.add(node.val);
                visited.add(node);
                continue;
            }
            if(node.right!=null) stack.push(node.right);
            if(node.left!=null) stack.push(node.left);
            visited.add(node);
        }
        return postorder;
    }
}

// Iterative with Stack only
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> postorder = new LinkedList<>();
        if(root == null) return postorder;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while(!stack.isEmpty() || cur != null){
            while(cur!=null){
                stack.push(cur);
                if(cur.left!=null) cur = cur.left;
                else cur = cur.right;
            }
            TreeNode tmp = stack.pop();
            postorder.add(tmp.val);
            if(!stack.isEmpty() && stack.peek().left == tmp) cur = stack.peek().right;
        }
        return postorder;
    }
}

// or some tricky way
class Solution {
    //left-right-middle --> middle-right-left
    public List<Integer> postorderTraversal(TreeNode root) {
        LinkedList<Integer> postorder = new LinkedList<>();
        if(root == null) return postorder;
        LinkedList<TreeNode> stack = new LinkedList<>();
        stack.add(root);
        while(!stack.isEmpty()){
            TreeNode node = stack.pollLast();
            postorder.addFirst(node.val);
            if(node.left!=null) stack.add(node.left);
            if(node.right!=null) stack.add(node.right);
        }
        return postorder;
    }
}

// 3.Morris traversal - inorder(Easy) Preorder(Medium) Postorder(Hard)
