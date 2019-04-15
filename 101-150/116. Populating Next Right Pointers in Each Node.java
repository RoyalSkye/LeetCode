/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val,Node _left,Node _right,Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/
// 1.BFS with Queue
class Solution {
    public Node connect(Node root) {
        if(root == null) return null;
        Node tmp = root;
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            int size = q.size();
            Node pre = q.poll();
            if(pre.left!=null) q.add(pre.left);
            if(pre.right!=null) q.add(pre.right);
            for(int i=1;i<size;i++){
                Node cur = q.poll();
                pre.next = cur;
                pre = cur;
                if(cur.left!=null) q.add(cur.left);
                if(cur.right!=null) q.add(cur.right);
            }
        }
        return root;
    }
}

// 2.Recursive
class Solution {
    public Node connect(Node root) {
        if(root==null) return null;
        if(root.left!=null)
            root.left.next = root.right;
        if(root.right!=null)
            if(root.next!=null)
                root.right.next = root.next.left;
        connect(root.left);
        connect(root.right);
        return root;
    }
}