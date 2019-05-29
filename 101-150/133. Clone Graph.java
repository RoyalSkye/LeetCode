/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {}

    public Node(int _val,List<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
};
*/

// 1.DFS
class Solution {
    public Node cloneGraph(Node node) {
        Map<Node, Node> map = new HashMap<>();
        return dfs(map, node);
    }
    
    private Node dfs(Map<Node, Node> map, Node node){
        if(node == null) return null;
        if(map.get(node)!=null) return map.get(node);
        Node newnode = new Node(node.val, new LinkedList<Node>());
        map.put(node, newnode);
        List<Node> ll = node.neighbors;
        for(Node tmp : ll){
            newnode.neighbors.add(dfs(map, tmp));
        }
        return newnode;
    }
}

// 2.BFS
class Solution {
    public Node cloneGraph(Node node) {
        if(node == null) return null;
        Map<Node, Node> map = new HashMap<>();
        Queue<Node> q = new LinkedList<>();
        q.add(node);
        Node newnode = new Node(node.val, new LinkedList<Node>());
        map.put(node, newnode);
        while(!q.isEmpty()){
            Node n1 = q.remove();
            Node n2 = map.get(n1);
            List<Node> tmp = n1.neighbors;
            for(Node next : tmp){
                if(map.get(next)!=null) n2.neighbors.add(map.get(next));
                else{
                    Node newnode1 = new Node(next.val, new LinkedList<Node>());
                    n2.neighbors.add(newnode1);
                    map.put(next, newnode1);
                    q.add(next);
                }
            }
        }
        return map.get(node);
    }
}