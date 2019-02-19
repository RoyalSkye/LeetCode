// 1.Map<Character,Character>  map.put(')','(') also works.
class Solution {
    public boolean isValid(String s) {
        boolean ans = true;
        if(s.isEmpty()) return true;
        Map<Character,Integer> map1 = new HashMap<>();
        Map<Character,Integer> map2 = new HashMap<>();
        map1.put('(',1);map1.put('{',2);map1.put('[',3);
        map2.put(')',1);map2.put('}',2);map2.put(']',3);
        Stack<Character> stack = new Stack<Character>();
        for(int i=0;i<s.length();i++){
            if(map1.containsKey(s.charAt(i))){  //push
                stack.push(s.charAt(i));
                continue;
            }else if(map2.containsKey(s.charAt(i))){  //pop
                if(stack.empty() || map1.get(stack.pop())!=map2.get(s.charAt(i))) return false;
            }
        }
        if(!stack.empty()) return false;
        return ans;
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