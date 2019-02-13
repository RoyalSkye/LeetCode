// 1.Recursion
class Solution {
    public boolean isMatch(String s, String p) {
        //System.out.println("isMatch("+s+", "+p+")");
        if(p.isEmpty()) return s.isEmpty();
        if(p.charAt(0) == '*') return false;
        boolean firstmatch = !s.isEmpty() && (p.charAt(0) == s.charAt(0) || p.charAt(0) == '.');
        if(p.length() >= 2 && p.charAt(1) == '*'){  //.* or a*
            return isMatch(s,p.substring(2)) || (firstmatch && isMatch(s.substring(1),p));
        }else{
           return firstmatch && isMatch(s.substring(1), p.substring(1));
        }
    }
}

// 2.Dynamic Programming - Top-Down Variation - perfect
enum Result {
    TRUE, FALSE
}
class Solution {
    Result[][] memo;
    public boolean isMatch(String text, String pattern) {
        memo = new Result[text.length() + 1][pattern.length() + 1];
        return dp(0, 0, text, pattern);
    }
    public boolean dp(int i, int j, String text, String pattern) {
        if (memo[i][j] != null) {
            return memo[i][j] == Result.TRUE;
        }
        boolean ans;
        if(j == pattern.length()){
            ans = (i == text.length());
        }else{
            boolean first_match = i < text.length() && (pattern.charAt(j) == text.charAt(i) || pattern.charAt(j) == '.');
            if(j + 1 < pattern.length() && pattern.charAt(j+1) == '*'){
                ans = dp(i, j+2, text, pattern) || (first_match && dp(i+1, j, text, pattern));
            }else{
                ans = first_match && dp(i+1, j+1, text, pattern);
            }
        }
        memo[i][j] = ans ? Result.TRUE : Result.FALSE;
        return ans;
    }
}

// 3.Dynamic Programming - Bottom-Up Variation(difficult to understand)
class Solution {
    public boolean isMatch(String text, String pattern) {
        boolean[][] dp = new boolean[text.length() + 1][pattern.length() + 1];
        dp[text.length()][pattern.length()] = true;
        for (int i = text.length(); i >= 0; i--){
            for (int j = pattern.length() - 1; j >= 0; j--){
                boolean first_match = i < text.length() && (pattern.charAt(j) == text.charAt(i) || pattern.charAt(j) == '.');
                if (j + 1 < pattern.length() && pattern.charAt(j+1) == '*'){
                    dp[i][j] = dp[i][j+2] || first_match && dp[i+1][j];
                } else {
                    dp[i][j] = first_match && dp[i+1][j+1];
                }
            }
        }
        return dp[0][0];
    }
}