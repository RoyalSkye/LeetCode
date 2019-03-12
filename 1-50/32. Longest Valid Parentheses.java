 // 1.my solution - use an auxiliary array and stack - O(N)
class Solution {
    public int longestValidParentheses(String s) {
        int ans = 0, pos = 0;
        int[] aux = new int[s.length()];
        Stack<Integer> stack = new Stack<>();
        for(int i=0;i<s.length();i++){
            if(s.charAt(i) == '('){
                stack.push(i);
            }else if(s.charAt(i) == ')'){
                if(!stack.empty()){
                    aux[i] = 1;
                    aux[stack.pop()] = 1;
                }
            }
        }
        while(pos < s.length()){
            int tmp = 0;
            while(pos < s.length() && aux[pos]!=0){
                tmp++;
                pos++;
            }
            ans = tmp > ans ? tmp : ans;
            pos++;
        } 
        return ans;
    }
}

// 2.Dynamic Programming - O(N)
class Solution {
    public int longestValidParentheses(String s) {
        int ans = 0;
        int[] dp = new int[s.length()];
        for(int i=1;i<s.length();i++){
            if(s.charAt(i)==')'){
                if(s.charAt(i-1)=='('){
                    dp[i] = i > 1 ? dp[i-2] + 2 : 2;
                }else if(i-dp[i-1]-1>=0 && s.charAt(i-dp[i-1]-1) == '('){  //&& s.charAt(i-1)==')' 
                    dp[i] =  i-dp[i-1]-2 >= 0 ? dp[i-1] + dp[i-dp[i-1]-2] + 2 : dp[i-1] + 2; 
                }
                ans = Math.max(dp[i], ans);
            }
        }
        return ans;
    }
}

// 3.Use Stack and 4.Without extra space but not readable
// https://leetcode.com/problems/longest-valid-parentheses/solution/