/*
// Definition for a Node.
class Node {
    public int val;
    public Node next;
    public Node random;

    public Node() {}

    public Node(int _val,Node _next,Node _random) {
        val = _val;
        next = _next;
        random = _random;
    }
};
*/

// the same as LC133. Clone Graph
// 1.DFS
class Solution {
    public Node copyRandomList(Node head) {
        Map<Node, Node> map = new HashMap<>();
        return dfs(map, head);
    }
    
    private Node dfs(Map<Node, Node> map, Node node){
        if(node == null) return null;
        if(map.get(node)!=null) return map.get(node);
        Node newnode = new Node(node.val, new Node(), new Node());
        map.put(node, newnode);
        newnode.next = dfs(map, node.next);
        newnode.random = dfs(map, node.random);
        return newnode;
    }
}

// 2.BFS
class Solution {
    public Node copyRandomList(Node head) {
        if(head == null) return null;
        Map<Node, Node> map = new HashMap<>();
        Queue<Node> q = new LinkedList<>();
        q.add(head);
        Node n1 = new Node(head.val, new Node(), new Node());
        map.put(head, n1);
        while(!q.isEmpty()){
            Node node = q.remove();
            Node newnode = map.get(node);
            Node next = node.next;
            Node random = node.random;
            if(next == null){
                newnode.next = null;
            }else if(map.get(next)!=null){
                newnode.next = map.get(next);
            }else{
                Node newnodenext = new Node(next.val, new Node(), new Node());
                newnode.next = newnodenext;
                map.put(next, newnodenext);
                q.add(next);
            }
            if(random == null){
                newnode.random = null;
            }else if(map.get(random)!=null){
                newnode.random = map.get(random);
            }else{
                Node newnoderandom = new Node(random.val, new Node(), new Node());
                newnode.random = newnoderandom;
                map.put(random, newnoderandom);
                q.add(random);
            }
        }
        return map.get(head);
    }
}

// brief and concise
class Solution {
    Map<Node, Node> map = new HashMap<>();
    
    public Node copyRandomList(Node head) {
        if(head == null) return null;
        Node oldnode = head;
        while(oldnode!=null){
            Node newnode = cloneNode(oldnode);
            newnode.next = cloneNode(oldnode.next);
            newnode.random = cloneNode(oldnode.random);
            oldnode = oldnode.next;
        }
        return map.get(head);
    }
    
    private Node cloneNode(Node node){
        if(node != null){
            if(map.get(node)!=null) return map.get(node);
            else{
                map.put(node, new Node(node.val, null, null));
                return map.get(node);
            }
        }
        return null;
    }
}

// 3.Iterative with Time:O(N) Space:O(1)
// The idea is to associate the original node with its copy node in a single linked list. In this way, we don't need extra space to keep track of the new nodes.
class Solution {
    public Node copyRandomList(Node head) {
        if(head == null) return null;
        Node first = head;
        //1.create newnode
        while(first!=null){
            Node n = new Node(first.val, null, null);
            n.next = first.next;
            first.next = n;
            first = n.next;
        }
        //2.link random to newnode
        first = head;
        while(first!=null){
            if(first.random!=null) first.next.random = first.random.next;
            first = first.next.next;
        }
        //3.extract copy list and restore original list
        first = head;
        Node pseudoHead = new Node(0, null, null);
        Node n1 = pseudoHead;
        while(first!=null){
            Node tmp = first.next.next;
            n1.next = first.next;
            n1 = n1.next;
            first.next = tmp;
            first = tmp;
        }
        return pseudoHead.next;
    }
}