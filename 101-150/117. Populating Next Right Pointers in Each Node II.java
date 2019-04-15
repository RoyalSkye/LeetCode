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
class Solution {
    public Node connect(Node root) {
        Node first = root;
        Node dummy = new Node(0);
        Node pre = dummy;
        while(first!=null){
            while(first!=null){
                if(first.left!=null) {pre.next = first.left; pre = pre.next;}
                if(first.right!=null) {pre.next = first.right; pre = pre.next;}
                first = first.next;
            }
            first = dummy.next;
            dummy.next = null;
            pre = dummy;
        }
        return root;
    }
}