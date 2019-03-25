// 1.Stack
class Solution {
    public String simplifyPath(String path) {
        StringBuilder ans = new StringBuilder("");
        Stack<String> stack = new Stack<>();
        String[] p = path.split("/");
        for(String s: p){
            // System.out.println(s+" ");
            if(s.equals("") || s.equals(".")) continue; 
            if(s.equals("..")){
                if(!stack.empty()) stack.pop();
            }else stack.push(s); 
        }
        if(stack.empty()) ans.append("/");
        else{
            while(!stack.empty()){
                ans.insert(0, "/");
                ans.insert(1, stack.pop());
            }
        }
        return ans.toString();
    }
}

// 2.Deque
// https://docs.oracle.com/javase/8/docs/api/java/util/Deque.html?is-external=true
class Solution {
    public String simplifyPath(String path) {
        StringBuilder ans = new StringBuilder("");
        Deque<String> deck = new LinkedList<>();  //双端队列
        for(String s: path.split("/")){
            if(s.equals("..")) deck.poll();
            else if(!s.equals("") && !s.equals(".")) deck.push(s);
        }
        while(deck.size()!=0) ans.append("/"+deck.pollLast());
        return ans.length() == 0 ? "/" : ans.toString();
    }
}