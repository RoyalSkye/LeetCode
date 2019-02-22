// 1.my solution - brute force(10ms) - O(2^2n * n) 
class Solution {
    List<String> ans = new LinkedList<>();
    
    public List<String> generateParenthesis(int n) { 
        String combination = "";
        generateNormal(combination, 2*n);
        return ans;
    }
    
    private void generateNormal(String combination, int n){
        if(n == 0){
            if(isParenthesis(combination)) ans.add(combination);
            return;
        }else{
            generateNormal(combination+'(' , n-1);
            generateNormal(combination+')' , n-1);
        }
    }
    
    private boolean isParenthesis(String s){
        int count = 0;
        for(char c : s.toCharArray()){
            if(c == '(') count++;
            else if(count>0){
                count--;
            }else return false;
        }
        return count == 0;
    }
}

// 2.Backtracking(1ms): only add them when we know it will remain a valid sequence.
class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList();
        backtrack(ans, "", 0, 0, n);
        return ans;
    }

    public void backtrack(List<String> ans, String cur, int open, int close, int max){
        if (cur.length() == max * 2) {
            ans.add(cur);
            return;
        }

        if (open < max)
            backtrack(ans, cur+"(", open+1, close, max);
        if (close < open)
            backtrack(ans, cur+")", open, close+1, max);
    }
}

// 3.Closure Number - hard to understand - can use dp to optimize it.
class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList();
        if (n == 0) {
            ans.add("");
        } else {
            for (int c = 0; c < n; ++c)
                for (String left: generateParenthesis(c))
                    for (String right: generateParenthesis(n-1-c)){
                        ans.add("(" + left + ")" + right);
                    }
                        
        }
        return ans;
    }
}
