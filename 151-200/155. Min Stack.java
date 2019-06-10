// 1.two/one Stack(just push nums and cur min into one stack)
class MinStack {
    Stack<Integer> s1;
    Stack<Integer> s2;
    int min;

    /** initialize your data structure here. */
    public MinStack() {
        s1 = new Stack<Integer>();
        s2 = new Stack<Integer>();
        min = Integer.MAX_VALUE;
    }
    
    public void push(int x) {
        s1.push(x);
        min = Math.min(min, x);
        s2.push(min);
    }
    
    public void pop() {
        s2.pop();
        if(s1.pop() == min) min = (s1.isEmpty()) ? Integer.MAX_VALUE : s2.peek();
    }
    
    public int top() {
        return s1.peek();
    }
    
    public int getMin() {
        return s2.peek();
    }
}

// 2.create a data structure to support the operation
class MinStack {
    Node head;

    /** initialize your data structure here. */
    public MinStack() {}
    
    public void push(int x) {
        if(head == null) head = new Node(x, x, null);
        else head = new Node(x, Math.min(head.min, x), head);
    }
    
    public void pop() {
        head = head.next;
    }
    
    public int top() {
        return head.val;
    }
    
    public int getMin() {
        return head.min;
    }
    
    class Node{
        int val;
        int min;
        Node next;
        
        public Node(int val, int min, Node next){
            this.val = val;
            this.min = min;
            this.next = next;
        }
    }
}
