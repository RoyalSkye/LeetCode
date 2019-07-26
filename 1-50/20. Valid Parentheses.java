// 1.Map<Character,Character>  map.put(')','(') also works.
class Solution {
    public boolean isValid(String s) {
        Map<Character, Character> map = new HashMap<>();
        map.put(')', '(');map.put('}', '{');map.put(']', '[');
        Stack<Character> stack = new Stack<>();
        char[] c = s.toCharArray();
        for(int i=0;i<c.length;i++){
            if(map.containsKey(c[i])){
                if(stack.isEmpty() || stack.pop() != map.get(c[i])) return false;
            }else stack.push(c[i]);
        }
        return stack.isEmpty();
    }
}

// 2. brilliant!!
class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<Character>();
        for (char c : s.toCharArray()) {
            if (c == '(')
                stack.push(')');
            else if (c == '{')
                stack.push('}');
            else if (c == '[')
                stack.push(']');
            else if (stack.isEmpty() || stack.pop() != c)  //encounter a closing bracket
                return false;
        }
        return stack.isEmpty();
    }
}