// non-trivial - HashMap + DoubleLinkedlist
// HashMap achieves both put and get operations cost O(1), 
// DoubleLinkedlist keep order and make delete operation O(1), since we can retrieve node.pre and node.next each time.
class LRUCache {

    class DLinkedNode{
        int key;
        int value;
        DLinkedNode pre;
        DLinkedNode next;
    }
    
    public void addNode(DLinkedNode node){ 
        node.pre = head;
        node.next = head.next;
        head.next.pre = node;
        head.next = node;
    }
    
    public void removeNode(DLinkedNode node){
        DLinkedNode pre = node.pre;
        DLinkedNode next = node.next;
        pre.next = next;
        next.pre = pre;
    }
    
    public void moveTohead(DLinkedNode node){
        removeNode(node);
        addNode(node);
    }
    
    public DLinkedNode removeTail(){
        DLinkedNode pre = tail.pre;
        removeNode(pre);
        return pre;
    }
    
    int capacity;
    DLinkedNode head, tail;
    Map<Integer, DLinkedNode> map = new HashMap<>();
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
        head = new DLinkedNode();
        tail = new DLinkedNode();
        head.pre = null;
        head.next = tail;
        tail.pre = head;
        tail.next = null;
    }
    
    public int get(int key) {
        DLinkedNode node = map.get(key);
        if(node == null) return -1;
        else{
            moveTohead(node);
            return node.value;
        }
    }
    
    public void put(int key, int value) {
        DLinkedNode node = map.get(key);
        if(node == null){
            node = new DLinkedNode();
            node.key = key;
            node.value = value;
            if(map.size() == this.capacity){
                DLinkedNode tail = removeTail();
                map.remove(tail.key);
            }
            addNode(node);
            map.put(key, node);
        }else{
            node.value = value;
            moveTohead(node);
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */