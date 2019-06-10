// 1.Stack
class Solution {
    public int evalRPN(String[] tokens) {
        Stack<String> s = new Stack<>();
        Set<String> operators = new HashSet<>();
        operators.add("+"); operators.add("-"); operators.add("*"); operators.add("/");
        int len = tokens.length;
        int res = 0;
        for(int i=0;i<len;i++){
            if(!operators.contains(tokens[i])){
                s.push(tokens[i]);
            }else{
                int operand1 = Integer.parseInt(s.pop());
                int operand2 = Integer.parseInt(s.pop());
                res = 0;
                if(tokens[i].equals("+")) res = operand2 + operand1;
                else if(tokens[i].equals("-"))res = operand2 - operand1;
                else if(tokens[i].equals("*")) res = operand2 * operand1;
                else res = operand2 / operand1;
                s.push(String.valueOf(res));
            }
        }
        return Integer.parseInt(s.pop());
    }
}